describe('Html5Forms controllers', function () {

    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, httpBackend;
        var expectGetTags = function () {
            httpBackend.expectGET('tags.form').
                respond([
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"},
                    {"id": 3, "name": "PMTCT"}
                ]);
        };
        var expectGetForms = function () {
            httpBackend.expectGET("forms.form").
                respond(
                    [
                        {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                            {"id": 1, "name": "Registration"},
                            {"id": 2, "name": "Patient"}
                        ]  },
                        {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false, "tags": [
                            {"id": 1, "name": "Registration"}
                        ] }
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
            expectGetTags();
            expectGetForms();
            expectGetXForms();
            scope = $rootScope.$new();
            ctrl = $controller(FormCtrl, {$scope: scope});
        }));

        it('should assign tags to scope', function () {
            ctrl.loadData().then(function (data) {

                expect(scope.tags[0].id).toBe(1);
                expect(scope.tags[0].name).toBe("Registration");
            });
        });

        it('should assign forms to scope', function () {
            ctrl.loadData().then(function (data) {
                expect(scope.hasForms()).toBe(true);
            });
        });

        it('should assign xforms to an empty array', function () {
            expect(scope.hasXForms()).toBe(false);
        });

        it('should import forms and toogle imortMode', function () {
            expect(scope.importMode).toBe(false);
            scope.import();
            expect(scope.importMode).toBe(true);
        });

        it('should post selected xform ids when clicked on done', function () {
            ctrl.loadData().then(function (data) {
                httpBackend.expectPOST('form.form', {'id': '4'}).respond(200);
                httpBackend.expectPOST('form.form', {'id': '5'}).respond(200);
                httpBackend.expectGET('form.form?id=4').
                    respond(
                    {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients"}
                )

                httpBackend.expectGET('form.form?id=5').
                    respond(
                    {"id": 2, "name": "Patient Registration Form", "description": "Form for registering patients"}
                );

                scope.selectXForm('4');
                scope.selectXForm('5');
                scope.importMode = true;

                scope.done();
                httpBackend.flush();
                expect(scope.importMode).toBe(false);
            });
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
            expect(scope.tagColorMap[2]).toBeUndefined();
            var tagStyle = scope.tagStyle(2);
            expect(tagStyle['background-color']).toBeDefined();
            expect(scope.tagColorMap[2].color).toBeDefined();
        });

        it('should not assign new color to tag if color is already assigned', function () {
            scope.tagColorMap[2] = {};
            scope.tagColorMap[2].color = '#333333';
            scope.tagStyle(2);
            expect(scope.tagColorMap[2].color).toEqual('#333333');
        });

        it('should return tag names', function () {
            ctrl.loadData().then(function (data) {
                var tagNames = scope.tagNames();
                expect(tagNames[0]).toBe('Registration');
                expect(tagNames[1]).toBe('Patient');
                expect(tagNames[2]).toBe('PMTCT');
            });
        });

        it('should save a non existing tag', function () {
            ctrl.loadData().then(function (data) {
                httpBackend.expectPOST('form.form', {
                    "id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"},
                        {"name": "Encounter"}
                    ]}).respond(200);

                httpBackend.expectGET('form.form?id=1').
                    respond(
                    {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"},
                        {"id": 4, "name": "Encounter"}
                    ]}
                );

                httpBackend.expectGET('tags.form').
                    respond([
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"},
                        {"id": 3, "name": "PMTCT"},
                        {"id": 4, "name": "Encounter"}
                    ]);

                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                scope.html5forms[0].newTag = "Encounter";
                scope.saveTag(scope.html5forms[0]);
                httpBackend.flush();
                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                expect(scope.html5forms[0].form.tags[2]).toEqual({"id": 4, "name": "Encounter"});
                expect(scope.html5forms[0].form.tags.length).toBe(3);
                expect(scope.tags.length).toBe(4);
                expect(scope.tags[3].name).toBe("Encounter");
            });
        });

        it('should save an existing tag', function () {
            ctrl.loadData().then(function (data) {
                httpBackend.expectPOST('form.form', {
                    "id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"},
                        {"id": 3, "name": "PMTCT"}
                    ]}).respond(200);

                httpBackend.expectGET('form.form?id=1').
                    respond(
                    {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"},
                        {"id": 3, "name": "PMTCT"}
                    ]}
                );

                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                scope.html5forms[0].newTag = "PMTCT";
                scope.saveTag(scope.html5forms[0]);
                httpBackend.flush();
                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                expect(scope.html5forms[0].form.tags[2]).toEqual({"id": 3, "name": "PMTCT"});
            });
        });

        it('should ignore an already added tag and should ignore case', function () {
            ctrl.loadData().then(function (data) {
                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                scope.html5forms.newTag = "registration";
                scope.saveTag(scope.html5forms[0]);
                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                expect(scope.html5forms[0].form.tags.length).toBe(2);
            });
        });

        it('should not add empty tag', function () {
            ctrl.loadData().then(function (data) {
                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                scope.html5forms.newTag = "";
                scope.saveTag(scope.html5forms[0]);
                expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
                expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
                expect(scope.html5forms[0].form.tags.length).toBe(2);
            });

        });

        it('should remove tag', function () {
            ctrl.loadData().then(function (data) {
            httpBackend.expectPOST('form.form', {
                "id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                    {"id": 2, "name": "Patient"}
                ]}).respond(200);

            httpBackend.expectGET('form.form?id=1').
                respond(
                {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                    {"id": 2, "name": "Patient"}
                ]}
            );

            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            scope.removeTag(scope.html5forms[0].form, scope.tags[0]);
            httpBackend.flush();
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags.length).toBe(1);
            });
        });
    });
});