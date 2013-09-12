'use strict';

/* global chronicleApp */
chronicleApp.controller('pageCtrl', function ($scope, $routeParams, pageService, $location, $q) {

        function page() {
            var deferred = $q.defer();

            pageService.get({uri: ($routeParams.pageUri === undefined) ? 'main' : $routeParams.pageUri}, function (page) {
                deferred.resolve(page);
            }, function () {
                deferred.reject();
            });

            return deferred.promise;
        }

        page().then(function(page) {
            if(page.meta.updated === 0) {
                $scope.editable = true;
            }
            $scope.page = page;
        });

        $scope.pages = pageService.query();

		$scope.cancelEditing = function() {
            $scope.page.$get(function() {
                $scope.editable = false;
                console.log('Getting : $scope.page.uri', $scope.page);
                $location.path($scope.page.uri);
            });
        };

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
                console.log('Saving : $scope.page.uri', $scope.page);
                $location.path($scope.page.uri);
            });
        };
    }
);

