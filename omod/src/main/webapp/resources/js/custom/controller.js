function FormCtrl($scope, FormService, TagService) {
    $scope.tags = TagService.tags;
    $scope.forms = FormService.forms;

    $scope.selectedFormId = $scope.forms[0].id;

    $scope.getPreviewFormPath = function () {
        return '../../moduleResources/html5forms/html5forms/form-' + $scope.selectedFormId + '.html';
    };

    $scope.select = function (id) {
        $scope.selectedFormId = id;
    };

    var tagColor = function (tagId) {
        var tag = $scope.tags[tagId];
        if (!tag.color) {
            tag.color = 'rgb(' + Math.floor(Math.random()*200) + ',' + Math.floor(Math.random()*200) + ',' + Math.floor(Math.random()*200) + ')';
        }
        return tag.color;
    };

    $scope.tagStyle = function (tagId) {
        return  {'background-color': tagColor(tagId)};
    };

}

