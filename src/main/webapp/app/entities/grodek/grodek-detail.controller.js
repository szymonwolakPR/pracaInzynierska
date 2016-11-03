(function() {
    'use strict';

    angular
        .module('showCasesApp')
        .controller('GrodekDetailController', GrodekDetailController);

    GrodekDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Grodek'];

    function GrodekDetailController($scope, $rootScope, $stateParams, previousState, entity, Grodek) {
        var vm = this;

        vm.grodek = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('showCasesApp:grodekUpdate', function(event, result) {
            vm.grodek = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
