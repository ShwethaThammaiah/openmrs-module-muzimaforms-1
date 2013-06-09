function FormCtrl($scope) {
    $scope.labels = [
        {name: 'Registration', color: '#b94a48'},
        {name: 'Patient', color: '#f89406'},
        {name: 'Encounter', color: '#468847'},
        {name: 'Observation', color: '#33B5E5'},
        {name: 'HIV', color: '#f84600'},
        {name: 'PMTCT', color: '#207178'},
        {name: 'Ante-Natal', color: '#1693a5'},
        {name: 'Pediatric', color: '#c19652'},
        {name: 'Outreach', color: '#7faf1b'},
        {name: 'Locator', color: '#01d2ff'}
    ];

    $scope.forms = [
        {id: '1', name: 'Patient Registration Form', description: 'Form for registering patients', selected: false, tags: [$scope.labels[0], $scope.labels[1]]},
        {id: '2', name: 'Encounter Form', description: 'Form for capturing patient encounters', selected: false, tags: [$scope.labels[1], $scope.labels[2], $scope.labels[3]]},
        {id: '3', name: 'Adult Initial HIV Encounter Form', description: 'Form for capturing initial HIV encounter for an adult', selected: false, tags: [$scope.labels[1], $scope.labels[4] , $scope.labels[2], $scope.labels[3]]},
        {id: '4', name: 'PMTCT Ante-Natal Care Form', description: '', selected: false, tags: [$scope.labels[1], $scope.labels[5], $scope.labels[6]]},
        {id: '5', name: 'Pediatric Summary Form', description: 'Form for capturing pediatric summary', selected: false, tags: [$scope.labels[7]]},
        {id: '6', name: 'Outreach Adult Locator Form', description: '', selected: false, tags: [$scope.labels[8], $scope.labels[9]]}
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

    $scope.getPreviewFormPath = function () {
        return '../../moduleResources/html5forms/html5forms/form-' + $scope.selectedFormId + '.html';
    };

    $scope.selectForm = function (id) {
        $scope.selectedFormId = id;
    };

    $scope.getTagStyle = function (tag) {
        tagStyle = {'background-color': "#333333"};
        if (tag.color) {
            tagStyle['background-color'] = tag.color;
        }
        return tagStyle;
    };

}