var html5formsModule = angular.module('html5forms', []);

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
        {id: '2', name: 'Encounter Form', description: 'Form for capturing patient encounters', selected: false, tags: [2, 5]},
        {id: '3', name: 'Adult Initial HIV Encounter Form', description: 'Form for capturing initial HIV encounter for an adult', selected: false, tags: [2,4,6,8]},
        {id: '4', name: 'PMTCT Ante-Natal Care Form', description: '', selected: false, tags: [1,5,2,7]},
        {id: '5', name: 'Pediatric Summary Form', description: 'Form for capturing pediatric summary', selected: false, tags: [9]},
        {id: '6', name: 'Outreach Adult Locator Form', description: '', selected: false, tags: [3,4,8]}
    ];

    return service;
});

html5formsModule.factory('TagService', function () {
    var service = {};

    service.tags = {
        1: { name: 'Registration'},
        2: { name: 'Patient'},
        3: { name: 'Encounter'},
        4: { name: 'Observation'},
        5: { name: 'Hiv'},
        6: { name: 'PMTCT'},
        7: { name: 'Ante-Natal'},
        8: { name: 'Pediatric'},
        9: { name: 'Locator'}
    };

    service.tagColor = function (tagId) {
        var tag = this.tags[tagId];
        if (!tag.color) {
            tag.color = generateRandomColor();
        }
        return tag.color;
    };

    function generateRandomColor(){
        return 'rgb(' + Math.floor(Math.random()*256) + ',' + Math.floor(Math.random()*256) + ',' + Math.floor(Math.random()*256) + ')';
    }

    return service;
});

