angular.module('app')

.factory('GameService',
    ['$http', '$rootScope',
    function ($http, $rootScope) {
        var service = {};

        service.GetGames = function (successCallback, errorCallback) {
            return service.GetGamesForUser($rootScope.globals.currentUser.data.id, successCallback, errorCallback);
        };

        service.GetGamesForUser = function (userId, successCallback, errorCallback) {
            console.log("Attempting to retrieve games owned by user: " + userId);
            $http.get('/v1/users/' + userId + '/games').then(function (data) {
                console.log('Found ' + data.data.data.length + ' games');
                successCallback(data.data.data);
            }, errorCallback);
        };

        return service;
    }]);