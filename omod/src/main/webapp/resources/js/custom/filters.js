'use strict';

/* Filters */

angular.module('html5filters', []).filter('tagFilter', function() {
    return function(html5forms, tagList) {
        if(tagList.length == 0){
            return html5forms;
        }
        var result = [];
        angular.forEach(html5forms, function(html5form){
            angular.forEach(html5form.form.tags, function(tag){
                angular.forEach(tagList, function(activeTag){
                    if(tag.name === activeTag.name){
                        var index = result.indexOf(html5form);
                        if(index < 0){
                            result.push(html5form);
                        }
                    }
                });
            });
        });
        return result;
    };
});
