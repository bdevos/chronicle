'use strict';

var chronicleApp = angular.module('chronicleApp', ['ngRoute', 'ngResource', 'ngSanitize']);

chronicleApp.config(function ($routeProvider) {
    $routeProvider
        .when('/:pageUri', {
            templateUrl: 'partials/page.html'
        })
        .when('/', {
            templateUrl: 'partials/welcome.html'
        });
});