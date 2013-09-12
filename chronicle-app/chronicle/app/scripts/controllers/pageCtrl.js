'use strict';

/* global chronicleApp */
chronicleApp.controller('pageCtrl', function ($scope, $routeParams, pageService, $location, $q) {
        $scope.page = pageService.get({uri: ($routeParams.pageUri === undefined) ? 'main' : $routeParams.pageUri});
        $scope.pages = pageService.query();

        $scope.editPage = function() {
            $scope.editable = true;
        };

        $scope.removePage = function () {
            this.page.$delete(function() {
                $location.path('/');
            });
        };

        $scope.savePage = function() {
            this.page.$save(function() {
                $scope.editable = false;
                console.log('$scope.page.uri', $scope.page);
                $location.path($scope.page.uri);
            });
        };
    }
);

