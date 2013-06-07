
angular.module('html5forms', []).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/formsList', {templateUrl: '../../moduleResources/html5forms/partials/forms-list.html', controller: FormCtrl}).
            when('/preview', {templateUrl: '../../moduleResources/html5forms/partials/form-preview.html'}).
            otherwise({redirectTo: '/formsList'});
    }]);


angular.module('preview', []).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/preview', {templateUrl: '../../moduleResources/html5forms/partials/form-preview.html', controller: FormCtrl}).
            otherwise({redirectTo: '/preview'});
    }]);

