'use strict';

angular.module('chronicleApp', ['ngRoute', 'ngResource', 'PageService'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/:pageUri', {
        templateUrl: 'views/page.html',
        controller: 'PageCtrl'
      })
      .when('/', {
        templateUrl: 'views/page.html',
        controller: 'PageCtrl'
      })
  });
