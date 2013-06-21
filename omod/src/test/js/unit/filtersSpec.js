'use strict';

describe('filter', function () {

    beforeEach(module('html5filters'));

    describe('tagFilter', function () {
        var html5forms = [
            {form: {"id": 1, "name": "Patient Registration Form", "description": "Form for registering patients", "selected": false, "tags": [
                {"id": 1, "name": "Registration"},
                {"id": 2, "name": "Patient"}
            ]  }, newTag: ""},
            {form: {"id": 2, "name": "PMTCT Ante-Natal Care Form", "description": "", "selected": false, "tags": [
                {"id": 1, "name": "Registration"}
            ] }, newTag: ""},
            {form: {"id": 3, "name": "AMPATH Form", "description": "", "selected": false, "tags": [
                {"id": 1, "name": "Encounter"}
            ] }, newTag: ""}
        ];


        it('should return form only if it have a tag corresponding to the active tag list',
            inject(function (tagFilterFilter) {
                var activeTagFilters = [
                    {"id": 1, "name": "Registration"},
                    {"id": 2, "name": "Patient"}
                ];
                var result = tagFilterFilter(html5forms, activeTagFilters);
                expect(result.length).toBe(2);
                expect(result[0]).toBe(html5forms[0]);
                expect(result[1]).toBe(html5forms[1]);
            }));

        it('should return all forms if aciveTagList is empty',
            inject(function (tagFilterFilter) {
                var activeTagFilters = [];
                var result = tagFilterFilter(html5forms, activeTagFilters);
                expect(result.length).toBe(3);
                expect(result[0]).toBe(html5forms[0]);
                expect(result[1]).toBe(html5forms[1]);
                expect(result[2]).toBe(html5forms[2]);
            }));
    });
});