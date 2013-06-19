'use strict';
function FormCtrl($scope, FormsService, FormService, XFormService, TagService) {
    $scope.tags = TagService.all();
    $scope.forms = FormsService.all();
    $scope.xForms = [];
    $scope.selectedXForms = [];
    $scope.editMode = true;
    $scope.importMode = false;
    $scope.tagColorMap = {};

    $scope.import = function () {
        $scope.importMode = true;
        $scope.xForms = XFormService.all();
    };

    //TODO: Use promises
    $scope.done = function () {
        angular.forEach($scope.selectedXForms, function (value) {
            FormService.save({id: value}, function () {
                FormService.get({id: value}, function (form) {
                    $scope.forms.push(form);
                });
            });
        });
        $scope.importMode = false;
    };

    $scope.cancelImport = function () {
        $scope.importMode = false;
    };

    $scope.hasXForms = function () {
        return $scope.xForms.length > 0;
    };

    $scope.hasForms = function () {
        return $scope.forms.length > 0;
    };

    $scope.xForms = function () {
        if (xForms && xForms.list) {
            return xForms.list;
        }
        return [];
    };

    $scope.getPreviewFormPath = function () {
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

    //TODO use a library function
    var contains = function (array, condition) {
        var conditionSatisfied = false;
        angular.forEach(array, function (value) {
            if (condition(value)) {
                conditionSatisfied = true;
            }
        });
        return conditionSatisfied;
    };

    $scope.saveTag = function (form, newTag) {
        if (newTag === "") {
            return;
        }
        var tagToBeAdded = {"name": newTag};
        angular.forEach($scope.tags, function (tag) {
            if (angular.lowercase(tag.name) === angular.lowercase(newTag)) {
                angular.extend(tagToBeAdded, tag);
            }
        });
        if (!contains(form.tags, function (tag) {
            return angular.lowercase(tag.name) === angular.lowercase(newTag);
        })) {
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
    };

    //TODO: Pull out a common function
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
}

