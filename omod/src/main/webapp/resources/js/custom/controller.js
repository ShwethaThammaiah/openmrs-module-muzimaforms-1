
function FormCtrl($scope) {
    $scope.forms = [
        {name: 'Patient Registration Form', description: 'Form for registering patients', selected: false, tags: ['Registration', 'Patient']},
        {name: 'Encounter Form', description: 'Form for capturing patient encounters', selected: false, tags:['Patient', 'Encounter', 'Observation']}
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