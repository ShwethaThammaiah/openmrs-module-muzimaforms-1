'use strict';
var FormCtrl = function($scope, FormService, $q){
  $scope.init = function(){
      $scope.selectedFormId = FormService.getSelectedForm();
  };

  $scope.hasForm = function(){
    return $scope.selectedFormId !== undefined;
  };

  $scope.getForm = function(){
    return $scope.selectedFormId;
  };

};
