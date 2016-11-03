(function() {
    'use strict';

    angular
        .module('showCasesApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('grodek', {
            parent: 'entity',
            url: '/grodek',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Grodeks'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/grodek/grodeks.html',
                    controller: 'GrodekController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('grodek-detail', {
            parent: 'entity',
            url: '/grodek/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Grodek'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/grodek/grodek-detail.html',
                    controller: 'GrodekDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Grodek', function($stateParams, Grodek) {
                    return Grodek.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'grodek',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('grodek-detail.edit', {
            parent: 'grodek-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grodek/grodek-dialog.html',
                    controller: 'GrodekDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Grodek', function(Grodek) {
                            return Grodek.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('grodek.new', {
            parent: 'grodek',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grodek/grodek-dialog.html',
                    controller: 'GrodekDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                place: null,
                                name: null,
                                description: null,
                                coords: null,
                                sign_type: null,
                                photo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('grodek', null, { reload: 'grodek' });
                }, function() {
                    $state.go('grodek');
                });
            }]
        })
        .state('grodek.edit', {
            parent: 'grodek',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grodek/grodek-dialog.html',
                    controller: 'GrodekDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Grodek', function(Grodek) {
                            return Grodek.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('grodek', null, { reload: 'grodek' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('grodek.delete', {
            parent: 'grodek',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grodek/grodek-delete-dialog.html',
                    controller: 'GrodekDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Grodek', function(Grodek) {
                            return Grodek.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('grodek', null, { reload: 'grodek' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
