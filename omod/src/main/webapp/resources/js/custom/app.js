var html5formsModule = angular.module('html5forms', ['ngResource','ui.bootstrap']);

html5formsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/forms', {templateUrl: '../../moduleResources/html5forms/partials/forms.html', controller: FormCtrl}).
            otherwise({redirectTo: '/forms'});
    }]);

html5formsModule.factory('FormsService', function ($resource) {
    return $resource('forms.form', {}, {
        "all": {method: 'GET', isArray:true}
    });
});

html5formsModule.factory('FormService', function ($resource) {
    return $resource('form.form', {}, {
        "get": {method: 'GET', data : {id : '@id'}},
        "save": {method: 'POST', data : {id : '@id'}}
    });
});

html5formsModule.factory('XFormService', function ($resource) {
    return $resource('xforms.form', {}, {
        "all": {method: 'GET', isArray:true}
    });
});

html5formsModule.factory('TagService', function ($resource) {
    return $resource('tags.form', {}, {
        "all": {method: 'GET', isArray:true }
    });
});

