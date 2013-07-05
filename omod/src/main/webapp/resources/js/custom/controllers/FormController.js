'use strict';
var FormCtrl = function($scope, FormService, $q){
  $scope.form = '';
  $scope.init = function(){
      $scope.selectedFormId = FormService.getSelectedForm();
      loadForm($scope.selectedFormId).then(setForm);
  };

  var loadForm = function(formId){
    return FormService.get(formId);
  };

  var setForm = function(result){
    $scope.form = result.data.html;
  };

  $scope.hasForm = function(){
    return $scope.selectedFormId !== undefined;
  };
};
