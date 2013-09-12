'use strict';

/* global chronicleApp */
chronicleApp.controller('pageCtrl', function ($scope, $routeParams, pageService, $location) {
        var pageUri = ($routeParams.pageUri === undefined) ? 'main' : $routeParams.pageUri;

    var page = pageService.get({uri: pageUri}, function() {
            $scope.page = page;
        });

        $scope.pages = pageService.query({}, function() {
            console.log($scope.pages);
        });

        $scope.editPage = function() {
            $scope.editable = true;
        };

        $scope.savePage = function() {
            $scope.page.$save(function() {
                $scope.editable = false;
                console.log('$scope.page.uri', $scope.page);
                $location.path($scope.page.uri);
            });
        };

        $scope.removePage = function() {
            $scope.page.$delete(function() {
                $location.path('/');
            });
        };
    }
);



//angular.module('chronicleApp')
//    .controller('PageCtrl', function ($scope, PageService, $routeParams, $location) {
//        var pageUri = ($routeParams.pageUri === undefined) ? 'main' : $routeParams.pageUri;
//
//        console.log('Loading: ', pageUri);
//
//        var page = PageService.get({uri: pageUri}, function() {
//            $scope.page = page;
//        });
//
//        $scope.pages = PageService.query({}, function() {
//            console.log($scope.pages);
//        });
//
//        $scope.editPage = function() {
//            $scope.editable = true;
//        };
//
//        $scope.savePage = function() {
//            $scope.page.$save(function() {
//                $scope.editable = false;
//                console.log('$scope.page.uri', $scope.page);
//                $location.path($scope.page.uri);
//            });
//        };
//
//        $scope.removePage = function() {
//            $scope.page.$delete(function() {
//                $location.path('/');
//            });
//        };
//    });
