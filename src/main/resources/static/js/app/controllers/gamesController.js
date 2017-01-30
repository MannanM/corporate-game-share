//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('GamesController', ['GameService', '$rootScope', '$scope', '$http', '$mdDialog',
        function(GameService, $rootScope, $scope, $http, $mdDialog) {

    var vm      = this;
    vm.consoles = [];
    vm.games    = [];
    vm.addGame  = addGame;
    vm.alert = function(data) {
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
            reverse(data);
            calculateColumns(4);
            if (data.length <= 6) {
                calculateColumns(6/3);
            } else if (data.length <= 9) {
                calculateColumns(9/3);
            }
            vm.games = chunk(data, vm.columns);
        }, logError);
    }

    function calculateColumns(number) {
        vm.columns = number;
        vm.size = Math.floor(100 / vm.columns);
    }

    function addGame($event) {
        $mdDialog.show({
            controller: DialogCtrl,
            controllerAs: 'ctrl',
            templateUrl: '/views/dialogs/add-game.html',
            parent: angular.element(document.body),
            targetEvent: $event,
            clickOutsideToClose: true
        }).then(loadGames);
    }

    function DialogCtrl(GameService, $timeout, $q, $scope, $mdDialog) {
        var self = this;
        self.querySearch = function(query) {
            return GameService.Search(query);
        };
        self.cancel = function($event) {
          $mdDialog.cancel();
        };
        self.finish = function($event) {
            if (!self.selectedItem) {
                return self.cancel();
            }
            GameService.Add(self.selectedItem,
                function (success) {
                    $mdDialog.hide();
                }, function(error) {
                    self.error = error.data.message;
                    self.selectedItem = null;
                }
            );
        };
    }

    //move common functions somewhere else
    function logError(data) {
        console.log(data)
    };

    function chunk(arr, size) {
      var newArr = [];
      for (var i = 0; i < arr.length; i += size) {
        newArr.push(arr.slice(i, i+size));
      }
      return newArr;
    }

    function reverse(arr) {
        var i = 0;
        var j = arr.length - 1;
        while (i < j) {
            var x = arr[i];
            arr[i] = arr[j];
            arr[j] = x;
            i++;
            j--;
        }
    }

    init();
}]);
