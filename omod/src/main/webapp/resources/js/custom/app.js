var muzimaformsModule = angular.module('muzimaforms', ['ui.bootstrap', 'muzimafilters']);

muzimaformsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/list/forms', {templateUrl: '../../moduleResources/muzimaforms/partials/list/forms.html'}).
            when('/list/xforms', {templateUrl: '../../moduleResources/muzimaforms/partials/list/xforms.html'}).
            when('/import/xforms', {templateUrl: '../../moduleResources/muzimaforms/partials/import/xforms.html'}).
            otherwise({redirectTo: '/list/forms'});
    }]);

muzimaformsModule.factory('_', function () {
    return window._;
});


muzimaformsModule.factory('FormService', function ($http) {

    var get = function (id) {
        return $http.get('../../ws/rest/v1/muzimaforms/form/' + id + "?v=custom:(id,uuid,name,model,modelJson,html,tags)");
    };
    var save = function (form) {
        return $http.post('form.form', form);
    };
    var all = function () {
        return $http.get('../../ws/rest/v1/muzimaforms/form');
    };

    return {
        all: all,
        get: get,
        save: save
    }
});

muzimaformsModule.factory('XFormService', function ($http) {
    var all = function () {
        return $http.get('xforms.form');
    };
    return {
        all: all
    };
});


muzimaformsModule.factory('TagService', function ($http) {
    var all = function () {
        return $http.get('../../ws/rest/v1/muzimaforms/tag');
    };
    return {all: all};
});

muzimaformsModule.factory('FormUploadService', function ($http) {
    var upload = function (form, file) {
        return $http({
            method: 'POST',
            url: "upload.form",
            headers: { 'Content-Type': false },
            transformRequest: function (data) {
                var formData = new FormData();
                formData.append("formMetadata", angular.toJson(data.formMetadata));
                formData.append("file", data.file);
                return formData;
            },
            data: {formMetadata: form, file: file}
        })
    };
    return {
        upload: upload
    };
});