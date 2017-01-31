angular.module('app')

.factory('GameService',
    ['$http', '$rootScope',
    function ($http, $rootScope) {
        var service = {};

        service.GetGames = function (successCallback, errorCallback) {
            return service.GetGamesForUser($rootScope.globals.currentUser.data.id, successCallback, errorCallback);
        };

        service.GetGamesForUser = function (userId, successCallback, errorCallback) {
            console.log('Attempting to retrieve games owned by user: ' + userId);
            $http.get('/v1/users/' + userId + '/games').then(function (response) {
                console.log('Found ' + response.data.data.length + ' games');
                successCallback(response.data.data);
            }, errorCallback);
        };

        service.Search = function (query, consoleId) {
            console.log('Attempting to search for console: ' + consoleId + ' game: ' + query);
            //todo: use console id instead of short name, e.g. /v1/consoles/1/games?name
            return $http.get('/v1/games?pageSize=10&console=' + consoleId + '&name=' + query).then(function(response) {
                console.log(response);
                return response.data.data;
             }, function(response) { // something went wrong
                console.log(response);
             });
        };

        service.Add = function (game, successCallback, errorCallback) {
            var userId = $rootScope.globals.currentUser.data.id;
            console.log('Attempting to add game: ' + game.data.id + ' to users: ' + userId + ' library');
            return $http.post('/v1/users/' + userId + '/games', {attributes: {game: game.data}})
                .then(successCallback, errorCallback);
        };

        service.UpdateGame = function (gameId, state, successCallback, errorCallback) {
            var userId = $rootScope.globals.currentUser.data.id;
            console.log('Attempting to update game: ' + gameId + ' in users: ' + userId + ' library to ' + state);
            return $http.patch('/v1/users/' + userId + '/games/' + gameId, {attributes: {state: state}})
                .then(successCallback, errorCallback);
        };

        return service;
    }]);