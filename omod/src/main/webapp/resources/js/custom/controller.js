function FormCtrl($scope, FormService, XFormService, TagService) {
    $scope.tags = TagService.tags();
    var forms = FormService.forms();
    var xForms = XFormService.xForms();

    $scope.editMode = true;
    $scope.importMode = false;

//    $scope.selectedFormId = $scope.forms[0].id;

    $scope.hasForms = function(){
        return ($scope.forms().length > 0);
    };

    $scope.hasXForms = function(){
        return ($scope.xForms().length > 0);
    };

    $scope.forms =  function(){
        if(forms && forms.list){
            return forms.list;
        }
        return [];
    };

    $scope.xForms =  function(){
        if(xForms && xForms.list){
            return xForms.list;
        }
        return [];
    };

    $scope.getPreviewFormPath = function () {
        return '../../moduleResources/html5forms/html5forms/form-' + $scope.selectedFormId + '.html';
    };

    $scope.select = function (id) {
        $scope.selectedFormId = id;
    };

    $scope.activeClass = function(id){
        return id === $scope.selectedFormId ? 'active-form' : undefined;
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

