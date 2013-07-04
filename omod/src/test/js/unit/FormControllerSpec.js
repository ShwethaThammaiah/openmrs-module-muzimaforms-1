describe('HTML5 form controllers', function() {
  beforeEach(module('html5forms'));
  describe('FormCtrl', function() {
    var scope, ctrl, q;
    var FormService = jasmine.createSpyObj('FormService', ['getSelectedForm']);

    beforeEach(inject(function($rootScope, $controller, $q, $timeout) {
      q = $q;
      scope = $rootScope.$new();
      ctrl = $controller(FormCtrl, {
        $scope: scope,
        FormService: FormService,
      });
    }));

    it('should initialise', function(){
      FormService.getSelectedForm.andReturn(34);
      scope.init();
      expect(FormService.getSelectedForm).toHaveBeenCalled();
      expect(scope.selectedFormId).toBe(34);      
    });        
  });
});
