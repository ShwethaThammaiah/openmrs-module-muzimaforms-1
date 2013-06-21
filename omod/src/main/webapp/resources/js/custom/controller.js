'use strict';
function FormCtrl($scope, FormService, FormsService, XFormService, TagService, _, $q) {
    var getTags = function () {
        return TagService.all();
    };
    var getForms = function () {
        return FormsService.all();
    };
    var getXForms = function () {
        return XFormService.all();
    };
    var setTags = function (result) {
        $scope.tags = result.data;
    };
    var setXForms = function (result) {
        $scope.xForms = result.data;

    };
    var setForms = function (result) {
        $scope.forms = result.data;
        $scope.html5forms = _.map(result.data, function (form) {
            return {
                form: form,
                newTag: ""
            };
        });
    };

    $scope.init = function () {

        $scope.selectedXForms = [];
        $scope.editMode = true;
        $scope.importMode = false;
        $scope.tagColorMap = {};
        $scope.activeTagFilters = [];

        getTags().then(setTags);
        getForms().then(setForms);
    };

    $scope.import = function () {
        $scope.importMode = true;
        getXForms().then(setXForms);
    };

    $scope.done = function () {
        var allSaved = $q.all(_.map($scope.selectedXForms, function (value) {
            return FormService.save({id: value});
        }));
        allSaved.then(FormsService.all).then(setForms);
        $scope.importMode = false;
    };

    $scope.cancelImport = function () {
        $scope.importMode = false;
    };

    $scope.hasXForms = function () {
        return !_.isEmpty($scope.xForms);
    };

    $scope.hasForms = function () {
        return !_.isEmpty($scope.html5forms);
    };

    $scope.getPreviewFormPath = function () {
        if ($scope.selectedFormId)
            return '../../moduleResources/html5forms/html5forms/form-' + $scope.selectedFormId + '.html';
    };

    $scope.selectForm = function (id) {
        $scope.selectedFormId = id;
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

    $scope.activeForm = function (id) {
        return id === $scope.selectedFormId ? 'active-form' : undefined;
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

    $scope.saveTag = function (html5form) {
        if (html5form.newTag === "") return;
        var form = html5form.form;
        var newTag = html5form.newTag;
        var tagToBeAdded = caseInsensitiveFind($scope.tags, newTag) || {"name": newTag};
        html5form.newTag = "";
        if (caseInsensitiveFind(form.tags, tagToBeAdded)) return;
        form.tags.push(tagToBeAdded);
        FormService.save(form)
            .then(function (result) {
                return FormService.get(form.id);
            })
            .then(function (savedForm) {
                angular.extend(form, savedForm);
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
                        return FormService.get(form.id);
                    })
                    .then(function (savedForm) {
                        angular.extend(form, savedForm);
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

