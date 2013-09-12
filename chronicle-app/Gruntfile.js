// Generated on 2013-09-08 using generator-angular 0.3.1
'use strict';

var mountFolder = function (connect, dir) {
    return connect.static(require('path').resolve(dir));
};

function parent(child) {
    return child.substring(0, child.lastIndexOf('/'));
}

module.exports = function (grunt) {
    // load all grunt tasks
    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    // configurable paths
    var config = {
        app: 'chronicle/app',
        libs: 'common/lib',
        dist: 'dist'
    };

    var serverPort = grunt.option('serverPort') || 9005;
    var reloadPort = grunt.option('reloadPort') || 8889;
    var hostname = grunt.option('hostname') || 'localhost';

    var lrSnippet = require('connect-livereload')({ port: reloadPort });
    var proxySnippet = require('grunt-connect-proxy/lib/utils').proxyRequest;

    grunt.initConfig({
        yeoman: config,
        watch: {
            compass: {
                files: ['<%= yeoman.app %>/styles/{,*/}*.{scss,sass}'],
                tasks: ['compass:app']
            },
            livereload: {
                options: {
                    livereload: config.reloadPort
                },
                files: [
                    '<%= yeoman.app %>/{,*/}*.html',
                    '{.tmp,<%= yeoman.app %>}/styles/{,*/}*.css',
                    '{.tmp,<%= yeoman.app %>}/scripts/{,*/}*.js',
                    '<%= yeoman.app %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
                ]
            }
        },
        connect: {
            proxies: [
                {
                    context: '/page',
                    host: 'localhost',
                    port: 8080,
                    https: false,
                    changeOrigin: true
                }
            ],
            options: {
                port: serverPort,
                hostname: hostname
            },
            livereload: {
                options: {
                    middleware: function (connect) {
                        return [
                            proxySnippet,
                            lrSnippet,
                            mountFolder(connect, '.tmp'),
                            mountFolder(connect, config.app)
                        ];
                    }
                }
            },
            dist: {
                options: {
                    middleware: function (connect) {
                        return [
                            proxySnippet,
                            mountFolder(connect, config.dist)
                        ];
                    }
                }
            }
        },
        open: {
            server: {
                url: 'http://localhost:<%= connect.options.port %>'
            }
        },
        clean: {
            app: [config.app + '/scripts/templates.js', parent(config.app) + '/coverage'],
            dist: config.dist,
            runtime: ['.tmp','.sass-cache', 'coverage']
        },
        concurrent: {
            quality: [
                'jshint:app',
                'karma:app'
            ],
            process: [
                'ngtemplates:app',
                'concat:modules',
                'concat:plugins'
            ]
        },
        jshint: {
            options: {
                jshintrc: '.jshintrc'
            },
            app: [
                '<%= yeoman.app %>/scripts/{,*/}*.js',
            ]
        },
        karma: {
            options: {
                singleRun: true
            },
            app: {
                configFile: parent(config.app) + '/config/karma.conf.js'
            }
        },
        ngtemplates: {
            app: {
                options: {
                    base: '<%= yeoman.app %>',
                    module: 'chronicleApp'
                },
                src: '<%= yeoman.app %>/partials/**.html',
                dest: '<%= yeoman.app %>/scripts/templates.js'
            }
        },
        compass: {
            options: {
                cssDir: '.tmp/styles',
                generatedImagesDir: '<%= yeoman.dist %>/img',
                imagesDir: '<%= yeoman.app %>/img',
                javascriptsDir: '<%= yeoman.app %>/scripts',
                importPath: '<%= yeoman.libs %>',
                relativeAssets: false
            },
            app: {
                options: {
                    fontsDir: '<%= yeoman.libs %>/font-awesome/font',
                    sassDir: '<%= yeoman.app %>/styles',
                    imagesDir: '<%= yeoman.app %>/img',
                    javascriptsDir: '<%= yeoman.app %>/scripts'
                }
            },
            dist: {}
        },
        concat: {
            modules: {
                files: {
                    'dist/scripts/modules.js': [
                        '<%=yeoman.libs %>/angular-route/angular-route.js',
                        '<%=yeoman.libs %>/angular-resource/angular-resource.js',
                        '<%=yeoman.libs %>/angular-cookies/angular-cookies.js',
                        '<%=yeoman.libs %>/angular-sanitize/angular-sanitize.js' ]
                }
            },
            plugins: {
                files: {
                    'dist/scripts/plugins.js': [
                        '<%=yeoman.libs %>/angular-ui/build/angular-ui.js',
                        '<%=yeoman.libs %>/jquery/jquery.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/affix.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/alert.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/dropdown.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/tooltip.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/modal.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/transition.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/button.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/popover.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/carousel.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/scrollspy.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/collapse.js',
                        '<%=yeoman.libs %>/bootstrap-sass/js/tab.js',
                        '<%=yeoman.libs %>/markdown/lib/markdown.js'
                    ]
                }
            },
            app: {
                files: {
                    'dist/scripts/chronicle.js': [
                        '<%=yeoman.app %>/scripts/chronicleApp.js',
                        '<%=yeoman.app %>/scripts/{,*/}*.js',
                        '<%=yeoman.app %>/scripts/**/*.js']
                }
            }
        },
        copy: {
            app: {
                files: [
                    {
                        expand: true,
                        dot: true,
                        cwd: '<%= yeoman.app %>',
                        dest: '<%= yeoman.dist %>',
                        src: [
                            '*.{ico,png,txt}',
                            '.htaccess',
                            'lib/**/*',
                            'img/{,*/}*.{gif,webp,svg}',
                            'styles/fonts/*'
                        ]
                    },
                    {
                        expand: true,
                        cwd: '.tmp/img',
                        dest: '<%= yeoman.dist %>/img',
                        src: [
                            'generated/*'
                        ]
                    },
                    {
                        expand: true,
                        cwd: '<%= yeoman.libs %>/font-awesome/font',
                        dest: '<%= yeoman.dist %>/font',
                        src: [
                            '*'
                        ]
                    }
                ]
            }
        },
        imagemin: {
            app: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= yeoman.app %>/img',
                        src: '{,*/}*.{png,jpg,jpeg}',
                        dest: '<%= yeoman.dist %>/img'
                    }
                ]
            }
        },
        svgmin: {
            dist: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= yeoman.app %>/img',
                        src: '{,*/}*.svg',
                        dest: '<%= yeoman.dist %>/img'
                    }
                ]
            }
        },
        htmlmin: {
            app: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= yeoman.app %>',
                        src: ['*.html'],
                        dest: '<%= yeoman.dist %>'
                    }
                ]
            }
        },
        cdnify: {
            dist: {
                html: ['<%= yeoman.dist %>/*.html']
            }
        },
        ngmin: {
            dist: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= yeoman.dist %>/scripts',
                        src: '*.js',
                        dest: '<%= yeoman.dist %>/scripts'
                    }
                ]
            }
        },
        cssmin: {
            dist: {
                files: {
                    '<%= yeoman.dist %>/styles/chronicle.css': [
                        '.tmp/styles/chronicle.css',
                        '<%= yeoman.dist %>/styles/chronicle.css'
                    ]
                }
            }
        },
        uglify: {
            modules: {
                files: {
                    '<%= yeoman.dist %>/scripts/modules.js': [
                        '<%= yeoman.dist %>/scripts/modules.js'
                    ]
                }
            },
            plugins: {
                files: {
                    '<%= yeoman.dist %>/scripts/plugins.js': [
                        '<%= yeoman.dist %>/scripts/plugins.js'
                    ]
                }
            },
            app: {
                files: {
                    '<%= yeoman.dist %>/scripts/chronicle.js': [
                        '<%= yeoman.dist %>/scripts/chronicleApp.js'
                    ]
                }
            }
        },
        rev: {
            dist: {
                files: {
                    src: [
                        '<%= yeoman.dist %>/scripts/{,*/}*.js',
                        '<%= yeoman.dist %>/styles/{,*/}*.css',
                        '<%= yeoman.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}'
                    ]
                }
            }
        },
        usemin: {
            html: ['<%= yeoman.dist %>/{,*/}*.html'],
            css: ['<%= yeoman.dist %>/styles/{,*/}*.css'],
            options: {
                dirs: ['<%= yeoman.dist %>']
            }
        }
    });

    grunt.registerTask('server', function (target) {
        if (target === 'dist') {
            return grunt.task.run(['build', 'open', 'configureProxies', 'connect:dist:keepalive']);
        }

        grunt.task.run([
            'clean:runtime',
            'configureProxies',
            'compass:app',
            'connect:livereload',
            'open',
            'watch'
        ]);
    });

    grunt.registerTask('build', [
        'clean',
        'concurrent:quality',
        'concurrent:process',
        'concat:app',
        'compass',
        'copy',
        'imagemin',
        'htmlmin',
        'cdnify',
        'ngmin',
        'cssmin',
        'uglify',
        'rev' ,
        'usemin'
    ]);


    grunt.registerTask('default', 'build');
};
