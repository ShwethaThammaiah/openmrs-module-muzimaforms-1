var muzimaformsModule = angular.module('muzimaforms', ['ui.bootstrap','muzimafilters']);

muzimaformsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/forms', {templateUrl: '../../moduleResources/muzimaforms/partials/forms.html'}).
            when('/form', {templateUrl: '../../moduleResources/muzimaforms/partials/form.html'}).
            otherwise({redirectTo: '/forms'});
    }]);

muzimaformsModule.factory('_', function () {
    return window._;
});


muzimaformsModule.factory('FormService', function ($http) {

    var get = function (id) {
        return $http.get('form.form?id=' + id);
    };
    var save = function (form) {
        return $http.post('form.form', form);
    };

    return {
        get: get,
        save: save
    }
});

muzimaformsModule.factory('FormsService', function ($http) {
    var all = function () {
        return $http.get('forms.form');
    };
    return {
        all: all
    };
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
        return $http.get('tags.form');
    };
    return {all: all};
});
