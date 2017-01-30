//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('GamesController', ['GameService', '$rootScope', '$scope', '$http',
        function(GameService, $rootScope, $scope, $http) {

    var vm = this;
    vm.consoles = [];
    vm.games = [];
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
        //Load Games for user
        GameService.GetGames(function(data) {
            reverse(data);
            vm.games = chunk(data, 3);
        }, logError);
    };

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
