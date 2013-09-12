'use strict';

/* global chronicleApp, markdown */
chronicleApp.filter('toMarkdown', function ($sce) {
        return function (input) {
            if (input) {
                var asMarkdown = markdown.toHTML(input);
                var sanitizedMarkdown = $sce.trustAsHtml(asMarkdown);
                return sanitizedMarkdown;
            }
            return;
        };
    }
);
