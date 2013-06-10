describe('Html5Forms controllers', function () {

    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, formService, tagService;

        beforeEach(inject(function (_FormService_, _TagService_, $rootScope, $controller) {
            tagService = _TagService_;
            formService = _FormService_;
            scope = $rootScope.$new();
            ctrl = $controller(FormCtrl, {$scope: scope});
        }));

        it('should assign tags to scope', function () {
            expect(scope.tags).toBeDefined();
        });

        it('should assign forms to scope', function () {
            expect(scope.forms).toBeDefined();
        });


        it('should assign a background color', function () {
            tagService.tagColor = function (id) {
                return id == 1 ? 'color' : 'invalid';
            };
            expect(scope.getTagStyle(1)['background-color']).toEqual('color');
        });
    });
});