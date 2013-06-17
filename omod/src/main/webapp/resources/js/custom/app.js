var html5formsModule = angular.module('html5forms', ['ngResource','ui.bootstrap']);

html5formsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/forms', {templateUrl: '../../moduleResources/html5forms/partials/forms.html', controller: FormCtrl}).
            otherwise({redirectTo: '/forms'});
    }]);

html5formsModule.factory('FormService', function ($resource) {
    return $resource('forms.form', {}, {
        forms: {method: 'GET', isArray:true},
        save: {method: 'POST', data : {id : '@id'}}
    });
});

html5formsModule.factory('XFormService', function ($resource) {
    return $resource('xforms.form', {}, {
        xForms: {method: 'GET', isArray:true}
    });
});

html5formsModule.factory('TagService', function ($resource) {
    return $resource('tags.form', {}, {
        tags: {method: 'GET'}
    });
});

