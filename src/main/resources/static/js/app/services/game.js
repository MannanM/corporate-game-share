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
            $http.get('/v1/users/' + userId + '/games').then(function (response) {
                console.log('Found ' + response.data.data.length + ' games');
                successCallback(response.data.data);
            }, errorCallback);
        };

        service.Search = function (query) {
            console.log("Attempting to search for game: " + query);
            //Todo: use console name or id instead of short name, as we don't have this available
            return $http.get('/v1/games?pageSize=10&console=PS4&name=' + query).then(function(response) {
                console.log(response);
                return response.data.data;
             }, function(response) { // something went wrong
                console.log(response);
             });
        };

        service.Add = function (game, successCallback, errorCallback) {
            var userId = $rootScope.globals.currentUser.data.id;
            console.log("Attempting to add game: " + game.data.id + " to users " + userId + " library");
            return $http.post('/v1/users/' + userId + '/games', {attributes: {game: game.data}})
                .then(successCallback, errorCallback);
        };

        return service;
    }]);