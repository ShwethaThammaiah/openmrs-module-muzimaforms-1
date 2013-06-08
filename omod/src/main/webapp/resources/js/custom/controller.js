
function FormCtrl($scope) {
    $scope.forms = [
        {id: '1', name: 'Patient Registration Form', description: 'Form for registering patients', selected: false, tags: ['Registration', 'Patient']},
        {id: '2', name: 'Encounter Form', description: 'Form for capturing patient encounters', selected: false, tags:['Patient', 'Encounter', 'Observation']},
        {id: '3', name: 'Adult Initial HIV Encounter Form', description: 'Form for capturing initial HIV encounter for an adult', selected: false, tags:['Patient','HIV' , 'Encounter', 'Observation']},
        {id: '4', name: 'PMTCT Ante-Natal Care Form', description: '', selected: false, tags:['Patient', 'PMTCT', 'Ante-Natal']},
        {id: '5', name: 'Pediatric Summary Form', description: 'Form for capturing pediatric summary', selected: false, tags:['Pediatric']},
        {id: '6', name: 'Outreach Adult Locator Form', description: '', selected: false, tags:['Outreach','Locator']}
    ];

    $scope.selectedFormId = $scope.forms[0].id;

    $scope.deleteForm = function () {
        var oldFroms = $scope.forms;
        $scope.forms = [];
        angular.forEach(oldFroms, function (form) {
            if (!form.selected)
                $scope.forms.push(form);
        });
    };

    $scope.getPreviewFormPath = function(){
        return '../../moduleResources/html5forms/html5forms/form-' + $scope.selectedFormId + '.html';
    };

    $scope.selectForm = function(id){
        $scope.selectedFormId = id;
    };

}