(function () {

  angular
    .module('frontend')
    .controller('DiscountController', DiscountController);

  /** @ngInject */
  function DiscountController($scope,$state, $http,UtilsFunctionsFactory,ngDialog,allDiscounts) {
    var vm = this;
    for (var i = 0;i<allDiscounts.data.length;i++){
      var formattedFromDate = UtilsFunctionsFactory.toDate(allDiscounts.data[i].actualFrom);
      var formattedToDate = UtilsFunctionsFactory.toDate(allDiscounts.data[i].actualTo);
      allDiscounts.data[i].actualTo = formattedToDate;
      allDiscounts.data[i].actualFrom = formattedFromDate;
    }
    $scope.discounts = allDiscounts.data;

    //для добавления
    var buyerPromise =$http.get("http://localhost:8080/crudGoods/rest/getAllBuyers");
    -      buyerPromise.then(fulfilled, rejected);

    function fulfilled(resp) {
      console.log(resp.data);
      for (var i =0;i<resp.data.length;i++){
        resp.data[i].birthDate = UtilsFunctionsFactory.toDate(resp.data[i].birthDate);
      }
      console.log("Покупатели подгружены");
      $scope.buyers = resp.data;
    }

    function rejected(error) {
     console.log("Покупатели не подгружены");
      console.log(error);
    }

    var productPromise =$http.get("http://localhost:8080/crudGoods/rest/getProducts");
    -      productPromise.then(productFulfilled, productRejected);

    function productFulfilled(resp) {
      console.log(resp.data);
      console.log("Продукты  подгружены");
      $scope.products = resp.data;
    }

    function productRejected(error) {
      console.log("Продукты не подгружены");
      console.log(error);
    }


    $scope.discountParams={
      actualFrom:"",
      actualTo:"",
      amountDiscount:"",
      selectedBuyer:0,
      selectedProduct:0
    };

    $scope.addDiscount = function() {
      $scope.discountParams.actualFrom="";
      $scope.discountParams.actualTo="";
      $scope.discountParams.amountDiscount="";
      ngDialog.open({ template: 'app/discount/addDiscount.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.discountOkClickHandler = function (scope) {
      $scope.discountParams.actualFrom=UtilsFunctionsFactory.dateStringToMillis($scope.discountParams.actualFrom);
      $scope.discountParams.actualTo=UtilsFunctionsFactory.dateStringToMillis($scope.discountParams.actualTo);
      console.log($scope.discountParams);
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/addDiscount",
        params:  $scope.discountParams
      }).then(function (resp) {
          console.log("Скидка добавлена");
          console.log("Success resp", resp);
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Скидка не  добавлена");
          console.error(result, result.data);
        });
    }

    $scope.discountCancelHandler = function (scope) {
      scope.closeThisDialog();
    }

    $scope.editDiscount = function (scope) {// todo покупатель и продавец пока не передаются
      $scope.entityId=scope.discount.id;
      $scope.discountParams.actualFrom = scope.discount.actualFrom;
      $scope.discountParams.actualTo = scope.discount.actualTo;
      $scope.discountParams.amountDiscount = scope.discount.amountDiscount;
      ngDialog.open({ template: 'app/discount/editDiscount.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    }

    $scope.editOkDiscountHandler = function (scope) {
      $scope.discountParams.id = scope.$parent.entityId;
      $scope.discountParams.actualFrom=UtilsFunctionsFactory.dateStringToMillis($scope.discountParams.actualFrom);
      $scope.discountParams.actualTo=UtilsFunctionsFactory.dateStringToMillis($scope.discountParams.actualTo);
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/updateDiscount",
        params:  $scope.discountParams
      }).then(function (resp) {
         console.log("Скидка обновлена");
          console.log("Success resp1", resp);
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Скидка не обновлена");
          console.error(result, result.data);
        });
    }

    $scope.deleteDiscount = function (scope) {
      var deletedId = scope.discount.id;
      $http({
        method: "DELETE",
        url: "http://localhost:8080/crudGoods/rest/deleteDiscount",
        params: {
          id:deletedId
        }
      }).then(function (resp) {

          console.log("Success resp1", resp);
          console.log("Скидка удалена");
          $state.reload();
        },
        function (result) {
          console.log("Скидка не удалена");
          console.error(result);
        });
    }
  }
})();
