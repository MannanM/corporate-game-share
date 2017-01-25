//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('GamesController', ['$scope', '$http',
        function($scope, $http) {

    var vm = this;

    var init = function() {
        console.log('loading games view...');
    };

    vm.onClickMe = function() {
        console.log('handling click event on games view...');
    };

    init();
}]);
