 angular.module("myApp", []).controller("productController", function ($scope, $http) {
   
   $scope.products =[];
   $http.get(`/rest/product`).then(resp =>{
     $scope.products = resp.data;
   },
   function(error){
     console.error(error);
   });
   
   $scope.priceFilter =0;
    $scope.updatePriceFilter = function() {
    $scope.priceFilter = { price: { $gte: $scope.priceFilter } };
  };
   });  