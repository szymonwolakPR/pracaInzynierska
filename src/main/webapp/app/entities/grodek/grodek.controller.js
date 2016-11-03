(function() {
    'use strict';

    angular
        .module('showCasesApp')
        .controller('GrodekController', GrodekController);

    GrodekController.$inject = ['$scope', '$state', 'Grodek'];

    function GrodekController ($scope, $state, Grodek) {
        var vm = this;
        
        vm.grodeks = [];

        loadAll();

        function loadAll() {
            Grodek.query(function(result) {
                vm.grodeks = result;
            });
        }
    }
})();
