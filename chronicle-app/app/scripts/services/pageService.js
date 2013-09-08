'use strict';

angular.module('PageService', ['ngResource'])
  .factory('PageService', function ($resource) {
    return $resource('/page/:uri', {uri: '@uri'}, {
      get: {
        method: 'GET'
      },
      query: {
        method: 'GET',
        isArray: true
      }
    });
  });