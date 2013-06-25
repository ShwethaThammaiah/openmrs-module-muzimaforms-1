describe('Html5Forms controllers', function () {


    beforeEach(module('html5forms'));

    describe('FormsCtrl', function () {
        var scope, ctrl, q, timeout;

        var FormsService = {
            all: function () {
                var deferred = q.defer();
                timeout(function () {
                    deferred.resolve({
                        data: [
                            {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                                {"id": 1, "name": "Registration"},
                                {"id": 2, "name": "Patient"}
                            ]  },
                            {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false, "tags": [
                                {"id": 1, "name": "Registration"}
                            ] }
                        ]
                    });
                });
                return deferred.promise;
            }
        };

        var FormService = {
            save: function (form) {
                return null;
            },
            get: function (id) {
                return null;
            }
        };


        var XFormService = {
            all: function () {
                var deferred = q.defer();
                timeout(function () {
                    deferred.resolve({
                        data: [
                            {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false},
                            {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false},
                            {"id": 3, "name": "Outreach Adult Locator Form", "description": "", "selected": false}
                        ]
                    });
                });
                return deferred.promise;
            }
        };


        var TagService = {
            tags: [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"},
                {"id": 3, "name": "PMTCT"}
            ],
            all: function () {
                var deferred = q.defer();
                timeout(function () {
                    deferred.resolve({
                        data: TagService.tags
                    })
                });
                return deferred.promise;
            }
        };


        beforeEach(inject(function ($rootScope, $controller, $q, $timeout) {
            q = $q;
            timeout = $timeout;
            scope = $rootScope.$new();
            ctrl = $controller(FormCtrl, {
                $scope: scope,
                TagService: TagService,
                FormService: FormService,
                FormsService: FormsService,
                XFormService: XFormService
            });
            scope.init();
        }));

        it('should assign tags to scope', function () {
            expect(scope.tags).toBeUndefined();
            timeout.flush();
            expect(scope.tags[0].id).toBe(1);
            expect(scope.tags[0].name).toBe("Registration");
        });

        it('should assign forms to scope', function () {
            expect(scope.hasForms()).toBe(false);
            timeout.flush();
            expect(scope.hasForms()).toBe(true);

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

            timeout.flush();
            var getPromise = function (response) {
                response = response || "";
                var deferredForSave = q.defer();
                deferredForSave.resolve(response);
                return deferredForSave.promise;
            };

            spyOn(FormService, "save").andReturn(getPromise(""));
            spyOn(FormsService, "all").andReturn(getPromise({data: [
                {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"}
                ]  },
                {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false, "tags": [
                    {"id": 1, "name": "Registration"}
                ] }
            ]}));
            scope.selectXForm('4');
            scope.selectXForm('5');
            scope.importMode = true;

            scope.done();
            scope.$apply();
            expect(FormService.save).toHaveBeenCalledWith({'id': '4'});
            expect(FormService.save).toHaveBeenCalledWith({'id': '5'});
            expect(FormsService.all).toHaveBeenCalled();
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
            timeout.flush();
            var tagNames = scope.tagNames();
            expect(tagNames[0]).toBe('Registration');
            expect(tagNames[1]).toBe('Patient');
            expect(tagNames[2]).toBe('PMTCT');
        });


        it('should save a non existing tag', function () {
            timeout.flush();
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});

            var unsavedForm = {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"},
                {"name": "Encounter"}
            ]};
            var savedForm = {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"},
                {"id": 4, "name": "Encounter"}
            ]};

            scope.html5forms[0].newTag = "Encounter";

            var getPromise = function (response) {
                response = response || "";
                var deferredForSave = q.defer();
                deferredForSave.resolve(response);
                return deferredForSave.promise;
            };

            spyOn(FormService, "save").andReturn(getPromise(""));
            spyOn(FormService, "get").andReturn(getPromise(savedForm));
            spyOn(TagService, "all").andReturn(getPromise({
                data: [
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"},
                    {"id": 3, "name": "PMTCT"},
                    {"id": 4, "name": "Encounter"}
                ]}));

            scope.saveTag(scope.html5forms[0]);
            scope.$apply();

            expect(FormService.save).toHaveBeenCalled();
            expect(FormService.get).toHaveBeenCalled();

            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags[2]).toEqual({"id": 4, "name": "Encounter"});

        });

        it('should save an existing tag', function () {
            timeout.flush();
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});

            var unsavedForm = {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"}
            ]};
            var savedForm = {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"},
                {"id": 3, "name": "PMCMT"}
            ]};

            scope.html5forms[0].newTag = "PMTCT";


            var getPromise = function (response) {
                response = response || "";
                var deferredForSave = q.defer();
                deferredForSave.resolve(response);
                return deferredForSave.promise;
            };

            spyOn(FormService, "save").andReturn(getPromise(""));
            spyOn(FormService, "get").andReturn(getPromise(savedForm));
            spyOn(TagService, "all").andReturn(getPromise({
                data: [
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"},
                    {"id": 3, "name": "PMTCT"}
                ]}));

            scope.saveTag(scope.html5forms[0]);
            scope.$apply();
            expect(FormService.save).toHaveBeenCalledWith(savedForm);
            expect(FormService.get).toHaveBeenCalled();

            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags[2]).toEqual({"id": 3, "name": "PMCMT"});

        });

        it('should ignore an already added tag and should ignore case', function () {
            timeout.flush();
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            scope.html5forms.newTag = "registration";
            scope.saveTag(scope.html5forms[0]);
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags.length).toBe(2);
        });

        it('should not add empty tag', function () {
            timeout.flush();
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            scope.html5forms.newTag = "";
            scope.saveTag(scope.html5forms[0]);
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags.length).toBe(2);
        });

        it('should remove tag', function () {
            timeout.flush();
            var oldForm = {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"}
            ]};
            var newForm = {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 2, "name": "Patient"}
            ]};

            var getPromise = function (response) {
                response = response || "";
                var deferredForSave = q.defer();
                deferredForSave.resolve(response);
                return deferredForSave.promise;
            };

            spyOn(FormService, "save").andReturn(getPromise(""));
            spyOn(FormService, "get").andReturn(getPromise(newForm));
            spyOn(TagService, "all").andReturn(getPromise({
                data: [
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"},
                    {"id": 3, "name": "PMTCT"}
                ]}));

            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 1, "name": "Registration"});
            expect(scope.html5forms[0].form.tags[1]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags.length).toBe(2);
            scope.removeTag(scope.html5forms[0].form, scope.tags[0]);
            scope.$apply();
            expect(FormService.save).toHaveBeenCalledWith(newForm);
            expect(FormService.get).toHaveBeenCalled();
            expect(scope.html5forms[0].form.tags[0]).toEqual({"id": 2, "name": "Patient"});
            expect(scope.html5forms[0].form.tags.length).toBe(1);
        });

        it('should add tag filter to active tag filters', function () {
            timeout.flush();
            expect(scope.activeTagFilters.length).toBe(0);
            scope.addTagFilter({"id": 2, "name": "Patient"});
            expect(scope.activeTagFilters.length).toBe(1);
            expect(scope.activeTagFilters[0].name).toBe("Patient");
        });

        it('should not add tag filter if already added', function () {
            timeout.flush();
            scope.activeTagFilters = [scope.tags[1]];
            scope.addTagFilter(scope.tags[1]);
            expect(scope.activeTagFilters.length).toBe(1);
        });

        it('should remove tagfilter', function () {
            timeout.flush();
            scope.activeTagFilters = [scope.tags[1]];
            scope.removeTagFilter(scope.tags[1]);
            expect(scope.activeTagFilters.length).toBe(0);
        });

        it('should return false if active tag list is empty and true otherwise', function () {
            timeout.flush();
            scope.activeTagFilters = [scope.tags[1]];
            expect(scope.tagFilterActive()).toBe(true);
            scope.activeTagFilters = [];
            expect(scope.tagFilterActive()).toBe(false);
        });
    });
});
