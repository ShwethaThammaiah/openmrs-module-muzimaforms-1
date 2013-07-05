describe('Html5Forms services', function () {

    beforeEach(module('html5forms'));

    describe('FormsService', function () {
        var httpBackend, service;

        var setGetAllExpectation = function () {
            httpBackend.expectGET("forms.form").
                respond([
                    {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"}
                    ]  },
                    {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false, "tags": [
                        {"id": 1, "name": "Registration"}
                    ] }
                ])
        };

        beforeEach(inject(function (_$httpBackend_, FormsService) {
            httpBackend = _$httpBackend_;
            service = FormsService;

        }));

        it("should get all forms", function () {
            setGetAllExpectation();
            service.all().then(function (result) {
                var forms = result.data;
                expect(forms.length).toBe(2);
            });
            httpBackend.flush();
        });


    });

    describe('FormService', function () {
        var httpBackend, service;
        var setGetOneExpectation = function () {
            httpBackend.expectGET("form.form?id=1").
                respond(
                {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false,
                    "tags": [
                        {"id": 1, "name": "Registration"},
                        {"id": 2, "name": "Patient"}
                    ]
                });
        };
        beforeEach(inject(function (_$httpBackend_, FormService) {
            httpBackend = _$httpBackend_;
            service = FormService;

        }));

        it("should get one form", function () {
            setGetOneExpectation();
            service.get(1).then(function (result) {
                var form = result.data;
                expect(form.id).toBe(1);
                expect(form.name).toBe("Patient Registration Form");
            });
            httpBackend.flush();
        });

    });


    describe('TagService', function () {
        var httpBackend, service;
        beforeEach(inject(function (_$httpBackend_, TagService) {
            httpBackend = _$httpBackend_;
            service = TagService;
            httpBackend.expectGET("tags.form").
                respond([
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"},
                    {"id": 3, "name": "PMTCT"}
                ]);
        }));

        it("should get all tags", function () {
            service.all().then(function (result) {
                var forms = result.data;
                expect(forms.length).toBe(3);
            });
            httpBackend.flush();
        });

    });

    describe('XFormsService', function () {
        var httpBackend, service;
        beforeEach(inject(function (_$httpBackend_, XFormService) {
            httpBackend = _$httpBackend_;
            service = XFormService;
            httpBackend.expectGET("xforms.form").
                respond([
                    {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false},
                    {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false},
                    {"id": 3, "name": "Outreach Adult Locator Form", "description": "", "selected": false}
                ]);
        }));

        it("should get all xforms", function () {
            service.all().then(function (result) {
                var forms = result.data;
                expect(forms.length).toBe(3);
            });
            httpBackend.flush();
        });

    });
});
