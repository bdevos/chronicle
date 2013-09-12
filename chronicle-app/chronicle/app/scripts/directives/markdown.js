'use strict';

/* global chronicleApp, markdown */
chronicleApp.directive('markdown', function ($sce) {
    return {
        restrict: 'E',
        require: '^ngModel',
        replace: true,
        template: '<p>{{model}}</p>',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$render = function() {
                if(ngModel.$viewValue !== undefined) {
                    element.html($sce.trustAsHtml(markdown.toHTML(ngModel.$viewValue)));
                }
            };
        }
    };
});
