var app = angular.module("appNhanvien", []);
app.controller("ctrl-nhanvien", function($scope, $http) {
  $scope.listRole = [];
  $scope.listAccount = [];
  $scope.checkedit=true;
  $scope.initial = function() {
    //get role 
    $http.get(`/rest/allrole`).then(resp => {
      $scope.listRole = resp.data;
    })
    $http.get(`/rest/nhanvien/getlist`).then(resp => {
      $scope.listAccount = resp.data;
    }).catch(resp => {
    })

  }
  $scope.initial();

  //cho form delay 1s
  /*  function submitForm(event) {
    event.preventDefault(); // ngăn chặn sự kiện submit mặc định của form
    setTimeout(function() {
      event.target.submit(); // submit form sau khi đợi 2 giây
    }, 2000);
  }*/

  //lay duong dan anh 
  /*$scope.image;
  $scope.onFileSelect = function($files) {
    $scope.image = $files[0];
  };*/

  // lay id dia chi 
  $scope.cityId;
  $scope.districtId;
  $scope.wardId;
  $scope.cityChoose = function(cityid) {
    $scope.districtChoose = function(districtid) {
      $scope.wardChoose = function(wardid) {
        $scope.cityId = cityid;
        $scope.districtId = districtid;
        $scope.wardId = wardid;
      }
    }
  }

  // lay roleid 
  $scope.roleId;
  $scope.roleChoose = function(roleId) {
    $scope.roleId = roleId;
  }

  console.log($scope.selectedFile);
  /* $scope.create = function() {
     console.log("chay di con");
     console.log($scope.image);
     $scope.nhanvien = {
       "username": $scope.username,
       "password": $scope.password,
       "image": $scope.image,
       "name": $scope.fullname,
       "email": $scope.email,
       "phone": $scope.phone
     }
 
     //lay dia chi 
     $scope.diachi = {
       "cityid": $scope.cityId,
       "districtid": $scope.districtId,
       "wardid": $scope.wardId
     }
     
     // gui yeu cau insert nhan vien 
     var data = {
       account: { ...$scope.nhanvien },
       address: { ...$scope.diachi },
   
     }
     $http.post(`/rest/nhanvien/create/${$scope.roleId}`, data
     ).then(resp => {
       console.log(resp.data);
       $scope.listAccount.add(resp.data);
   })
   }*/

  //ham reset 
  $scope.reset = function() {
      $scope.checkedit=true;
    $scope.username = "";
    $scope.password = "";
    $scope.image = "";
    $scope.selectedRole = "";
    $scope.email = "";
    $scope.selectedCity = "";
    $scope.selectedDistrict = "";
    $scope.selectedWard = "";
    $scope.phone = "";
    $scope.fullname = "";
  }

  //ham edit
  $scope.idUser;
  $scope.index;
  $scope.edit = function(id, index) {
      $scope.checkedit=false;
    console.log(id);
    var item = $scope.listAccount.find(item => item.userid == id);
    $scope.username = item.username;
    $scope.password = item.password;
    $scope.image = item.image;
    $scope.selectedRole = item.selectedRole;
    $scope.email = item.email;
    $scope.selectedCity = item.selectedCity;
    $scope.selectedDistrict = item.selectedDistrict;
    $scope.selectedWard = item.selectedWard;
    $scope.phone = item.phone;
    $scope.fullname = item.name;

    //gui id len cho delete button 
    $scope.idUser = id;
    $scope.index = index;

  

    //lay role
    $http.get(`/rest/nhanvien/getAuthorities/${id}`).then(resp => {
      $scope.selectedRole = resp.data.role.roleid;
    })
  }
  //xoa
  $scope.delete = function(id, index) {
    $http.delete(`/rest/nhanvien/deleteAccount/${id}`).then(resp => {
    })
    $scope.listAccount.splice(index, 1);

  }
  $scope.file1 =null;
    $scope.setFile = function(files, index) {
    if (index == 1) {
      $scope.file1 = files[0];
      console.log($scope.file1);
    }
  };
  $scope.update = function(idUser){
    //lay anh
   console.log($scope.file1);
    var formData = new FormData();
     formData.append("image", $scope.file1);
     console.log(formData);
      $scope.nhanvien = {
       "username": $scope.username,
       "password": $scope.password,
       "image": $scope.image,
       "name": $scope.fullname,
       "email": $scope.email,
       "phone": $scope.phone
     }
     $scope.role ={
       "roleid":$scope.selectedRole,
     }
 
     //lay dia chi 
     $scope.diachi = {
       "cityid": $scope.cityId,
       "districtid": $scope.districtId,
       "wardid": $scope.wardId
     }
      var data = {
       account: { ...$scope.nhanvien },
       address: { ...$scope.diachi },
       role:{...$scope.role},
   
     }
       $http.post(`/rest/nhanvien/update/${idUser}`, data
      
     ).then(resp => {
        console.log(resp.data);
        var item = resp.data;
      var index =  $scope.listAccount.findIndex(i=>i.userid == item.userid);
       $scope.listAccount.splice(index, 1, item);
      
       
   })
      
  }

  //dia chi
  console.log($scope.selectedCity);
  console.log($scope.cartdb);
  console.log($scope.vnpaycheck);
  /* hiển thị địa chỉ và tính toán tiền vận chuyển */
  //thanh pho
  $scope.citys = [];
  $scope.districts = [];
  $scope.wards = [];
  $scope.shippingServices = [];
  $scope.serviceFees = [];

  //cac ma thanh pho huyen xa
  $scope.cityId;
  $scope.districtId;
  $scope.wardId;

  //lay thanh pho
  $http({
    method: 'GET',
    url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/province',
    headers: {
      'token': 'e1eab2eb-daac-11ed-9eaf-eac62dba9bd9',
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  }).then(function successCallback(response) {
    // Xử lý kết quả trả về từ API
    $scope.citys = response.data.data;
  }, function errorCallback(response) {
    console.log("loi");
    // Xử lý lỗi trả về từ API
  });



  //lay ra huyen theo thanh pho
  $scope.cityChoose = function(cityId) {
    //lay id thanh pho
    $scope.cityId = cityId;
    $http({
      method: 'GET',
      url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=' + cityId,
      headers: {
        'token': 'e1eab2eb-daac-11ed-9eaf-eac62dba9bd9',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    }).then(resp => {
      $scope.districts = resp.data.data;

      // lay phuong xa theo quan 

      $scope.districtChoose = function(districtId) {
        //lay ma huyen
        $scope.districtId = districtId;
        $http({
          method: 'GET',
          url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=' + districtId,
          headers: {
            'token': 'e1eab2eb-daac-11ed-9eaf-eac62dba9bd9',
            'Content-Type': 'application/json',
            'Accept': 'application/json'
          }
        }).then(resp => {
          $scope.wards = resp.data.data;


        }).catch(resp => {
          console.log("lỗi khi lấy xa ")
        });
      }
    }).catch(resp => {
      console.log("lỗi khi lấy huyện ")
    });

  };



});