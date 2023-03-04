  var app = angular.module('myApp', []);
    app.controller('MyCtrl', function($scope) {
      $scope.name = 'World';
    });

app.directive('headerSection',function(){
  return{
    restrict:'A',
    templateUrl:'layout/header.html'
  };
});
app.directive('footerSection',function(){
  return{
    restrict:'A',
    templateUrl:'layout/footer.html'
  };
}); 