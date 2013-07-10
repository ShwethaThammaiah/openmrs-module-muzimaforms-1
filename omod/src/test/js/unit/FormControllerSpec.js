describe('HTML5 form controllers', function() {
  beforeEach(module('html5forms'));
  describe('FormCtrl', function() {
    var scope, ctrl, q, timeout;
    var FormService = jasmine.createSpyObj('FormService', ['get']);

    beforeEach(inject(function($rootScope, $controller, $q, $timeout) {
      q = $q;
      scope = $rootScope.$new();
      timeout = $timeout;
      ctrl = $controller(FormCtrl, {
        $scope: scope,
        FormService: FormService,
        $routeParams: {
          formId: 34
        }
      });
    }));

    var getPromise = function(data) {
      var deferred = q.defer();
      timeout(function() {
        deferred.resolve(data)
      });
      return deferred.promise;
    };


    it('should initialise', function() {
      FormService.get.andReturn(getPromise({
        data: {
          "id": 34,
          "name": "Patient Registration Form",
          "description": "Form for registering patients",
          "selected": false,
          "html": '<form></form>'
        }
      }));
      scope.init();
      expect(FormService.get).toHaveBeenCalled();
      expect(scope.selectedFormId).toBe(34);
      expect(scope.form).toBe('');
    });

    it('should load form', function() {
      FormService.get.andReturn(getPromise({
        data: {
          "id": 34,
          "name": "Patient Registration Form",
          "description": "Form for registering patients",
          "selected": false,
          "html": '<form></form>'
        }
      }));

      scope.init();
      timeout.flush();
      expect(scope.form).toBe('<form></form>');
    });

  });
});
