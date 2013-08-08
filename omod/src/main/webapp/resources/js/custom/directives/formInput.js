muzimaformsModule.directive("formInput", function () {
    return {
        restrict: "E",
        templateUrl: "../../moduleResources/muzimaforms/partials/formsImport.html",
        replace: false,
        transclude: true,
        controller: function($scope, $attrs) {
            $scope.fileToUpload = "";
            $scope.formName = "";
            $scope.formDescription = "";
            $scope.errorMsg = "";

            $scope.hasFileToUpload = function(){
                return $scope.fileToUpload != "";
            };

            $scope.submitForm = function(){
                $scope.errorMsg = "";
                if($scope.fileToUpload == ""){
                    $scope.errorMsg = "Please select a file to upload"
                    return;
                }
                if($scope.formName == ""){
                    $scope.errorMsg = "Please enter form name"
                    return;
                }

                console.log("import button clicked");
            };

            $scope.errorInImport = function(){
                return $scope.errorMsg != "";
            }
        },
        link: function (scope, element, attrs) {
            var fileInputButton = element.find("#hiddenFormImportButton");
            fileInputButton.css('display','none');
            var fileInputText = element.find("#inputFileName");
            var chooseFileButton = element.find("#chooseFileButton");
            var formNameInput = element.find("#inputFormName");
            var formDescriptionInput = element.find("#inputFormDescription");

            chooseFileButton.click(function(){
                fileInputButton.click();
            });

            fileInputButton.on("change",function(){
                scope.fileToUpload = fileInputButton.val();
                var file = fileInputButton.val().replace(/C:\\fakepath\\/i, '');
                fileInputText.val(file);
            });

            formNameInput.bind("change paste keyup", function(){
                scope.formName = formNameInput.val();
            });

            formDescriptionInput.bind("change paste keyup", function(){
                scope.formDescription = formDescriptionInput.val();
            });
        }
    }
});
