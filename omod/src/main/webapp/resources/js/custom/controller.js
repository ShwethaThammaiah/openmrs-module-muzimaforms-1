function FormCtrl($scope, FormService, TagService) {
    $scope.tags = TagService.tags;
    $scope.forms = FormService.forms;

//    $scope.selectedFormId = $scope.forms[0].id;

//    $scope.getPreviewFormPath = function () {
//        return '../../moduleResources/html5forms/html5forms/form-' + $scope.selectedFormId + '.html';
//    };

//    $scope.selectForm = function (id) {
//        $scope.selectedFormId = id;
//    };
//
    $scope.getTagStyle = function (tagId) {
        console.log(TagService.tagColor(tagId));
        return  {'background-color': TagService.tagColor(tagId)};
    };

}

