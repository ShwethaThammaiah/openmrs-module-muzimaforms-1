
angular.module('html5forms', []).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/formsList', {templateUrl: '../../moduleResources/html5forms/partials/forms.html', controller: FormCtrl}).
            when('/editForm/:formId', {templateUrl: '../../moduleResources/html5forms/partials/form-edit.html'}).
            otherwise({redirectTo: '/formsList'});
    }]);


