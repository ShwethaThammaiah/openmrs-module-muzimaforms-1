function FormCtrl($scope, FormService, XFormService, TagService) {
    $scope.tags = TagService.tags();
    var forms = { list : [{"id":"1","name":"Patient Registration Form","description":"Form for registering patients","selected":false,"tags":[1,2]},
        {"id":"2","name":"PMTCT Ante-Natal Care Form","description":"","selected":false,"tags":[1,5,2,7]},
        {"id":"3","name":"Encounter Form","description":"Form for capturing patient encounters","selected":false,"tags":[2,5]},
        {"id":"4","name":"Adult Initial HIV Encounter Form","description":"Form for capturing initial HIV encounter for an adult","selected":false,"tags":[2,4,6,8]},
        {"id":"5","name":"Pediatric Summary Form","description":"Form for capturing pediatric summary","selected":false,"tags":[9]},
        {"id":"6","name":"Outreach Adult Locator Form","description":"","selected":false,"tags":[3,4,8]}]};
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

    $scope.cancelImport = function(){
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

