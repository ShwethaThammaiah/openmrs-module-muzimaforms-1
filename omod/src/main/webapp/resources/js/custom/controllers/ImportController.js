'use strict';
function ImportCtrl($scope, FileUploadService, _, $location) {

    $scope.validate = function (file, isODK) {
        FileUploadService.post({url: isODK ? 'odk/validate.form' : 'javarosa/validate.form§', file: file, params: { isODK: isODK } }).then(function (result) {
            $scope.validations = result.data;
        });
    };

    $scope.upload = function (file, name, description, isODK) {
        FileUploadService.post({
            url: isODK ? 'odk/upload.form' : 'javarosa/upload.form', file: file, params: {
                name: name, description: description || ""
            }
        }).then(function () {
                $location.path("#/list/forms");
            });
    };

    $scope.style = function (type) {
        return type === 'ERROR' ? 'alert-danger' : 'alert-info';
    };

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
    };

    $scope.isValidated = function () {
        return ($scope.validations) ? true : false;

    };

    $scope.cancel = function () {
        $scope.validations = null;
        if ($scope.clearFile) $scope.clearFile();
    }
}