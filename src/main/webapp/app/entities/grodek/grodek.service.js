(function() {
    'use strict';
    angular
        .module('showCasesApp')
        .factory('Grodek', Grodek);

    Grodek.$inject = ['$resource'];

    function Grodek ($resource) {
        var resourceUrl =  'api/grodeks/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
