(function () {
  'use strict';

  angular
    .module('frontend')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('products', {
        url: '/products',
        views: {
          'index': {
            templateUrl: 'app/item/item.html',
            controller: 'ItemController',
            controllerAs: 'itemCtrl'
          }
        },
        resolve: {
          allProducts: function ($http) {
            return $http({
              method: "GET",
              url: "http://localhost:8080/crudGoods/rest/getProducts",
              params:{}
            });
          }
        }
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
        resolve: {
          allBuyers: function ($http) {
            return $http({
              method: "GET",
              url: "http://localhost:8080/crudGoods/rest/getAllBuyers",
              params:{}
            });
          }
        }
      })
      .state('discounts', {
        url: '/discounts',
        views: {
          'index': {
            templateUrl: 'app/discount/discount.html',
            controller: 'DiscountController',
            controllerAs: 'discountCtrl'
          }
        },
        resolve: {
          allDiscounts: function ($http) {
            return $http({
              method: "GET",
              url: "http://localhost:8080/crudGoods/rest/getDiscounts",
              params:{}
            });
          }
        }
      })
      .state('sales', {
        url: '/sales',
        views: {
          'index': {
            templateUrl: 'app/sale/sale.html',
            controller: 'SaleController',
            controllerAs: 'saleCtrl'
          }
        },
        resolve: {
          allSells: function ($http) {
            return $http({
              method: "GET",
              url: "http://localhost:8080/crudGoods/rest/getAllSells",
              params:{}
            });
          }
        }
      })
      .state('sellers', {
        url: '/sellers',
        views: {
          'index': {
            templateUrl: 'app/seler/seller.html',
            controller: 'SellersController',
            controllerAs: 'sellerCtrl'
          }
        },
        resolve: {
          allSellers: function ($http) {
            return $http({
              method: "GET",
              url: "http://localhost:8080/crudGoods/rest/getAllSellers",
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
