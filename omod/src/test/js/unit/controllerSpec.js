describe('Html5Forms controllers', function () {

    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, tagService, formService, httpBackend;
        beforeEach(inject(function (_$httpBackend_, _TagService_,_FormService_, $rootScope, $controller) {
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

            httpBackend.expectGET('forms.form').
                respond({ list : [{"id":"1","name":"Patient Registration Form","description":"Form for registering patients","selected":false,"tags":[1,2]},
                    {"id":"2","name":"PMTCT Ante-Natal Care Form","description":"","selected":false,"tags":[1,5,2,7]},
                    {"id":"3","name":"Encounter Form","description":"Form for capturing patient encounters","selected":false,"tags":[2,5]},
                    {"id":"4","name":"Adult Initial HIV Encounter Form","description":"Form for capturing initial HIV encounter for an adult","selected":false,"tags":[2,4,6,8]},
                    {"id":"5","name":"Pediatric Summary Form","description":"Form for capturing pediatric summary","selected":false,"tags":[9]},
                    {"id":"6","name":"Outreach Adult Locator Form","description":"","selected":false,"tags":[3,4,8]}]});

            tagService = _TagService_;
            formService = _FormService_;
            scope = $rootScope.$new();
            ctrl = $controller(FormCtrl, {$scope: scope});

            httpBackend.flush();
        }));

        it('should assign tags to scope', function () {
            expect(scope.tags[1]).toBeDefined();
        });

        it('should assign forms to scope', function () {
            expect(scope.hasForms()).toBe(true);
            expect(scope.hasForms()).toBe(true);
        });

        it('should assign color to empty tag', function () {
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