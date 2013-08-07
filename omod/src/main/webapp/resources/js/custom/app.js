var muzimaformsModule = angular.module('muzimaforms', ['ui.bootstrap', 'muzimafilters']);

muzimaformsModule.
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/forms', {templateUrl: '../../moduleResources/muzimaforms/partials/formsNew.html'}).
            when('/form', {templateUrl: '../../moduleResources/muzimaforms/partials/form.html'}).
            otherwise({redirectTo: '/forms'});
    }]);

muzimaformsModule.factory('_', function () {
    return window._;
});


muzimaformsModule.factory('FormService', function ($http) {

    var get = function (id) {
        return $http.get('../../ws/rest/v1/muzimaforms/form/' + id + "?v=custom:(id,uuid,name,model,modelJson,html,tags)");
    };
    var save = function (form) {
        return $http.post('form.form', form);
    };
    var all = function () {
        return $http.get('../../ws/rest/v1/muzimaforms/form');
    };

    return {
        all: all,
        get: get,
        save: save
    }
});

muzimaformsModule.factory('XFormService', function ($http) {
    var all = function () {
        return $http.get('xforms.form');
    };
    return {
        all: all
    };
});


muzimaformsModule.factory('TagService', function ($http) {
    var all = function () {
        return $http.get('../../ws/rest/v1/muzimaforms/tag');
    };
    return {all: all};
});

muzimaformsModule.directive("fileInput", function () {
    return {
        restrict: "E",
        template: "<div>" +
            "<input class='blah' type='file' name='form[files][]'>" +
            "<div class='input-group'>" +
            "<input type='text' class='form-control' readonly placeholder='Choose form to import'" +
            "style='padding-left: 5px; padding-right: 5px'/>" +
            "<span class='input-group-btn'>" +
            "<button class='btn btn-default' type='button'>Choose XForm</button>" +
            "</span>" +
            "</div>" +
            "</div>",
        replace: false,
        transclude: false,
        link: function (scope, element, attrs) {
            var fileInputButton = element.find("input[type=file]");
            fileInputButton.css('display','none');
            var fileInputText = element.find("input[type=text]");
            var chooseFileButton = element.find(".input-group");
            chooseFileButton.click(function(){
                fileInputButton.click();
            });
            fileInputButton.on("change",function(){
                var file = fileInputButton.val().replace(/C:\\fakepath\\/i, '');
                fileInputText.val(file);
            });
        }
    }
});
