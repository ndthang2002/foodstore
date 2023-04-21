app.controller("myCtrl-account", function($scope, $http, $window) {

  console.log("da ok ");
  $http.get(`/rest/account/loged/`).then(resp => {
    $scope.account = resp.data;
 
  
  }).catch(resp => {

  })


})