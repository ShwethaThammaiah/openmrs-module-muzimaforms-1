'use strict';
function ImportCtrl($scope, FileUploadService, _) {
    $scope.validate = function (file) {
        FileUploadService.post({url: 'upload.form', file: file}).then(function (result) {
            $scope.validations = result.data;
        });
    };

    $scope.style = function (type) {
        return type === 'ERROR' ? 'alert-danger' : 'alert-info';
    }

    $scope.hasFile = function () {
        return ($scope.file) ? true : false;
    };

    $scope.isValidXForm = function () {
        if (!$scope.validations) return false;
        return !$scope.validations.list ? false : _.isEmpty($scope.validations.list);
    }

    $scope.cancel = function () {
        $scope.validations = null;
    }
}