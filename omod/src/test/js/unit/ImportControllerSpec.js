describe('muzima Import controllers', function () {
    beforeEach(module('muzimaforms'));
    describe('ImportCtrl', function () {
        var scope, ctrl;
        beforeEach(inject(function ($rootScope, $controller) {
            scope = $rootScope.$new();
            ctrl = $controller(ImportCtrl, {
                $scope: scope,
                $window: window
            });
        }));

        it('should return the error style when the validation type is error', function () {
            expect(scope.style('ERROR')).toBe('alert-danger');
        });

        it('should return the warning style when the validation type is warning', function () {
            expect(scope.style('WARNING')).toBe('alert-info');
        });

        it('should return false when there is not file associated to the scope', function () {
            expect(scope.hasFile()).toBe(false);
        });

        it('should return the true when there is a file associated to the scope', function () {
            scope.file = new Object();
            expect(scope.hasFile()).toBe(true);
        });

        it('should return the false if the file has not been validated', function () {
            expect(scope.isValidXForm()).toBe(false);
        });

        it('should return the false if there are validation errors', function () {
            scope.validations = { list: [
                {message: "An error occured", type: "ERROR"}
            ]};
            expect(scope.isValidXForm()).toBe(false);
        });

        it('should return the true if there are no validation errors', function () {
            scope.validations = { list: []};
            expect(scope.isValidXForm()).toBe(true);
        });

        it('should return the false if validations list is not available', function () {
            scope.validations = {};
            expect(scope.isValidXForm()).toBe(false);
        });

        it('should clear validations when you cancel', function () {
            scope.validations = {};
            scope.cancel();
            expect(scope.validations).toBe(null);
        });

    });
});
