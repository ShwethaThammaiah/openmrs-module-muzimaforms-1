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
        return $http.get('../../ws/rest/v1/muzimaforms/form/' + id + "?v=custom:(id,uuid,name,model,modelJson,html,tags,version)");
    };
    var save = function (form) {
        return $http.post('form.form', form);
    };
    var all = function () {
        return $http.get('../../ws/rest/v1/muzimaforms/form', {cache: false});
    };

    var getForms = function() {
        return $http.get('../../ws/rest/v1/form?v=custom:(name,uuid,version,description)');
    };

    return {
        all: all,
        get: get,
        save: save,
        getForms: getForms
    }
});

muzimaformsModule.factory('XFormService', function ($http) {
    var all = function () {
        return $http.get('xforms.form');
    };

    var save = function (data) {
        return $http({url: 'xforms.form', method: 'POST', params: data});
    };

    return {
        all: all,
        save: save
    };
});


muzimaformsModule.factory('TagService', function ($http) {
    var all = function () {
        return $http.get('../../ws/rest/v1/muzimaforms/tag');
    };
    return {all: all};
});

muzimaformsModule.factory('FileUploadService', function ($http) {
    return {
        post: function (options) {
            return $http({
                method: 'POST',
                url: options.url,
                headers: { 'Content-Type': false },
                transformRequest: function (data) {
                    var formData = new FormData();
                    angular.forEach(data.params, function (key, value) {
                        formData.append(value, key);
                    });
                    formData.append("file", data.file);
                    return formData;
                },
                data: {file: options.file, params: options.params}
            })
        }
    };
});