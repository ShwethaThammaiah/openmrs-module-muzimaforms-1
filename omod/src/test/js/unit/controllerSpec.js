describe('Html5Forms controllers', function () {

    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, httpBackend;
        var expextGetTags = function () {
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
        };
        var expectGetForms = function () {
            httpBackend.expectGET("forms.form").
                respond(
                    [
                        {
                            "id": "1",
                            "name": "Patient Registration Form",
                            "description": "Form for registering patients",
                            "selected": false
                        },
                        {
                            "id": "2",
                            "name": "PMTCT Ante-Natal Care Form",
                            "description": "",
                            "selected": false,
                            "tags": [1, 5, 2, 7]
                        },
                        {"id": "3", "name": "Encounter Form", "description": "Form for capturing patient encounters", "selected": false, "tags": [2, 5]},
                        {"id": "4", "name": "Adult Initial HIV Encounter Form", "description": "Form for capturing initial HIV encounter for an adult", "selected": false, "tags": [2, 4, 6, 8]},
                        {"id": "5", "name": "Pediatric Summary Form", "description": "Form for capturing pediatric summary", "selected": false, "tags": [9]},
                        {"id": "6", "name": "Outreach Adult Locator Form", "description": "", "selected": false, "tags": [3, 4, 8]}
                    ]);
        };

        var expectGetXForms = function () {
            httpBackend.expectGET('xforms.form').
                respond(
                [
                    {"id": "1", "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false},
                    {"id": "2", "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false},
                    {"id": "3", "name": "Outreach Adult Locator Form", "description": "", "selected": false}
                ]
            );
        };


        beforeEach(inject(function (_$httpBackend_, $rootScope, $controller) {
            httpBackend = _$httpBackend_;
            expextGetTags();
            expectGetForms();
            scope = $rootScope.$new();
            ctrl = $controller(FormCtrl, {$scope: scope});
            httpBackend.flush();

        }));

        it('should assign tags to scope', function () {
            expect(scope.tags[1]).toBeDefined();

        });

        it('should assign forms to scope', function () {
            expect(scope.hasForms()).toBe(true);
        });

        it('should assign xforms to an empty array', function () {
            expect(scope.hasXForms()).toBe(false);
        });

        it('should import forms and toogle imortMode', function () {
            expectGetXForms();
            expect(scope.importMode).toBe(false);
            scope.import();
            httpBackend.flush();
            expect(scope.importMode).toBe(true);
        });

        it('should post selected xform ids when clicked on done', function () {
            var form1 = {id: "2"};
            var form2 = {id: "3"};
            httpBackend.expectPOST('forms.form', form1).respond(201);
            httpBackend.expectPOST('forms.form', form2).respond(201);
            scope.selectXForm('2');
            scope.selectXForm('3');
            scope.importMode = true;
            expectGetForms();
            scope.done();
            httpBackend.flush();
            expect(scope.importMode).toBe(false);
        });

        it('cancel should toggle imortMode', function () {
            scope.importMode = true;
            scope.cancelImport();
            expect(scope.importMode).toBe(false);
        });

        it('should assign color to active form', function () {
            expect(scope.activeForm(1)).toBeUndefined();
            scope.selectForm(1);
            expect(scope.activeForm(1)).toBe('active-form');
        });

        it('should assign color to active xForm', function () {
            expect(scope.activeXForm(1)).toBeUndefined();
            scope.selectXForm(1);
            expect(scope.activeXForm(1)).toBe('active-xform');
            scope.selectXForm(1);
            expect(scope.activeXForm(1)).toBeUndefined();
        });

        it('should add selected Xform to the selected XForms list', function () {
            expect(scope);
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