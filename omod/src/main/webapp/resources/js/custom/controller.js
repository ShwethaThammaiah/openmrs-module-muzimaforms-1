'use strict';
function FormCtrl($scope, FormService, FormsService, XFormService, TagService, _, $q) {
    var tagsPromise = function () {
        return TagService.all();
    }
    var formsPromise = function () {
        return FormsService.all();
    }
    var xFormsPromise = function () {
        return XFormService.all();
    }

    var setTags = function (result) {
        $scope.tags = result.data;
    };
    var setXForms = function (result) {
        $scope.xForms = result.data;

    };

    var setHTML5Forms = function (result) {
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

        tagsPromise().then(setTags);
        formsPromise().then(setHTML5Forms);
    };

    $scope.import = function () {
        $scope.importMode = true;
        xFormsPromise().then(setXForms);
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
                        TagService.all().then(function (result) {
                            $scope.tags = result.data;
                        });
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
    };
}

