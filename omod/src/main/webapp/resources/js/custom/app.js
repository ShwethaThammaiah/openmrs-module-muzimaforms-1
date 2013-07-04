var html5formsModule = angular.module('html5forms', ['ui.bootstrap','html5filters']);

html5formsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/forms', {templateUrl: '../../moduleResources/html5forms/partials/forms.html'}).
            when('/form', {templateUrl: '../../moduleResources/html5forms/partials/form.html'}).
            otherwise({redirectTo: '/forms'});
    }]);

html5formsModule.factory('_', function () {
    return window._;
});


html5formsModule.factory('FormService', function ($http) {

    var selectedFormId;

    var selectForm = function(id){
        selectedFormId = id;
    };

    var getSelectedForm = function(){
        return selectedFormId;
    };

    var get = function (id) {
        return $http.get('form.form?id=' + id);
    };
    var save = function (form) {
        return $http.post('form.form', form);
    };

    return {
        get: get,
        save: save,
        selectForm: selectForm,
        getSelectedForm: getSelectedForm
    }
});

html5formsModule.factory('FormsService', function ($http) {
    var all = function () {
        return $http.get('forms.form');
    };
    return {
        all: all
    };
});

html5formsModule.factory('XFormService', function ($http) {
    var all = function () {
        return $http.get('xforms.form');
    };
    return {
        all: all
    };
});


html5formsModule.factory('TagService', function ($http) {
    var all = function () {
        return $http.get('tags.form');
    };
    return {all: all};
});
