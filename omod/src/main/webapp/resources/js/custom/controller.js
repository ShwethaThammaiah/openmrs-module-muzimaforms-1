'use strict';
function FormCtrl($scope, FormsService, FormService, XFormService, TagService, _, $q) {
    var tagsPromise = TagService.all();
    var formsPromise = FormsService.all();
    var xFormsPromise = XFormService.all();

    var dataLoaded = $q.all([tagsPromise, formsPromise, xFormsPromise]);

    var setTags = function (result) {
        console.log(JSON.stringify(result.data));
        $scope.tags = result.data;
    };
    var setXForms = function (result) {
        console.log(JSON.stringify(result.data));

        $scope.xForms = result.data;
    };

    var setHTML5Forms = function (result) {
        console.log(JSON.stringify(result.data));

        $scope.forms = result.data;
        $scope.html5forms = _.map(result.data, function (form) {
            return {
                form: form,
                newTag: ""
            };
        });
    };

    $scope.xForms = [];
    $scope.selectedXForms = [];
    $scope.editMode = true;
    $scope.importMode = false;
    $scope.tagColorMap = {};

    $scope.import = function () {
        $scope.importMode = true;
    };

    $scope.done = function () {
        var savePromises = [];
        _.each($scope.selectedXForms, function (value) {
            savePromises.push(FormService.save({id: value}));
        });
        var allSaved = $q.all(savePromises);
        allSaved.then(FormsService.all).then(setHTML5Forms);
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
        if (html5form.newTag === "")
            return;

        var form = html5form.form;
        var newTag = html5form.newTag;

        var tagToBeAdded = caseInsensitiveFind($scope.tags, newTag);
        if (!tagToBeAdded) tagToBeAdded = {"name": newTag};

        if (!caseInsensitiveFind(form.tags, tagToBeAdded)) {
            form.tags.push(tagToBeAdded);
            FormService.save(form, function () {
                FormService.get({id: form.id}, function (savedForm) {
                    angular.extend(form, savedForm);
                    if (!tagToBeAdded.id) {
                        $scope.tags = TagService.all();
                    }
                });
            });
        }

        html5form.newTag = "";
    };

    $scope.removeTag = function (form, tagToRemove) {
        angular.forEach(form.tags, function (tag, index) {
            if (tag.name === tagToRemove.name) {
                form.tags.splice(index, 1);
                FormService.save(form, function () {
                    FormService.get({id: form.id}, function (savedForm) {
                        angular.extend(form, savedForm);
                    });
                });
            }
        });
    }

    tagsPromise.then(setTags);
    formsPromise.then(setHTML5Forms);
    xFormsPromise.then(setXForms);


    var loadData = function () {
        return dataLoaded;
    };


    return {
        loadData: loadData
    };
}

