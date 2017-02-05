angular.module('app').controller('GamesController', ['GameService', '$rootScope', '$scope', '$http', '$mdDialog',
   function(GameService, $rootScope, $scope, $http, $mdDialog) {

    var vm        = this;
    vm.consoles   = [];
    vm.games      = [];
    vm.addGame    = addGame;
    vm.alert      = function(data) {
        alert(data);
    }

    var init = function() {
        console.log('loading games view...');
        vm.consoles = $rootScope.globals.currentUser.data.attributes.consoles;
        for (var i = 0; i< vm.consoles.length; i++) {
            vm.consoles[i].background = "green";
        }
        console.log(vm.consoles);
        loadGames();
    };

    function loadGames() {
        GameService.GetGames(function(data) {
            vm.games = reverse(data);
        }, logError);
    }

    function addGame($event) {
        $mdDialog.show({
            controller: 'SearchGameCtrl',
            controllerAs: 'ctrl',
            templateUrl: '/views/dialogs/search-game.html',
            parent: angular.element(document.body),
            targetEvent: $event,
            clickOutsideToClose: true,
            locals: {
                title: 'Add a game to your library',
                buttonText: 'Add',
                callback: function(game, successCallback, errorCallback) {
                              GameService.Add(game, successCallback, errorCallback);
                          }
            },
        }).then(loadGames);
    }

    init();
  }
]);

//todo: move this into it's own .js file
angular.module('app').controller('SearchGameCtrl', ['GameService', '$scope', '$mdDialog', 'callback', 'title', 'buttonText',
    function(GameService, $scope, $mdDialog, callback, title, buttonText) {
        $scope.title = title;
        $scope.buttonText = buttonText;

        var self = this;
        self.querySearch = function(query, consoleId) {
            return GameService.Search(query, consoleId);
        };
        self.cancel = function($event) {
          $mdDialog.cancel();
        };
        self.finish = function($event) {
            if (!self.selectedItem) {
                return self.cancel();
            }
            callback(self.selectedItem,
                function (success) {
                    $mdDialog.hide();
                }, function(error) {
                    self.error = error.data.message;
                    self.selectedItem = null;
                }
            );
        };
    }
]);