describe('Html5Forms controllers', function () {

    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, httpBackend;
        var expextGetTags = function () {
            httpBackend.expectGET('tags.form').
                respond([
                    {"id": 1, "name":"Registration"},
                    {"id": 2, "name":"Patient"},
                    {"id": 3, "name":"PMTCT"}
                ]);
        };
        var expectGetForms = function () {
            httpBackend.expectGET("forms.form").
                respond(
                    [
                        {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false,"tags": [{"id": 1, "name":"Registration"}, {"id": 2, "name":"Patient"}]  },
                        {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false, "tags": [{"id": 1, "name":"Registration"}] }
                    ]);
        };

        var expectGetXForms = function () {
            httpBackend.expectGET('xforms.form').
                respond(
                    [
                        {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false},
                        {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false},
                        {"id": 3, "name": "Outreach Adult Locator Form", "description": "", "selected": false}
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
            expect(scope.tags[0].id).toBe(1);
            expect(scope.tags[0].name).toBe("Registration");
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

//        it('should post selected xform ids when clicked on done', function () {
//            httpBackend.expectPOST('forms.form', {id: "2").respond(201);
//            httpBackend.expectPOST('forms.form', {id: "3"}).respond(201);
//            scope.selectXForm('2');
//            scope.selectXForm('3');
//            scope.importMode = true;
//            expectGetForms();
//            scope.done();
//            httpBackend.flush();
//            expect(scope.importMode).toBe(false);
//        });

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

        it('should return tag names', function () {
            var tagNames = scope.tagNames();
            expect(tagNames[0]).toBe('Registration');
            expect(tagNames[1]).toBe('Patient');
            expect(tagNames[2]).toBe('PMTCT');
        });

        it('should save a non existing tag', function () {
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            scope.saveTag(1, "Encounter");
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            expect(scope.forms[0].tags[2]).toEqual({"name":"Encounter"});
        });

        it('should save an existing tag', function () {
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            scope.saveTag(1,"PMTCT");
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            expect(scope.forms[0].tags[2]).toEqual({"id": 3, "name":"PMTCT"});
        });

        it('should ignore an already added tag and should ignore case', function () {
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            scope.saveTag(1,"registration");
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            expect(scope.forms[0].tags.length).toBe(2);
        });

        it('should not add empty tag', function () {
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            scope.saveTag(1,"");
            expect(scope.forms[0].tags[0]).toEqual({"id": 1, "name":"Registration"});
            expect(scope.forms[0].tags[1]).toEqual({"id": 2, "name":"Patient"});
            expect(scope.forms[0].tags.length).toBe(2);
        });
    });
});