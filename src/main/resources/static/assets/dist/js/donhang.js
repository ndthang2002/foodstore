var app = angular.module("appDonhang", []);
app.controller("ctrl-donhang", function($scope, $http) {
  $scope.orders =[];
  $scope.initial = function() {
    $http.get(`/rest/order/getAllOrder`).then(resp =>{
      $scope.orders = resp.data;
      console.log($scope.orders);
    })
  }

  $scope.initial();
  
  $scope.orderdetai = function(id){
    $http.get(`/rest/order/getOrderdetail/${id}`).then(resp =>{
      $scope.orderitem = resp.data;
    });
  }
})
  console.log("hihi");