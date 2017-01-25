//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('OptionsController', ['$scope', '$http',
        function($scope, $http) {

    var vm = this;

    var init = function() {
        console.log('loading options view...');
    };

    vm.onClickMe = function() {
        console.log('handling click event on options view...');
    };

    init();

}]);