'use strict';

/* global chronicleApp */
chronicleApp.factory('pageService', function ($resource) {
    return $resource('/page/:uri', {
        uri: '@uri'
        }, {
        query: {
            method: 'GET',
            isArray: true
        }
    });
});