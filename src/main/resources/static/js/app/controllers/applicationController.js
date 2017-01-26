//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('ApplicationController', ['$scope', '$rootScope', '$http', 'appConfig',
        function($scope, $rootScope, $http, appConfig) {

    var vm = this;
    vm.url = appConfig.apiUrl;
    vm.currentNavItem = 'games'
    vm.user = {};
    vm.loggedIn = function() {
        return user.id;
    };

    $scope.$on('login', function() {
        vm.user = $rootScope.globals.currentUser.data;
    });
    $scope.$on('logout', function() {
        vm.user = {};
    });

    vm.init = function() {
        console.log('loading app...' + appConfig.apiUrl);
        if ($rootScope.globals.currentUser) {
            vm.user = $rootScope.globals.currentUser.data;
        }

      $rootScope.$on('$routeChangeSuccess', function(event, current) {
        var path = current.$$route.originalPath;
        //remove forward slash
        vm.currentNavItem = path.substr(1);
      });
    };

}]);
