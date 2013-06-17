function FormCtrl($scope, FormService, XFormService, TagService) {
    $scope.tags = TagService.tags();
    $scope.forms = FormService.forms();
    $scope.xForms = [];
    $scope.selectedXForms = [];
    $scope.selectedFormId;
    $scope.editMode = true;
    $scope.importMode = false;

    $scope.import = function () {
        $scope.importMode = true;
        $scope.xForms = XFormService.xForms();
    };

    $scope.done = function () {
        angular.forEach($scope.selectedXForms, function (value) {
            console.log("Submitted form with id:" + value);
            FormService.save({id: value});
        });
        $scope.importMode = false;
        $scope.forms = FormService.forms();
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
        var tag = $scope.tags[tagId];
        if (!tag.color) {
            tag.color = 'rgb(' + Math.floor(Math.random() * 200) + ',' + Math.floor(Math.random() * 200) + ',' + Math.floor(Math.random() * 200) + ')';
        }
        return tag.color;
    };

    $scope.tagStyle = function (tagId) {
        return  {'background-color': tagColor(tagId)};
    };
}

