var html5formsModule = angular.module('html5forms', ['ngResource','ui.bootstrap']);

html5formsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/formsList', {templateUrl: '../../moduleResources/html5forms/partials/forms.html', controller: FormCtrl}).
            when('/editForm/:formId', {templateUrl: '../../moduleResources/html5forms/partials/form-edit.html'}).
            otherwise({redirectTo: '/formsList'});
    }]);

html5formsModule.factory('FormService', function ($resource) {
    return $resource('forms.form', {}, {
        forms: {method: 'GET', isArray:true}
    });
});

html5formsModule.factory('TagService', function ($resource) {
    return $resource('tags.form', {}, {
        tags: {method: 'GET'}
    });
});

