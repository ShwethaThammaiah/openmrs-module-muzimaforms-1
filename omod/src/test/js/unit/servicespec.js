describe('Html5Forms services', function () {

    beforeEach(module('html5forms'));

    describe('FormService', function () {
        var service;

        beforeEach(inject(function (_FormService_) {
            service = _FormService_;
        }));

        it('should assign colors to tags', function () {

        });

    });

    describe('TagService', function () {
        var service;

        beforeEach(inject(function (_TagService_) {
            service = _TagService_;
        }));

        it('should assign color to tag', function () {
            expect(service.tags[2].color).toBeUndefined();
            service.tagColor(2);
            expect(service.tags[2].color).toBeDefined();
        });

        it('should not assign new color to tag if color is already assigned', function () {
            service.tags[2].color = '#333333';
            service.tagColor(2);
            expect(service.tags[2].color).toEqual('#333333');
        });
    });
});