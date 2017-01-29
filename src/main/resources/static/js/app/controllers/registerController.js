angular.module('app')

.controller('RegisterController', ['$scope', '$location',
    function ($scope, $location) {
        var vm = this;
        vm.dataLoading      = false;
        vm.searchText       = '';
        vm.selectedConsoles = [];
        vm.consoles         = loadAll();
        vm.querySearch      = querySearch;
        vm.register = function () {
            vm.dataLoading = true;
        };

        /**
         * Search for consoles...
         */
        function querySearch (query) {
          return query ? vm.consoles.filter( createFilterFor(query) ) : vm.consoles;
        }

        /**
         * Build `consoles` list of key/value pairs. Switch this over to console endpoint
         */
        function loadAll() {
          return 'PlayStation 4, Xbox One'.split(/, +/g).map( function (state) {
            return {
              value: state.toLowerCase(),
              display: state
            };
          });
        }

        /**
         * Create filter function for a query string
         */
        function createFilterFor(query) {
          var lowercaseQuery = angular.lowercase(query);

          return function filterFn(state) {
            return (state.value.indexOf(lowercaseQuery) === 0);
          };
        }
    ///
}]);