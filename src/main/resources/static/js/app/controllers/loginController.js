angular.module('app')

.controller('LoginController',
    ['$scope', '$location', 'AuthenticationService',
    function ($scope, $location, AuthenticationService) {
        // reset login status
        AuthenticationService.ClearCredentials();
        $scope.$emit('logout');
        var vm = this;
        vm.login       = login;
        vm.error       = '';
        vm.dataLoading = false;

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password,
                function () {
                    $location.path('/games');
                    $scope.$emit('login');
                },
                function(response) {
                    vm.error = 'Sorry, your Username and Password combination do not match.';
                    vm.dataLoading = false;
                 }
            )
        };
    }]);