
app.controller("myCtrl-order", function($scope, $http, $window,$timeout) {
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

          //lay dich vu
          // kho mặc định nằm ở quận hà đông hà nội

          $http({
            method: 'GET',
            url: 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services',
            params: { shop_id: '4029744', from_district: '1542', to_district: districtId },
            headers: {
              'token': 'e1eab2eb-daac-11ed-9eaf-eac62dba9bd9',
              'Content-Type': 'application/json',
              'Accept': 'application/json'
            }
          }).then(resp => {
            $scope.shippingServices = resp.data.data;

            //tinh gia cuoc van chuyen 
            $scope.wardChoose = function(wardId) {
              console.log("vao chon xa");
              console.log(wardId, "thanf");
              $scope.serviceChoose = function(serviceId) {
                console.log(wardId);
                $http({
                  method: 'GET',
                  url: 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee',
                  params: {
                    "service_id": serviceId,
                    "insurance_value": 500000,
                    "coupon": null,
                    "from_district_id": 1542,
                    "to_district_id": districtId,
                    "to_ward_code": wardId,
                    "height": 15,
                    "length": 15,
                    "weight": 1000,
                    "width": 15
                  },
                  headers: {
                    'token': 'e1eab2eb-daac-11ed-9eaf-eac62dba9bd9',
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                  }
                }).then(resp => {
                  $scope.serviceFees = resp.data.data;

                  /*lua vao gio hang */
                   
                  $scope.saveorder = function() {
                    //kiem tra phuong thuc thanh toan 
                 
                      if ($scope.vnpaycheck) {
                        //da check thanh toan online
                        var item = $scope.shippingServices.find(item => item.service_id == serviceId);
                        $http({
                          method: 'POST',
                          url: '/rest/order/allproducttocart',
                          params: {
                            city_Id: cityId,
                            district_Id: districtId,
                            ward_Id: wardId,
                            delivery_name: item.short_name,
                            delivery_fee: $scope.serviceFees.service_fee,
                            payment: "Thanh toán online vnpay"
                          },
                          headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                          }
                        }).then(resp => {
                          console.log("thanhcong.");
                          console.log(resp.data);
                          var payment = {
                            idService: resp.data.orderid,
                            amount: 500000,
                            description: "Thanh toan online",
                            bankcode: "",
                          }
                          var json = JSON.stringify(payment);
                          console.log("lay idorder", resp.data.orderid);
                          $http.post(`/create-payment/${resp.data.orderid}`, json).then(resp => {
                            console.log(resp.data.url);
                            console.log(resp.data.status);
                           $window.location.href = resp.data.url;
                          }).catch(error => {
                            console.log(error);
                          })
                        }).catch(error => {
                          console.log(error.data);
                        });
                     
                      } else {
                        //khong check thanh toan khi nhan hang
                        var item = $scope.shippingServices.find(item => item.service_id == serviceId);
                        $http({
                          method: 'POST',
                          url: '/rest/order/allproducttocart',
                          params: {
                            city_Id: cityId,
                            district_Id: districtId,
                            ward_Id: wardId,
                            delivery_name: item.short_name,
                            delivery_fee: $scope.serviceFees.service_fee,
                            payment: "Thanh toán khi nhận hàng"
                          },
                          headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'application/json'
                          }
                        }).then(resp => {
                          console.log("thanhcong.");
                          $timeout(function(){
                            $window.location.href = 'http://localhost:8080/index';
                          },1500);
                          
                           
                        }).catch(error => {
                          console.log(error.data);
                        });
                        showSuccessToast();
                      }

                    }
                  
                  /*lua vao gio hang */
                });
              }
            }

          }).catch(resp => {
            console.log("lỗi khi lấy services ")
          });
        }).catch(resp => {
          console.log("lỗi khi lấy xa ")
        });
      }
    }).catch(resp => {
      console.log("lỗi khi lấy huyện ")
    });

  };

  /* hiển thị địa chỉ và tính toán tiền vận chuyển */
  // tinh tong tien 
  $scope.sanpham = [];
  $scope.tongtienhang = 0;
  $http({
    method: 'GET',
    url: '/rest/getCartByUserLoged',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    }
  }).then(resp => {
    $scope.sanpham = resp.data;
    for (let i = 0; i < $scope.sanpham.length; i++) {
      $scope.tongtien = $scope.sanpham[i].price * $scope.sanpham[i].quantity;
      $scope.tongtienhang += $scope.tongtien;
    }

  }).catch(error => {

    console.log("loi khi goi api");
  });


  $scope.order = {
    /*   createdate:new Date(),
       fullname:"",
       email:"",
       address:"",
       phone:"",
       account:{username:$("#username").text()},

       get orderdetails(){
           return $scope.cart.items.map(item =>{
               return {
                   product:{id:item.id},
                   price:item.price,
                   quantity:item.quantity,
               }
           });
       },*/

    purchase() {
      this.fullname = $scope.account.fullname;
      this.email = $scope.account.email;
      this.address = $scope.account.address;
      this.phone = $scope.account.phone;
      var order = angular.copy(this);
      var amount = $scope.cart.amount;
      // Thực hiện đặt hàng
      $http.post("/rest/order", order).then(resp => {
        $scope.cart.clear();
        var payment = {
          idService: resp.data.id,
          amount: amount,
          description: "Thanh toan khoa hoc online",
          bankcode: "",
        }
        var json = JSON.stringify(payment);
        $http.post(`/create-payment/${resp.data.id}`, json).then(resp => {
          $window.location.href = resp.data.url;
        }).catch(error => {
          console.log(error);
        })
      }).catch(error => {
        alert('Đặt hàng lỗi!');
        console.log("Error: " + error);
      })
    },
  }

  // toast hien thi xoa thanh cong 
  function showSuccessToast() {
    toast({
      title: "Đặt hàng Thành công !",
      message: "xem chi tiết đơn hàng trong mục đơn mua",
      type: "success",
      duration: 5000
    });
  }
  // Toast function
  function toast({ title = "", message = "", type = "", duration = "" }) {
    const main = document.getElementById("toast");
    if (main) {
      const toastt = document.createElement("div");

      // Auto remove toast
      const autoRemoveId = setTimeout(function() {
        main.removeChild(toastt);
      }, duration + 1000);

      // Remove toast when clicked
      toastt.onclick = function(e) {
        if (e.target.closest(".toast__close")) {
          main.removeChild(toastt);
          clearTimeout(autoRemoveId);
        }
      };
      const icons = {
        success: "fas fa-check-circle"
      };
      const icon = icons[type];
      const delay = (duration / 1000).toFixed(2);

      toastt.classList.add("toastt", `toast--${type}`);
      /* toast.style.animation = `slideInLeft ease .5s, fadeOut linear 5s 1s forwards`;*/
      toastt.style.animation = `slideInLeft ease .3s, fadeOut linear ${delay}s 1s forwards`;

      toastt.innerHTML = `
                    <div class="toast__icon">
                        <i class="${icon}"></i>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3>
                        <p class="toast__msg">${message}</p>
                    </div>
                    <div class="toast__close">
                        <i class="fas fa-times"></i>
                    </div>
                `;

      main.appendChild(toastt);
      /* toast.classList.add('show');*/
    }
  }

}); 