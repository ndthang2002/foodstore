var app = angular.module("appHome", []);
app.controller("ctrl-home", function($scope, $http) {
  
  $scope.donhang;
  $scope.nhanvien;
  $scope.sanpham;
  console.log('sdfsa');
  $http.get(`/rest/home/sodonhang`).then(resp =>{
    $scope.donhang = resp.data;
    console.log(resp.data);
  })
  
   $http.get(`/rest/home/sonhanvien`).then(resp =>{
    $scope.nhanvien = resp.data;
     console.log(resp.data);
  })
  
   $http.get(`/rest/home/sosanpham`).then(resp =>{
    $scope.sanpham = resp.data;
     console.log(resp.data);
  })
  });