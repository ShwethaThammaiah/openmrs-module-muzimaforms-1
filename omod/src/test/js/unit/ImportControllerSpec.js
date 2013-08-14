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
    });
});
