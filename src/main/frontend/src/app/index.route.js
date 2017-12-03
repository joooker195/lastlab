(function () {
  'use strict';

  angular
    .module('frontend')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
     $stateProvider
       .state('plane', {
         url: '/plane',
         views: {
           'index': {
             templateUrl: 'app/item/plane.html',
             controller: 'PlaneController',
             controllerAs: 'planeCtrl'
           }
         },
         resolve: {}
       })
       .state('products', {
         url: '/products',
         views: {
           'index': {
             templateUrl: 'app/item/item.html',
             controller: 'ItemController',
             controllerAs: 'itemCtrl'
           }
         },
         resolve: {}
       })
       .state('buyers', {
       url: '/buyers',
       views: {
         'index': {
           templateUrl: 'app/buyer/buyer.html',
           controller: 'BuyerController',
           controllerAs: 'buyerCtrl'
         }
       },
       resolve: {}
     });

    $urlRouterProvider.otherwise(function ($injector, $location) {
      console.log($injector, $location);
      return "/";
    });
  }

})();
