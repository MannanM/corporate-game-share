//
// Angular JS PunchCard Application
//

var app = angular.module('app', ['ngRoute']);

if (typeof bootstrapApp == 'function') {
    bootstrapApp(app);
}

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/games', {
            templateUrl: '/views/games.html',
            controller: 'GamesController',
            controllerAs: 'gc'
        }).
        when('/options', {
            templateUrl: '/views/options.html',
            controller:  'OptionsController',
            controllerAs: 'oc'
        })
        .otherwise({ redirectTo: '/games'});
}]);

