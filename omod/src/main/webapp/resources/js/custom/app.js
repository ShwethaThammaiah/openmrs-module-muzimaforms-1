var muzimaformsModule = angular.module('muzimaforms', ['ui.bootstrap', 'muzimafilters']);

muzimaformsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/forms', {templateUrl: '../../moduleResources/muzimaforms/partials/formsNew.html'}).
            when('/form', {templateUrl: '../../moduleResources/muzimaforms/partials/form.html'}).
            otherwise({redirectTo: '/forms'});
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
