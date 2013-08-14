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

        it('should assign a boolean variable indicating that the file input should be hidden', function () {
            expect(scope.showFileInput).toBe(false);
        });

        it('should return the error style when the validation type is error', function () {
            expect(scope.style('ERROR')).toBe('alert-danger');
        });

        it('should return the warning style when the validation type is warning', function () {
            expect(scope.style('WARNING')).toBe('alert-info');
        });
    });
});
