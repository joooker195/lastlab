(function () {
  angular
    .module('frontend')
    .controller('ItemController', ItemController);

  function ItemController($scope,$http) {
    var vm  =this;
    $scope.sendRequest = function () {
      var promise = $http.get("../../data/products.json");
      promise.then(fulfilled, rejected)
    };

    function fulfilled(response) {
      console.log(response);
      $scope.items = response.data;
    }

    function rejected(err) {
      console.log(err);
    }

    vm.addProductClickHandler = function () {
      alert("qwe");
    };

    $scope.editProductHandler = function () {
      alert("edit1");
    };

    $scope.deleteProductHandler = function () {
      alert("delete")
    }
  }
})();
