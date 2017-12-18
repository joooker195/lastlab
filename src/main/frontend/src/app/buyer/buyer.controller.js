(function () {
  angular
    .module('frontend')
    .controller('BuyerController', BuyerController);

  /** @ngInject */
  function BuyerController($scope, $http, $state,$interval, $location,ngDialog,UtilsFunctionsFactory,allBuyers) {
    var vm = this;
    vm.UtilsFunctionsFactory = UtilsFunctionsFactory;
    for (var i = 0;i<allBuyers.data.length;i++){
      allBuyers.data[i].birthDate = UtilsFunctionsFactory.toDate(allBuyers.data[i].birthDate);
    }
    $scope.buyres = allBuyers.data;
    var discountMas = [];
    for(var i = 0;i<$scope.buyres.length;i++){
      var discounts = $scope.buyres[i].discounts;
      console.log(discounts);
      for(var j =0;j<discounts.length;j++){
        var actualFromDate = UtilsFunctionsFactory.toDate(discounts[j].actualFrom);
        var actualToDate = UtilsFunctionsFactory.toDate(discounts[j].actualTo);
        discounts[j].actualFrom = actualFromDate;
        discounts[j].actualTo = actualToDate;
        discountMas.unshift(discounts[j])
      }
    }
    $scope.discountsMas=discountMas;
    $scope.showAddDiv = false;

    $scope.buyerParams={
      firstName :"",
      middleName :"",
      lastName:"",
      phoneNumber :"",
      birthDate :"",
      livingAddress:""
    };


      // var promise = $http.get($location.protocol() + '://' + $location.host() + ':'+ $location.port() + "/crudGoods/data/buyers.json");
      //  var promise = $http.get("../../data/buyers.json");
    $scope.showBuyerDialog = function() {
      $scope.buyerParams.firstName="";
      $scope.buyerParams.middleName="";
      $scope.buyerParams.lastName="";
      $scope.buyerParams.phoneNumber="";
      $scope.buyerParams.birthDate="";
      $scope.buyerParams.livingAddress="";
      ngDialog.open({ template: 'app/buyer/addBuyer.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.buyerOkClickHandler = function (scope) {
      $scope.buyerParams.birthDate = UtilsFunctionsFactory.dateStringToMillis($scope.buyerParams.birthDate);
      console.log($scope.buyerParams);
      $http({
       // method: "POST",
        url: "http://localhost:8080/crudGoods/rest/addBuyer",
        params: $scope.buyerParams
      }).then(function (resp) {
         console.log("Покупатель добавлен");
          console.log("Success resp", resp);
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Покупатель не добавлен");
          console.log($scope.buyerParams);
          console.error(result, result.data);
        });
    };

    $scope.closeBuyerAddDialog = function (scope) {
      scope.closeThisDialog();
    }

    $scope.editBuyer = function (tableScope) {
      $scope.entityId=tableScope.buyer.id;
      $scope.buyerParams.firstName=tableScope.buyer.firstName;
      $scope.buyerParams.middleName=tableScope.buyer.middleName;
      $scope.buyerParams.lastName=tableScope.buyer.lastName;
      $scope.buyerParams.birthDate=tableScope.buyer.birthDate;
      $scope.buyerParams.livingAddress=tableScope.buyer.livingAddress;
      $scope.buyerParams.phoneNumber=tableScope.buyer.phoneNumber;
      ngDialog.open({ template: 'app/buyer/editBuyer.html',
        className: 'ngdialog-theme-default',
        scope: $scope
      });
    };

    $scope.editOkBuyerHandler = function (scope) {
      debugger;
      $scope.buyerParams.id = scope.$parent.entityId;
      $scope.buyerParams.birthDate = UtilsFunctionsFactory.dateStringToMillis($scope.buyerParams.birthDate);
      $http({
      //  method: "POST",
        url: "http://localhost:3000/crudGoods/rest/updateBuyer",
        params: $scope.buyerParams
      }).then(function (resp) {
          console.log("Покупатель обновлен");
          console.log("Success resp", resp);
          scope.closeThisDialog();
          $state.reload();
        },
        function (result) {
          console.log("Покупатель  не обновлен");
          console.error(result, result.data);
        });
    }

    $scope.deleteBuyer = function (scope) {
      var buyerId = scope.buyer.id;
      $http({
        method: "DELETE",
        url: "http://localhost:8080/crudGoods/rest/deleteBuyer",
        params:{
          id:buyerId
        }
      }).then(function (resp) {
          console.log("Покупатель удален");
          console.log("Success resp", resp)
          $state.reload();
        },
        function (result) {
          console.log("Покупатель не удален");
          console.error(result, result.data);
        });
    };

    $scope.showHide1 = function () {
      this.showAddDiv = !this.showAddDiv;
    }
  }

})();
// gttp data - тело запроса bodyParam
