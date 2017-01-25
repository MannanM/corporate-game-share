//
// Angular JS CorpGameShare Application
//

angular.module('app')
    .controller('ApplicationController', ['$scope', '$http', 'appConfig',
        function($scope, $http, appConfig) {

    var vm = this;
    vm.url = appConfig.apiUrl;

    vm.init = function() {
        console.log('loading app...' + appConfig.apiUrl);
    };

}]);
