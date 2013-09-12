'use strict';

angular.module('chronicleApp', ['ngRoute', 'ngResource', 'PageService'])
    .config(function ($routeProvider, $locationProvider) {
        // One comment

        $routeProvider
            .when('/:pageUri*', {
                templateUrl: 'views/page.html',
                controller: 'PageCtrl'
            })
            .when('/', {
                templateUrl: 'views/page.html',
                controller: 'PageCtrl'
            });
    });
