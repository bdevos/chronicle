'use strict';

angular.module('chronicleApp')
  .controller('PageCtrl', function ($scope, PageService, $routeParams) {
    var pageUri = ($routeParams.pageUri === undefined) ? 'main' : $routeParams.pageUri;

    var page = PageService.get({uri: pageUri}, function() {
      $scope.page = page;
    });

    $scope.pages = PageService.query();

    $scope.editPage = function() {
      $scope.editable = true;
    };
    $scope.savePage = function() {
      $scope.page.$save(function() {
        $scope.editable = false;
        $scope.pages = PageService.query();
      });
    };
  });
