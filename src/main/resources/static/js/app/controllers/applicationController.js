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
    vm.loggedIn = false;

    $scope.$on('login', function() {
        console.log("login event triggered");
        vm.user = $rootScope.globals.currentUser.data;
        vm.loggedIn = true;
    });
    $scope.$on('logout', function() {
        vm.user = {};
        vm.loggedIn = false;
    });

    vm.init = function() {
        console.log('loading app...' + appConfig.apiUrl);
        if ($rootScope.globals.currentUser) {
            vm.user = $rootScope.globals.currentUser.data;
            vm.loggedIn = true;
        }
    };

}]);
