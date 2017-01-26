angular.module('app')

.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
        // reset login status
        AuthenticationService.ClearCredentials();
        $scope.$emit('logout');

        $scope.login = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.username, $scope.password,
                function () {
                    $location.path('/games');
                    $scope.$emit('login');
                },
                function(response) {
                    $scope.error = 'Sorry, your Username and Password combination does not match.';
                    $scope.dataLoading = false;
                 }
            )
        };
    }]);