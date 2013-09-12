'use strict';

/* global chronicleApp, markdown */
chronicleApp.directive('dragFilesDirective', function() {
  var processDragOverOrEnter = function(event) {
    event.preventDefault();
  };
  return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      element.bind('dragover', processDragOverOrEnter);
      element.bind('dragenter', processDragOverOrEnter);
      element.bind('drop', function(event) {
        console.log('DROPPED!');
      });
    }
  }
});