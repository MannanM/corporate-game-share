//
// Angular JS Game Share Application
//
var app = angular.module('app', ['ngRoute', 'ngCookies', 'ngMaterial', 'ngMdIcons']);

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
    $mdThemingProvider.theme('default') .primaryPalette('blue') .accentPalette('blue-grey');
    $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
    $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
    $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
});

app.filter('escape', function() {
  return window.encodeURIComponent;
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

app.controller('LeftCtrl', function ($scope, $timeout, $mdSidenav, $log) {
   $scope.close = function () {
     // Component lookup should always be available since we are not using `ng-if`
     $mdSidenav('left').close()
       .then(function () {
         $log.debug("close LEFT is done");
       });

   };
 });

//common functions, should they live somewhere else?
function logError(data) {
    console.log(data)
};

function chunk(arr, size) {
  var newArr = [];
  for (var i = 0; i < arr.length; i += size) {
    newArr.push(arr.slice(i, i+size));
  }
  return newArr;
}

function reverse(arr) {
    var i = 0;
    var j = arr.length - 1;
    while (i < j) {
        var x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
        i++;
        j--;
    }
}