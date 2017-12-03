(function () {
  angular
    .module('frontend')
    .controller('PlaneController', PlaneController);

  function PlaneController($scope,$http) {
    var vm  =this;
    $scope.sendRequest = function () {
      $http({
        method: "GET",
        url: "http://localhost:8080/crudGoods/rest/getAllPlanes",
        params:{
          fileName:"BEN_120.DAT"
        }
      }).then(function (resp) {
          debugger;
          console.log("Профиль", resp)
          $scope.filrnames=resp.data;
        },
        function (result) {
          debugger;
          console.error(result, result.data);
        });
    };

    vm.addProductClickHandler = function ()
    {

    };

    $scope.editProductHandler = function () {
      alert("edit1");
    };

    $scope.deleteProductHandler = function () {
      alert("delete")
    }
  }
})();