var html5formsModule = angular.module('html5forms', ['ngResource','ui.bootstrap']);

html5formsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/formsList', {templateUrl: '../../moduleResources/html5forms/partials/forms.html', controller: FormCtrl}).
            when('/editForm/:formId', {templateUrl: '../../moduleResources/html5forms/partials/form-edit.html'}).
            otherwise({redirectTo: '/formsList'});
    }]);

html5formsModule.factory('FormService', function () {
    var service = {};

    service.forms = [
        {id: '1', name: 'Patient Registration Form', description: 'Form for registering patients', selected: false, tags: [1, 2]},
        {id: '2', name: 'PMTCT Ante-Natal Care Form', description: '', selected: false, tags: [1, 5, 2, 7]},
        {id: '3', name: 'Encounter Form', description: 'Form for capturing patient encounters', selected: false, tags: [2, 5]},
        {id: '4', name: 'Adult Initial HIV Encounter Form', description: 'Form for capturing initial HIV encounter for an adult', selected: false, tags: [2, 4, 6, 8]},
        {id: '5', name: 'Pediatric Summary Form', description: 'Form for capturing pediatric summary', selected: false, tags: [9]},
        {id: '6', name: 'Outreach Adult Locator Form', description: '', selected: false, tags: [3, 4, 8]}
    ];

    return service;
});

html5formsModule.factory('TagService', function ($resource) {
    return $resource('tags.form', {}, {
        tags: {method: 'GET'}
    });
});

