const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});


  var app =  angular.module("myApp", []);
  app.controller("myCtrl", function($scope, $http) {
  $scope.quaylaidangnhap = function(){
    console.log("thang");
    $scope.dangnhap = false;
  };
  
  $scope.thaydoimatkhau = function(){
    console.log("thagn");
    $scope.dangnhap=true;
  }
  })

  
  angular.module('myApp').directive('compareTo', function() {
  return {
    require: 'ngModel',
    scope: {
      otherModelValue: '=compareTo'
    },
    link: function(scope, element, attributes, ngModel) {

      ngModel.$validators.compareTo = function(modelValue) {
        return modelValue == scope.otherModelValue;
      };

      scope.$watch('otherModelValue', function() {
        ngModel.$validate();
      });
    }
  };
});