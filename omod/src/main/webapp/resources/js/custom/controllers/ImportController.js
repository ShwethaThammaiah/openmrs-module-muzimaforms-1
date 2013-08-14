'use strict';
function ImportCtrl($scope, FileUploadService) {
    $scope.showFileInput = false;

    $scope.validate = function (file) {
        FileUploadService.post({url: 'upload.form', file: file}).then(function (result) {
            $scope.validations = result.data;
        });
    };

    $scope.style = function(type)
    {
        return type === 'ERROR' ? 'alert-danger' : 'alert-info';
    }
}