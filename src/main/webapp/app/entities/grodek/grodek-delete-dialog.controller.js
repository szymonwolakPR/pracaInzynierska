(function() {
    'use strict';

    angular
        .module('showCasesApp')
        .controller('GrodekDeleteController',GrodekDeleteController);

    GrodekDeleteController.$inject = ['$uibModalInstance', 'entity', 'Grodek'];

    function GrodekDeleteController($uibModalInstance, entity, Grodek) {
        var vm = this;

        vm.grodek = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Grodek.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
