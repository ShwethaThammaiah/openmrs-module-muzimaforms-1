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

        it('should assign color to tag', function () {
            expect(scope.tags[2].color).toBeUndefined();
            var tagStyle = scope.tagStyle(2);
            expect(tagStyle['background-color']).toBeDefined();
            expect(scope.tags[2].color).toBeDefined();
        });

        it('should not assign new color to tag if color is already assigned', function () {
            scope.tags[2].color = '#333333';
            scope.tagStyle(2);
            expect(scope.tags[2].color).toEqual('#333333');
        });
    });
});