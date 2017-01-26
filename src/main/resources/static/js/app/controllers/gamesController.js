//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('GamesController', ['$rootScope', '$scope', '$http',
        function($rootScope, $scope, $http) {

    var vm = this;
    vm.consoles = [];
    vm.alert = function(data) {
        alert(data);
    }

    var init = function() {
        console.log('loading games view...');
        vm.consoles = $rootScope.globals.currentUser.data.attributes.consoles;
        for (var i=0; i< vm.consoles.length; i++) {
            vm.consoles[i].background = "green";
        }
        console.log(vm.consoles);
    };

    vm.onClickMe = function() {
        console.log('handling click event on games view...');
    };

    init();
}]);
