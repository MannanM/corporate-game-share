//
// Angular JS PunchCard Application
//
var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngMaterial']);

if (typeof bootstrapApp == 'function') {
    bootstrapApp(app);
}
//Disable browsers popping up with Basic Auth challenge
app.config(($routeProvider, $httpProvider) => {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
});

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/login', {
            templateUrl: '/views/login.html',
            controller: 'LoginController',
            controllerAs: 'lo'
        }).
        when('/register', {
            templateUrl: '/views/register.html',
            controller: 'RegisterController',
            controllerAs: 'ro'
        }).
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
app.config(function($mdThemingProvider) {
      $mdThemingProvider.theme('default')
        .primaryPalette('blue')
        .accentPalette('blue-grey');
    });

app.run(['$rootScope', '$location', '$cookieStore', '$http',
   function ($rootScope, $location, $cookieStore, $http) {
       // keep user logged in after page refresh
       $rootScope.globals = $cookieStore.get('globals') || {};
       if ($rootScope.globals.currentUser) {
           $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
       }

       $rootScope.$on('$locationChangeStart', function (event, next, current) {
           // redirect to login page if not logged in
           if (!$rootScope.globals.currentUser) {
                if (!($location.path() == '/login' || $location.path() == '/register')) {
                    $location.path('/login');
               }
           }
       });
}]);
