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
            controllerAs: 'lo',
            hideMenus: true
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

app.run(['$rootScope', '$location', '$cookieStore', '$http',
   function ($rootScope, $location, $cookieStore, $http) {
       // keep user logged in after page refresh
       $rootScope.globals = $cookieStore.get('globals') || {};
       if ($rootScope.globals.currentUser) {
           $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata;
       }

       $rootScope.$on('$locationChangeStart', function (event, next, current) {
           // redirect to login page if not logged in
           if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
               $location.path('/login');
           }
       });
}]);
