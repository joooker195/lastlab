(function () {
  angular
    .module('frontend')
    .controller('ItemController', ItemController);

  /** @ngInject */
  function ItemController($scope,$http,UtilsFunctionsFactory,ngDialog,$state,allProducts) {
    var vm  =this;
     $scope.items = allProducts.data;
    vm.UtilsFunctionsFactory = UtilsFunctionsFactory;
    var discountMas = [];
    for(var i = 0;i<$scope.items.length;i++){
      var discounts = $scope.items[i].discounts;
      console.log(discounts);
      for(var j =0;j<discounts.length;j++){
        var actualFromDate = UtilsFunctionsFactory.toDate(discounts[j].actualFrom);
        var actualToDate = UtilsFunctionsFactory.toDate(discounts[j].actualTo);
        discounts[j].actualFrom = actualFromDate;
        discounts[j].actualTo = actualToDate;
        discountMas.unshift(discounts[j])
      }
    }

    $scope.discountsMas= discountMas;
    $scope.showAddDiv=false;


    var allSales =$http.get("http://localhost:8080/crudGoods/rest/getAllSells");
    -      allSales.then(fulfilled, rejected);

    function fulfilled(resp) {
      console.log(resp.data);
      for (var i =0;i<resp.data.length;i++){
        resp.data[i].orderDate = UtilsFunctionsFactory.toDate(resp.data[i].orderDate);
        resp.data[i].deliveryDate = UtilsFunctionsFactory.toDate(resp.data[i].deliveryDate);
      }
      $scope.sells = resp.data;
      console.log("Продажи подгружены");
    }

    function rejected(error) {
      console.log("Продажи не подгружены");
      console.log(error);
    }

    $scope.params = {
      name: "",
      unitCoast: "",
      unitName: "",
      selectedSale:0
    };

    $scope.modalShown = false;

    $scope.addItem = function() {
      $scope.params.name="";
      $scope.params.unitCoast="";
      $scope.params.unitName="";
      $scope.params.selectedSale=0;
      ngDialog.open({ template: 'app/item/addItem.html',
       className: 'ngdialog-theme-default',
       scope: $scope
      });
    };

    $scope.editOkHandler = function (scope) {
      $scope.params.id = scope.$parent.entityId;
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/updateProduct",
        params:  $scope.params
      }).then(function (resp) {
          console.log("Success resp1", resp);
          console.log("Продукт  обновлен");
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
        console.log("Продукт не обновлен");
          console.error(result, result.data);
        });
    };

    /*
    * Создание нового товара
    * */
    $scope.dialogOkHandler = function (dialogScope) {
      console.log( $scope.params);
      var dataObj =$scope.params;
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/saveProduct",
        params: dataObj
      }).then(function (resp) {
        console.log("Продукт сохранен");
          console.log("Success resp1", resp);
          dialogScope.closeThisDialog();
          $state.reload();
        },
        function (result) {
        console.log("Продукт не создан");
          console.error(result, result.data);
        });
    }

    $scope.dialogCancelHandler = function (scope) {/*scope диалога*/
      scope.closeThisDialog()
    }

    $scope.editProductHandler = function (scope) {
      console.log(scope);
      $scope.entityId=scope.item.id;
      $scope.params.name=scope.item.name;
      $scope.params.unitCoast = scope.item.unitCoast;
      $scope.params.unitName=scope.item.unitName;

      ngDialog.open({ template: 'app/item/editProduct.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.deleteProductHandler = function (scope) {
      var deletedId= scope.item.id;
      console.log(deletedId);
      $http({
        method: "DELETE",
        url: "http://localhost:8080/crudGoods/rest/removeProduct",
        params: {
          id:deletedId
        }
      }).then(function (resp) {
          console.log("Продукт удален");
          console.log("Success resp1", resp);
          $state.reload();
        },
        function (result) {
          console.log("Продукт  не удален");
          console.error(result);
        });
    }


    $scope.showHide1 = function () {
      this.showAddDiv = !this.showAddDiv;
    }
  }
})();
