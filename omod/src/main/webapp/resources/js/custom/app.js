var html5formsModule = angular.module('html5forms', ['ngResource','ui.bootstrap']);

html5formsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/formsList', {templateUrl: '../../moduleResources/html5forms/partials/forms.html', controller: FormCtrl}).
            otherwise({redirectTo: '/formsList'});
    }]);

html5formsModule.factory('FormService', function ($resource) {
    return $resource('forms.form', {}, {
        forms: {method: 'GET'}
    });
});

html5formsModule.factory('TagService', function ($resource) {
    return $resource('tags.form', {}, {
        tags: {method: 'GET'}
    });
});

