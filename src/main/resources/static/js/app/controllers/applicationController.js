//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('ApplicationController', ['$scope', '$rootScope', 'appConfig', '$location', '$mdSidenav',
        function($scope, $rootScope, appConfig, $location, $mdSidenav) {

    var vm = this;
    vm.url = appConfig.apiUrl;
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

    $scope.isSectionSelected = function(section) {
        return $location.path() == section ? 'active' : '';
    }

    $scope.toggleSidenav = function(menuId) {
        $mdSidenav(menuId).toggle();
    };

    vm.init = function() {
        console.log('loading app...' + appConfig.apiUrl);
        if ($rootScope.globals.currentUser) {
            vm.user = $rootScope.globals.currentUser.data;
            vm.loggedIn = true;
        }
    };

}]);
