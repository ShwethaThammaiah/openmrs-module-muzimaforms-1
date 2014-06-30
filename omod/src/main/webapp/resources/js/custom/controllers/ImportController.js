'use strict';
function ImportCtrl($scope, FileUploadService, _, $location) {

    var showErrorMessage = function(content, cl, time) {
        $('<div/>')
            .addClass('alert')
            .addClass('alert-error')
            .hide()
            .fadeIn('slow')
            .delay(time)
            .appendTo('#error-alert')
            .text(content);
    };

    $scope.validate = function (file,formType) {

        if (formType == 'html') {
            $scope.validations = { list: []};
        } else {
            FileUploadService.post({url: formType == 'odk' ? 'odk/validate.form' : 'javarosa/validate.form§', file: file, params: { isODK: formType == 'odk' } }).then(function (result) {
                $scope.validations = result.data;
            });
        }
    };

    $scope.upload = function (file, name, discriminator, description, formType) {
        var match = name.match('[\\s\\w]*');
        if(match == null || match[0] != name){
            showErrorMessage("The form name shouldn't contain any special characters");
            return;
        }
        FileUploadService.post({
            url: $scope.getURL(formType), file: file, params: {
                name: name, discriminator: discriminator,  description: description || ""
            }
        }).success(function () {
                $location.path("#/list/forms");
            }).error(function () {
                showErrorMessage("The form name already exists !! Please use some other name.");
            });
    };

    $scope.getURL = function (formType) {
        if (formType == 'html') return 'html/upload.form';
        if (formType == 'odk') return 'odk/upload.form';
        return 'javarosa/upload.form';
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