
function FormCtrl($scope) {
    $scope.forms = [
        {name: 'Patient Registration Form', description: 'Form for registering patients', selected: false, tags: ['Registration', 'Patient']},
        {name: 'Encounter Form', description: 'Form for capturing patient encounters', selected: false, tags:['Patient', 'Encounter', 'Observation']},
        {name: 'Adult Initial HIV Encounter Form', description: 'Form for capturing initial HIV encounter for an adult', selected: false, tags:['Patient','HIV' , 'Encounter', 'Observation']},
        {name: 'PMTCT Ante-Natal Care Form', description: '', selected: false, tags:['Patient', 'PMTCT', 'Ante-Natal']},
        {name: 'Pediatric Summary Form', description: 'Form for capturing pediatric summary', selected: false, tags:['Pediatric']},
        {name: 'Outreach Adult Locator Form', description: '', selected: false, tags:['Outreach','Locator']}
    ];

    $scope.deleteForm = function () {
        var oldFroms = $scope.forms;
        $scope.forms = [];
        angular.forEach(oldFroms, function (form) {
            if (!form.selected)
                $scope.forms.push(form);
        });
    }
}