'use strict';

var chronicleApp = angular.module('chronicleApp', ['ui', 'ngRoute', 'ngResource', 'ngSanitize', 'ngAnimate']);

chronicleApp.config(function ($routeProvider) {
    $routeProvider
        .when('/:pageUri', {
            templateUrl: 'partials/page.html'
        })
        .when('/', {
            templateUrl: 'partials/welcome.html'
        });
});