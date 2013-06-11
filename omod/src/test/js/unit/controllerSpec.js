describe('Html5Forms controllers', function () {

    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, tagService, httpBackend;
        beforeEach(inject(function (_$httpBackend_, _TagService_, $rootScope, $controller) {
            httpBackend = _$httpBackend_;

            httpBackend.expectGET('tags.form').
                respond({
                    1: { name: 'Registration'},
                    2: { name: 'Patient'},
                    3: { name: 'Encounter'},
                    4: { name: 'Observation'},
                    5: { name: 'Hiv'},
                    6: { name: 'PMTCT'},
                    7: { name: 'Ante-Natal'},
                    8: { name: 'Pediatric'},
                    9: { name: 'Locator'}
                });

            tagService = _TagService_;
            scope = $rootScope.$new();
            ctrl = $controller(FormCtrl, {$scope: scope});

            httpBackend.flush();
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