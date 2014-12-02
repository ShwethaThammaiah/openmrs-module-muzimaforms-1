 'use strict';
function FormViewCtrl($location, $scope, $window,FileUploadService, FormService,TagService, _,$routeParams) {

       $scope.init = function (){
           $scope.editHtml = false;
           $scope.tagColorMap = {};
           $scope.form_uuid =  $routeParams.muzimaform_uuid
           $scope.title = 'Click here to update new Form';
           $scope.xformToUpload = "";
           $scope.htmlFormToUpload = "";
           $scope.fetchingForms = false;

           getForm($scope.form_uuid).then(setForm);

       };

       var getForm = function (formUuid) {
           $scope.fetchingForms = true;
           return FormService.get(formUuid);
       };

       var setForm = function (result) {
           $scope.fetchingForms = false;
           $scope.forms = result.data;
           console.log($scope.forms);
           $scope.muzimaforms = {form: result.data,
                           newTag: ""}
       };

       $scope.hasForms = function () {
           return !_.isEmpty($scope.muzimaforms);
       };

       var getTags = function () {
               return TagService.all();
       };


       var setTags = function (result) {
               $scope.tags = result.data.results;
       };


        $scope.getUpdateURL = function (formType) {
                       if (formType == 'html') return 'html/update.form';
                       if (formType == 'odk') return 'odk/update.form';
                       return 'javarosa/update.form';
        };

       $scope.update = function (file, form,formType) {

                   var uuid = "";
                   if (form != null && form !== 'undefined') {
                       uuid = form.uuid;
                   }

                   FileUploadService.post({
                       url: $scope.getUpdateURL(formType), file: file, params: {
                           form: uuid
                       }
                   }).success(function () {
                       $window.location.reload();
                   }).error(function () {
                       showErrorMessage("Unable to update form with uuid " + uuid );
                   });
       };

   $scope.validate = function (file, formType) {

           if (formType == 'html') {
               $scope.validations = { list: []};
           } else {
               FileUploadService.post({
                   url: formType == 'odk' ? 'odk/validate.form' : 'javarosa/validate.form§',
                   file: file,
                   params: { isODK: formType == 'odk'
                   }
               }).then(function (result) {
                   $scope.validations = result.data;
               });
           }
       };

       var tagColor = function (tagId) {
               var tag = $scope.tagColorMap[tagId];
               if (!tag) {
                   $scope.tagColorMap[tagId] = {};
                   $scope.tagColorMap[tagId].color =
                       'rgb(' + (50 + Math.floor(Math.random() * 150))
                       + ',' + (50 + Math.floor(Math.random() * 150))
                       + ',' + (50 + Math.floor(Math.random() * 150)) + ')';
               }
               return $scope.tagColorMap[tagId].color;
           };

           $scope.tagStyle = function (tagId) {
               return  {'background-color': tagColor(tagId)};
           };

           $scope.tagNames = function () {
               var tagNames = [];
               angular.forEach($scope.tags, function (tag) {
                   tagNames.push(tag.name);
               });
               return tagNames;
           };

$scope.saveTag = function (muzimaform) {
        if (muzimaform.newTag === "") return;
        var form = muzimaform.form;
        var newTag = muzimaform.newTag;
        var tagToBeAdded = caseInsensitiveFind($scope.tags, newTag) || {"name": newTag};

        muzimaform.newTag = "";
        if (caseInsensitiveFind(form.tags, tagToBeAdded.name)) return;
        form.tags.push(tagToBeAdded);
        FormService.save(form)
            .then(function (result) {
                return FormService.get(form.uuid);
            })
            .then(function (savedForm) {
                angular.extend(form, savedForm.data);
                if (!tagToBeAdded.id)
                    getTags().then(setTags);
            });

    };

    $scope.removeTag = function (form, tagToRemove) {
        angular.forEach(form.tags, function (tag, index) {
            if (tag.name === tagToRemove.name) {
                form.tags.splice(index, 1);
                FormService.save(form)
                    .then(function (result) {
                        return FormService.get(form.uuid);
                    })
                    .then(function (savedForm) {
                        angular.extend(form, savedForm.data);
                    });
            }
        });
    };

    $scope.showFormPreview = function (formHTML, formModel, formJSON) {
            var previewWindow = $window.open("../../moduleResources/muzimaforms/preview/enketo/template.html");

            previewWindow.formHTML = formHTML;
            previewWindow.formModel = formModel;
            previewWindow.formJSON = formJSON;
        };

    var caseInsensitiveFind = function (tags, newTag) {
            return _.find(tags, function (tag) {
                return angular.lowercase(tag.name) === angular.lowercase(newTag);
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

       $scope.cancelUpdate = function () {
           $location.path('/list/forms');
       }
   }