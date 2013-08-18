'use strict';
function XFormsCtrl($scope, FormService, XFormService, _, $q) {
    $scope.init = function () {
        $scope.selectedXForms = [];
        $scope.fetch();
    };

    $scope.fetch = function () {
        XFormService.all().then(function (result) {
            $scope.xForms = result.data;
        });
    };

    $scope.done = function () {
        var allSaved = $q.all(_.map($scope.selectedXForms, function (value) {
            return FormService.save({id: value});
        }));
    };

    $scope.hasXForms = function () {
        return !_.isEmpty($scope.xForms);
    };

    $scope.selectXForm = function (id) {
        var indexOfId = $scope.selectedXForms.indexOf(id);
        if (indexOfId >= 0) {
            $scope.selectedXForms.splice(indexOfId, 1);
        } else {
            $scope.selectedXForms.push(id);
        }
    };

}
