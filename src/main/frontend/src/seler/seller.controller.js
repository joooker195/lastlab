(function () {
  angular
    .module('frontend')
    .controller('SellersController', SellersController);

  /** @ngInject */
  function SellersController($scope, $http, ngDialog, UtilsFunctionsFactory, allSellers,$state) {

    for (var i = 0; i < allSellers.data.length; i++) {
      console.log(allSellers.data);
      var orderDate = UtilsFunctionsFactory.toDate(allSellers.data[i].sale.orderDate);// из продажи
      var deliveryDate = UtilsFunctionsFactory.toDate(allSellers.data[i].sale.deliveryDate);// из продажи
      allSellers.data[i].birthDate = UtilsFunctionsFactory.toDate(allSellers.data[i].birthDate);
      allSellers.data[i].orderDate = orderDate;// данные о продажк берутся с объекта продажа и сохраняются в качесчтве значений полей объекта "продавец"
      allSellers.data[i].deliveryDate = deliveryDate;
    }

    $scope.sellers = allSellers.data;

    $scope.sellerParams = {
      firstName: "",
      middleName: "",
      lastName: "",
      birthDate: "",
      email: "",
      deliveryAddress: "",
      saleSelect:0
    };

    var allSale =$http.get("http://localhost:8080/crudGoods/rest/getAllSells");
    -      allSale.then(fulfilled, rejected);

    function fulfilled(resp) {
      console.log(resp.data);
      for (var i =0;i<resp.data.length;i++){
        resp.data[i].orderDate = UtilsFunctionsFactory.toDate(resp.data[i].orderDate);
        resp.data[i].deliveryDate = UtilsFunctionsFactory.toDate(resp.data[i].deliveryDate);
      }
      console.log("Продажи подгружены");
      $scope.sells = resp.data;
    }

    function rejected(error) {
      console.log("Продажи не подгружены");
      console.log(error);
    }

    $scope.addNewSeller = function () {
      $scope.sellerParams.firstName="";
      $scope.sellerParams.middleName="";
      $scope.sellerParams.lastName="";
      $scope.sellerParams.birthDate="";
      $scope.sellerParams.email="";
      $scope.sellerParams.deliveryAddress="";
      $scope.sellerParams.saleSelect="";
      ngDialog.open({
        template: 'app/seler/addSellerDialog.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.addSellerOkHandler = function (dialogScope) {
      $scope.sellerParams.birthDate = UtilsFunctionsFactory.dateStringToMillis($scope.sellerParams.birthDate);
      console.log($scope.sellerParams);
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/addSeller",
        params:  $scope.sellerParams
      }).then(function (resp) {
          console.log("Продавец добавлен");
          console.log("Success resp", resp);
          dialogScope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Продавец не добавлен");
          console.error(result, result.data);
        });
    };

    $scope.addSellerCancelClickHandler = function (scope) {
      scope.closeThisDialog();
    }

    $scope.editSeller = function (scope) {
      $scope.entityId=scope.seller.id;
      $scope.sellerParams.firstName=scope.seller.firstName;
      $scope.sellerParams.middleName = scope.seller.middleName;
      $scope.sellerParams.lastName=scope.seller.lastName;
      $scope.sellerParams.birthDate=scope.seller.birthDate;
      $scope.sellerParams.email=scope.seller.email;
      $scope.sellerParams.deliveryAddress=scope.seller.deliveryAddress;
      ngDialog.open({
        template: 'app/seler/editSellerDialog.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.editSellerOkClickHandler = function (dialogScope) {
      debugger;
      $scope.sellerParams.id = dialogScope.$parent.entityId;
      $scope.sellerParams.birthDate = UtilsFunctionsFactory.dateStringToMillis($scope.sellerParams.birthDate);
      console.log($scope.sellerParams);
      $http({
        method: "POST",
        url: "http://localhost:8080/crudGoods/rest/updateSeller",
        params:  $scope.sellerParams
      }).then(function (resp) {
          console.log("Продавец обновлен");
          console.log("Success resp", resp);
          dialogScope.closeThisDialog();
         $state.reload();
        },
        function (result) {
          console.log("Продавец не обновлен");
          console.error(result, result.data);
        });
    };

    $scope.deleteSeller = function (scope) {
      var deletedId = scope.seller.id;
      $http({
        method: "DELETE",
        url: "http://localhost:8080/crudGoods/rest/deleteSeller",
        params: {
          id:deletedId
        }
      }).then(function (resp) {
        debugger;
          console.log("Продавец удален");
          console.log("Success resp1", resp);
          $state.reload();
        },
        function (result) {
        debugger;
          console.log("Продавец не удален");
          console.error(result);
        });
    }
  }
})();
