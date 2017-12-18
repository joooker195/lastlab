(function () {
  'use strict';

  angular
    .module('frontend')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('buyers', {
        url: '/buyers',
        views: {
          'index': {
            templateUrl: 'app/buyer/buyer.html',
            controller: 'BuyerController',
            controllerAs: 'buyerCtrl'
          }
        },
        resolve: {
          allBuyers: function ($http) {
            return $http({
              method: "GET",
              url: "http://localhost:8080/crudGoods/rest/getAllBuyers",
              params:{}
            });
          }
        }
      });


    $urlRouterProvider.otherwise(function ($injector, $location) {
      console.log($injector, $location);
      return "/";
    });
  }

})();
