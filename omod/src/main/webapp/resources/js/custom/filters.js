'use strict';

/* Filters */

angular.module('muzimafilters', []).filter('tagFilter', function() {
    return function(muzimaforms, tagList) {
        if(tagList.length == 0){
            return muzimaforms;
        }
        var result = [];
        angular.forEach(muzimaforms, function(muzimaform){
            angular.forEach(muzimaform.form.tags, function(tag){
                angular.forEach(tagList, function(activeTag){
                    if(tag.name === activeTag.name){
                        var index = result.indexOf(muzimaform);
                        if(index < 0){
                            result.push(muzimaform);
                        }
                    }
                });
            });
        });
        return result;
    };
});
