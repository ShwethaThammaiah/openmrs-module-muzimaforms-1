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
        return $scope.isValidated() && !hasValidationMessages();
    };

    var hasValidationMessages = function () {
        return !_.isEmpty($scope.validations.list);
    };

    $scope.isInvalidXForm = function () {
        return $scope.isValidated() && hasValidationMessages();
    }

    $scope.isValidated = function () {
        return ($scope.validations) ? true : false;

    }

    $scope.cancel = function () {
        $scope.validations = null;
        if($scope.clearFile) $scope.clearFile();
    }
}