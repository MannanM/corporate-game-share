angular.module('app').directive('gameCard', function () {
 return {
   restrict: 'E',
   templateUrl: '/views/cards/game-card.html',
   scope: {
     game: '=game',
   },
   controller: function ($scope, GameService) {
     $scope.state      = getState;
     $scope.getTheme   = getTheme;
     $scope.updateGame = updateGame;
     //Console never changes so ok to shortcut and initialise like this
     $scope.console    = window.encodeURIComponent($scope.game.attributes.game.attributes.console);

     function getState() {
        return $scope.game.attributes.state;
     }

     function getTheme() {
        switch ($scope.game.attributes.state) {
            case 'UNAVAILABLE': return 'dark-grey';
            case 'SOLD': return 'dark-orange';
            case 'ON_LOAN': return 'dark-blue';
            default: return 'default';
        }
    }

    function updateGame(newState) {
        GameService.UpdateGame($scope.game.id, newState, function() {
            //update the game directly on success, rather than refreshing everything
            $scope.game.attributes.state = newState;
        }, logError);
    }
   }
 }
});