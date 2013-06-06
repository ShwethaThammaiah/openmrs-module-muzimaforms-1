function TableCtrl($scope) {
    $scope.forms = [];

    $scope.addForm = function () {
        if ($scope.formName) {
            $scope.forms.push({name: $scope.formName, deleteThis: false});
            $scope.formName = '';
        }
    };

    $scope.deleteForm = function () {
        var oldFroms = $scope.forms;
        $scope.forms = [];
        angular.forEach(oldFroms, function (form) {
            if (!form.deleteThis)
                $scope.forms.push(form);
        });
    }
}