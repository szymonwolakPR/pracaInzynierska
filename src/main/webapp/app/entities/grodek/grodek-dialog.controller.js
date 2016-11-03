(function() {
    'use strict';

    angular
        .module('showCasesApp')
        .controller('GrodekDialogController', GrodekDialogController);

    GrodekDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Grodek'];

    function GrodekDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Grodek) {
        var vm = this;

        vm.grodek = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.grodek.id !== null) {
                Grodek.update(vm.grodek, onSaveSuccess, onSaveError);
            } else {
                Grodek.save(vm.grodek, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('showCasesApp:grodekUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
