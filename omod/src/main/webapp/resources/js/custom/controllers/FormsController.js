'use strict';
function FormsCtrl($scope, FormService, XFormService, TagService, _, $q) {
    $scope.init = function () {
        $scope.selectedXForms = [];
        $scope.editMode = false;
        $scope.importMode = false;
        $scope.tagColorMap = {};
        $scope.activeTagFilters = [];

        getTags().then(setTags);
        getForms().then(setForms).then(selectFirstForm);

    };

    var getTags = function () {
        return TagService.all();
    };
    var getForms = function () {
        return FormService.all();
    };
    var getXForms = function () {
        return XFormService.all();
    };
    var setTags = function (result) {
        $scope.tags = result.data.results;
    };
    var setXForms = function (result) {
        $scope.xForms = result.data;

    };
    var setForms = function (result) {
        $scope.forms = result.data.results;
        $scope.muzimaforms = _.map(result.data.results, function (form) {

            return {
                form: form,
                newTag: ""
            };
        });
    };

    var selectFirstForm = function () {
        var firstForm = _.head($scope.forms);
        if (firstForm)
            $scope.selectForm(firstForm.uuid);
    }

    $scope.import = function () {
        $scope.importMode = true;
        getXForms().then(setXForms);
    };

    $scope.done = function () {
        var allSaved = $q.all(_.map($scope.selectedXForms, function (value) {
            return FormService.save({id: value});
        }));
        allSaved.then(FormService.all).then(setForms);
        $scope.importMode = false;
    };

    $scope.cancelImport = function () {
        $scope.importMode = false;
    };

    $scope.hasXForms = function () {
        return !_.isEmpty($scope.xForms);
    };

    $scope.hasForms = function () {
        return !_.isEmpty($scope.muzimaforms);
    };

    $scope.getFormPreview = function () {
        return $scope.selectedForm ? $scope.selectedForm.html : "";
    };

    $scope.selectForm = function (uuid) {
        if($scope.selectedFormId === uuid) return;

        $scope.selectedFormId = uuid;

        var setSelectedForm = function (result) {
            var form = result.data;
            $scope.selectedForm = form;
            return form.uuid;
        }
        FormService.get(uuid).then(setSelectedForm);
    };

    $scope.activeForm = function (uuid) {
        return uuid === $scope.selectedFormId ? 'active-form' : undefined;
    };


    $scope.selectXForm = function (id) {
        var indexOfId = $scope.selectedXForms.indexOf(id);
        if (indexOfId >= 0) {
            $scope.selectedXForms.splice(indexOfId, 1);
        } else {
            $scope.selectedXForms.push(id);
        }
    };

    $scope.activeXForm = function (id) {
        var indexOfId = $scope.selectedXForms.indexOf(id);
        return indexOfId >= 0 ? 'active-xform' : undefined;
    };

    var tagColor = function (tagId) {
        var tag = $scope.tagColorMap[tagId];
        if (!tag) {
            $scope.tagColorMap[tagId] = {};
            $scope.tagColorMap[tagId].color = 'rgb(' + (50 + Math.floor(Math.random() * 150)) + ',' + (50 + Math.floor(Math.random() * 150)) + ',' + (50 + Math.floor(Math.random() * 150)) + ')';
        }
        return $scope.tagColorMap[tagId].color;
    };

    $scope.tagStyle = function (tagId) {
        return  {'background-color': tagColor(tagId)};
    };

    $scope.tagNames = function () {
        var tagNames = [];
        angular.forEach($scope.tags, function (tag) {
            tagNames.push(tag.name);
        });
        return tagNames;
    };

    var caseInsensitiveFind = function (tags, newTag) {
        return _.find(tags, function (tag) {
            return angular.lowercase(tag.name) === angular.lowercase(newTag);
        });
    };

    $scope.saveTag = function (muzimaform) {
        if (muzimaform.newTag === "") return;
        var form = muzimaform.form;
        var newTag = muzimaform.newTag;
        var tagToBeAdded = caseInsensitiveFind($scope.tags, newTag) || {"name": newTag};

        muzimaform.newTag = "";
        if (caseInsensitiveFind(form.tags, tagToBeAdded.name)) return;
        form.tags.push(tagToBeAdded);
        FormService.save(form)
            .then(function (result) {
                return FormService.get(form.uuid);
            })
            .then(function (savedForm) {
                angular.extend(form, savedForm.data);
                if (!tagToBeAdded.id)
                    getTags().then(setTags);
            });

    };

    $scope.removeTag = function (form, tagToRemove) {
        angular.forEach(form.tags, function (tag, index) {
            if (tag.name === tagToRemove.name) {
                form.tags.splice(index, 1);
                FormService.save(form)
                    .then(function (result) {
                        return FormService.get(form.uuid);
                    })
                    .then(function (savedForm) {
                        angular.extend(form, savedForm.data);
                    });
            }
        });
    };

    $scope.tagFilterActive = function () {
        return !_.isEmpty($scope.activeTagFilters);
    };

    $scope.removeTagFilter = function (tag) {
        $scope.activeTagFilters = _.without($scope.activeTagFilters, tag);
    };

    $scope.addTagFilter = function (tagToAdd) {
        var tag = _.find($scope.tags, function (tag) {
            return tag.id === tagToAdd.id;
        });
        $scope.activeTagFilters = _.union($scope.activeTagFilters, [tag]);
    };

}

