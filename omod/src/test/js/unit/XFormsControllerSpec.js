describe('muzimaForms controllers', function () {
    beforeEach(module('muzimaforms'));
    describe('XFormsCtrl', function () {


        var FormService = {
            save: function (form) {
                return null;
            }
        };


        var XFormService = {
            all: function () {
                return {then: function (callback) {
                    callback({result: {data: [
                        {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false},
                        {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false},
                        {"id": 3, "name": "Outreach Adult Locator Form", "description": "", "selected": false}
                    ]}})
                }};
            }
        };

        var window = {
            open: function () {
                return null;
            }
        };

        beforeEach(inject(function ($rootScope, $controller) {
            scope = $rootScope.$new();
            ctrl = $controller(XFormsCtrl, {
                $scope: scope,
                $window: window,
                FormService: FormService,
                XFormService: XFormService
            });
            scope.init();
        }));

        it('should assign xforms to an empty array', function () {
            expect(scope.hasXForms()).toBe(false);
        });

        it('should fetch Xforms', function () {
            scope.fetch();
        });


        it('should post selected xform ids when clicked on done', function () {
            spyOn(FormService, "save").andReturn("");

            scope.selectXForm('4');
            scope.selectXForm('5');

            scope.done();
            scope.$apply();

            expect(FormService.save).toHaveBeenCalledWith({'id': '4'});
            expect(FormService.save).toHaveBeenCalledWith({'id': '5'});
        });

    });
})
;
