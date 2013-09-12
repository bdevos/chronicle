module.exports = function (config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: '../app',

        // frameworks to use
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [
            '../../common/lib/angular/angular.js',
            '../../common/lib/angular-cookies/angular-cookies.js',
            '../../common/lib/angular-resource/angular-resource.js',
            '../../common/lib/angular-sanitize/angular-sanitize.js',
            '../../common/lib/angular-mocks/angular-mocks.js',
            'scripts/chronicleApp.js',
            'scripts/**/*.js',
            'partials/*.html',
            '../test/unit/**/*.js'
        ],

        // list of files to exclude
        exclude: [

        ],

        // test results reporter to use
        // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
        reporters: ['progress', 'coverage'],

        preprocessors: {
            'partials/*.html': 'html2js',
            'scripts/**/*.js': 'coverage'
        },

        coverageReporter: {
            type: 'html',
            dir: '../coverage'
        },

        // web server port
        port: 9876,

        // enable / disable colors in the output (reporters and logs)
        colors: true,

        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['Chrome'],

        // If browser does not capture in given timeout [ms], kill it
        captureTimeout: 60000,

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false
    });
};