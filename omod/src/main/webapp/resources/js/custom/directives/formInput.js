muzimaformsModule.directive("formInput", function (FormUploadService) {
    return {
        restrict: "E",
        templateUrl: "../../moduleResources/muzimaforms/partials/formsImport.html",
        replace: false,
        transclude: true,
        controller: function ($scope, FormUploadService) {
            $scope.form = {formName: "", formDescription: ""};
            $scope.errorMsg = "";

            $scope.hasFileToUpload = function () {
                return $scope.fileToUpload != "";
            };

            $scope.submitForm = function () {
                $scope.errorMsg = "";
                if ($scope.fileToUpload == undefined) {
                    $scope.errorMsg = "Please select a file to upload"
                    return;
                }
                if ($scope.form.formName == "") {
                    $scope.errorMsg = "Please enter form name"
                    return;
                }

                FormUploadService.upload($scope.form, $scope.fileToUpload);
            };

            $scope.errorInImport = function () {
                return $scope.errorMsg != "";
            }
        },
        link: function (scope, element, attrs) {
            var fileInputButton = element.find("#hiddenFormImportButton");
            fileInputButton.css('display', 'none');
            var fileInputText = element.find("#inputFileName");
            var chooseFileButton = element.find("#chooseFileButton");
            var formNameInput = element.find("#inputFormName");
            var formDescriptionInput = element.find("#inputFormDescription");
            var form = element.find('#formToImport');

            chooseFileButton.click(function () {
                fileInputButton.click();
            });

            fileInputButton.on("change", function () {
                scope.fileToUpload = this.files[0];
                var fileName = scope.fileToUpload == undefined ? "Choose form to import" : scope.fileToUpload.name;
                fileInputText.val(fileName);
            });

            formNameInput.bind("change paste keyup", function () {
                scope.form.formName = formNameInput.val();
            });

            formDescriptionInput.bind("change paste keyup", function () {
                scope.form.formDescription = formDescriptionInput.val();
            });
        }
    }
});
