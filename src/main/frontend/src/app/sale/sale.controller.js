(function () {

  angular
    .module('frontend')
    .controller('SaleController', SaleController);

  /** @ngInject */
  function SaleController($scope, $http,ngDialog,UtilsFunctionsFactory,allSells,$state) {
    var vm = this;
    for (var i = 0;i<allSells.data.length;i++){
      var formattedOrderDate = UtilsFunctionsFactory.toDate(allSells.data[i].orderDate);
      var formattedDeliveryDate = UtilsFunctionsFactory.toDate(allSells.data[i].deliveryDate);
      allSells.data[i].orderDate = formattedOrderDate;
      allSells.data[i].deliveryDate = formattedDeliveryDate;
    }
    $scope.sales = allSells.data;

    var products = [];
    for (var i= 0;i<$scope.sales.length;i++){
      var productsS = $scope.sales[i].products;
      console.log(productsS);
      for(var j =0;j<productsS.length;j++){
        products.unshift(productsS[j]);
      }
    }
    $scope.salesProducts = products;

    var promise =$http.get("http://localhost:8080/crudGoods/rest/getAllBuyers");
    -      promise.then(fulfilled, rejected);

    function fulfilled(resp) {
      console.log(resp.data);
      for (var i =0;i<resp.data.length;i++){
        resp.data[i].birthDate = UtilsFunctionsFactory.toDate(resp.data[i].birthDate);
      }
      console.log("Покупатели подгружены");
      $scope.salesBuyers =  resp.data;
    }

    function rejected(error) {
      console.log("Покупатели не подгружены");
      console.log(error);
    }

    /*свойства для диалога*/
    $scope.saleParams = {
      orderDate:"",
      deliveryDate : "",
      amountProduct:"",
      selectedBuyer:[]
    };
    $scope.showAddDiv = false;


    $scope.addNewSale = function () {
      $scope.saleParams.orderDate="";
      $scope.saleParams.deliveryDate="";
      $scope.saleParams.amountProduct="";
      ngDialog.open({ template: 'app/sale/addSaleDialog.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.dialogOkClickHandler = function (scope) {
      $scope.saleParams.orderDate = UtilsFunctionsFactory.dateStringToMillis($scope.saleParams.orderDate);
      $scope.saleParams.deliveryDate = UtilsFunctionsFactory.dateStringToMillis($scope.saleParams.deliveryDate);
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/addSale",
        params: $scope.saleParams
      }).then(function (resp) {
         console.log("Продажа добавлена");
          console.log("Success resp1", resp);
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Продажа не добавлена");
          console.error(result, result.data);
        });
    };

    $scope.dialogCancelHandler = function (scope) {
      scope.closeThisDialog()
    };

    $scope.editSale = function (saleScope) {
      $scope.entityId=saleScope.sale.id;
      $scope.saleParams.orderDate=saleScope.sale.orderDate;
      $scope.saleParams.deliveryDate=saleScope.sale.deliveryDate;
      $scope.saleParams.amountProduct=saleScope.sale.amountProduct;
      ngDialog.open({ template: 'app/sale/editSaleDialog.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.editSaleOkClickHandler = function (scope) {
      $scope.saleParams.id = scope.$parent.entityId;
      $scope.saleParams.orderDate = UtilsFunctionsFactory.dateStringToMillis($scope.saleParams.orderDate);
      $scope.saleParams.deliveryDate = UtilsFunctionsFactory.dateStringToMillis($scope.saleParams.deliveryDate);
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/updateSale",
        params: $scope.saleParams
      }).then(function (resp) {
          console.log("Продажа обновлена");
          console.log("Success resp1", resp);
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Продажа не обновлена");
          console.error(result, result.data);
        });
    };

    $scope.deleteSale = function (scope) {
      var saleId = scope.sale.id;
      console.log(saleId);
      $http({
        method: "DELETE",
        url: "http://localhost:8080/crudGoods/rest/deleteSale",
        params: {
          id:saleId
        }
      }).then(function (resp) {
          console.log("Продажа удалена");
          console.log("Success resp1", resp)
          $state.reload();
        },
        function (result) {
        console.log("Продажа не удалена");
          console.error(result, result.data);
        });
    }

    $scope.showAddDiv = function () {
      this.showAddDiv = !this.showAddDiv;
    }
  }
})();
