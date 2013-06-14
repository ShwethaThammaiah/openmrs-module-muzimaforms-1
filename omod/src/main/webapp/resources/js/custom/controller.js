function FormCtrl($scope, FormService, XFormService, TagService) {
    $scope.tags = TagService.tags();
    var forms = FormService.forms();
    var xForms = XFormService.xForms();
    var selectedXForms = [];
    var selectedFormId;

    $scope.editMode = true;
    $scope.importMode = false;

//    $scope.selectedFormId = $scope.forms[0].id;

    $scope.import = function(){
        $scope.importMode = true;
    };

    $scope.done = function(){
        $scope.importMode = false;
    };

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

    $scope.selectForm = function (id) {
        selectedFormId = id;
    };

    $scope.selectXForm = function (id) {
        var indexOfId = selectedXForms.indexOf(id);
        if(indexOfId >= 0){
            selectedXForms.splice(indexOfId, 1);
        }else{
            selectedXForms.push(id);
        }
    };

    $scope.activeXForm = function (id) {
        var indexOfId = selectedXForms.indexOf(id);
        return indexOfId >=0 ? 'active-xform' : undefined;
    };

    $scope.activeForm = function(id){
        return id === selectedFormId ? 'active-form' : undefined;
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

