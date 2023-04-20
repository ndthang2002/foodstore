
var app = angular.module("myApp", ['ngRoute']);
app.controller("myCtrl", function($scope, $http, $route, $window, $location) {
  $scope.all = 'Tổng thanh toán (0 sản phẩm ):S 0';
  let payArr = [];
  let idProduct;
  $scope.cartdb = [];
  $scope.cartLength = 0;
  const token='d6e3dccb-6289-11ea-8b85-c60e4edfe802';
  
  //tương tác với các thẻ html 
  let thongbaoaddtocart = document.getElementById(`thongbaoaddtocart`);
  
  //update so luong khi nguoi dung nhap vao input
  
  this.getdata = function() {
    return $http.get('/rest/getCartByUserLoged');
  };

  $http.get(`/rest/checklogin`).then(resp =>{
    $scope.ischeckLoged=resp.data;
  })
  
  function addAllDatatoList() {
    return $scope.cartdb = $scope.cartdb;
    console.log($scope.cartdb);
  }

  function getquantity(cartid) {
    var item = $scope.cartdb.find(item => item.cartid == id);
    return item;
  }
 
  this.getdata().then(function(response) {
    //an dangnhap khi da dang nhap
    /*$scope.checkLoged=false;
    alert($scope.checkLoged);*/
    $scope.cartdb = response.data;
    console.log(response.data);
    $scope.cartLength = $scope.cartdb.length;
    addAllDatatoList($scope.cartdb);
    
    $scope.cart = {

      items: [],
      /*them san pham vo*/
      add(id) {
        $http.get(`/rest/checklogin`).then(resp =>{
          console.log(resp.data);
          if(resp.data === true){
            console.log("deo vo day");
            thongbaoaddtocart.innerText="Thêm sản phẩm thành công";
            $scope.checklogin=true;
            $scope.iconsthongbao=true;
            setTimeout(function(){
  document.getElementById("close").click();
}, 700);
          }
        })
        
        var item = $scope.cartdb.find(item => item.productid == id);
        console.log(item);
        if (item) {
          item.quantity++;
          $http.get(`/rest/addtocart/${id}`);
          console.log("them so luong");
        } else {
          $http.get(`/rest/addtocart/${id}`).then(resp => {
           $scope.cartdb.push(resp.data);
           $scope.cartLength = $scope.cartLength + 1;
          }).catch(error => {
            console.log(error.data);
          });
          console.log("them moi");
        }
        console.log("kkk");
        //$window.location.reload();
      },

      chonsanpham(id) {
        this.idProduct = id;
        if ($scope.ischecked) {
          console.log("checkbox is ok ");
        } else {
          console.log("checkbox is not ");
        }
      },
      
      remove(id, index) {
        //xoa tren list tam
        $scope.cartdb.splice(index, 1);
        //cap nhat so luong san pham 
        $scope.cartLength = $scope.cartdb.length;
        //xoa trong db
        $http.delete(`/rest/deleteCart/${id}`).then(resp => {
          if (resp.status == 200) {
            // alert("xoa thanh cong");
            // $window.location.reload();
          }
        }).catch(error => {
          alert("loi");
        });
        showSuccessToast();
      },
      cleartCart() {
        console.log("hihia");
        $http.delete(`/rest/deletecartall`);
        $scope.cartdb=[];
        $scope.cartLength=0;
        console.log("thang");
      },
      get getCount() {
        var count = this.items
          .map(item => item.quantity)
          .reduce((total, quantity) => total += quantity, 0);
        return count;
      },
      get getAmount() {
        $scope.ischecked = false;
        if ($scope.all == true) {
          console.log("aaa");
          return this.items
            .map(item => item.quantity * item.price)
            .reduce((total, price) => total += price, 0);
        } else {
          $scope.all = false;
        }
      }
      , saveToLocalStorage() {
        var json = JSON.stringify(angular.copy(this.items));
        localStorage.setItem("cart", json);
      },

      /* loadFromLocalStorage() {
          $http.get(`'/rest/getCartByUserLoged'`).then(resp =>{
            this.items = resp.data;
            }).catch(error=>{
           console.log("loi");
         })
       },*/
       
      cong(id) {
        var item = $scope.cartdb.find(item => item.cartid == id);
          item.quantity++;
          $http.put(`updatecart/${id}`,item.quantity).then(rest =>{
          }).catch(error=>{
           
          });
          
      }, tru(id) {
        var item = $scope.cartdb.find(item => item.cartid == id);
          item.quantity--;
           $http.put(`updatecart/${id}`,item.quantity).then(rest =>{
          }).catch(error=>{
           
          });
      },
      
      gettt() {
        let amount = 0;
        $scope.items.forEach(element => {
          if (element.ischecked) {
            amount += element.price * element.quantity;
          }
        });
        return amount;
      },
      get totalAll() {
        return this.items
          .map(item => item.quantity * item.price)
          .reduce((total, price) => total += price, 0);
      },

      /*  getdata (){
            $http.get('/api/data').then(resp =>{
             this.items = resp.data;
             
           })
         }*/


    };
  }).catch(erorr => {
    $scope.checkLoged=false;
    alert($scope.checkLoged);
    console.log("chua dang nhap");
  });

/*  $scope.cart.getdata();*/
/* $scope.cart.loadFromLocalStorage(); 
*/  $scope.disabledFlag = false;
  if ($scope.all == true) {
    $scope.disabledFlag = true;
  }

});

// toast hien thi xoa thanh cong 
function showSuccessToast(){
  toast({
    title:"Thành công !",
    message:"xóa thành công",
    type:"success",
    duration:3000
  });
}
// Toast function
function toast({ title = "", message = "", type = "", duration = "" }) {
  const main = document.getElementById("toast");
  if (main) {
    const toastt = document.createElement("div");

    // Auto remove toast
    const autoRemoveId = setTimeout(function () {
      main.removeChild(toastt);
    }, duration + 1000);
    
    // Remove toast when clicked
    toastt.onclick = function (e) {
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

//cấu hình token api
/*app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push(['$injector', function ($injector) {
        return $injector.get('AuthInterceptor');
    }]);
}]);

app.factory('AuthInterceptor', ['$q', '$rootScope', function ($q, $rootScope) {
    return {
        request: function (config) {
            config.headers = config.headers || {};
            var token = 'e1eab2eb-daac-11ed-9eaf-eac62dba9bd9';
            if (token) {
                config.headers.Authorization = 'Bearer ' + token;
            }
            return config;
        }
    };
}]);
app.factory('User', ['$resource', function ($resource) {
    return $resource('/api/users/:id', { id: '@id' }, {
        update: {
            method: 'GET'
        }
    });
}]);*/
