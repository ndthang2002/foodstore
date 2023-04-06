



angular.module("myApp", []).controller("myCtrl", function($scope, $http) {
  $scope.all = 'Tổng thanh toán (0 sản phẩm ):S 0';
  let payArr = [];
  let idProduct;
  
  $scope.cart = {

    items: [],
    /*them san pham vo*/
    add(id) {
      /*var item = this.items.find(item => item.productid == id);
      if (item) {
        item.quantity++;
        this.saveToLocalStorage();
      } else {
        $http.get(`/rest/products/${id}`).then(resp => {
          resp.data.quantity = 1;
          this.items.push(resp.data);
          this.saveToLocalStorage();
        }).catch(error => {
          console.log("K tìm dc", error);
        });
      }*/
      /*đã ok nhưng bị lỗi do cors */
      $http.get(`/rest/addtocart/${id}`);
    },

    chonsanpham(id) {
      this.idProduct = id;
      if ($scope.ischecked) {
        console.log("checkbox is ok ");
      } else {
        console.log("checkbox is not ");
      }

    },
    remove(id) {
      var index = this.items.findIndex(item => item.productid == id);
      this.items.splice(index, 1);
      this.saveToLocalStorage();
      if (this.items.length == 0) {
        localStorage.removeItem("cart");
      }
    },
    cleartCart() {
      localStorage.removeItem("cart");
      this.loadFromLocalStorage();
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
    loadFromLocalStorage() {
      var json = localStorage.getItem("cart");
      this.items = json ? JSON.parse(json) : [];
    }
    , cong(id) {
      $scope.congdisable = true;
      var item = this.items.find(item => item.productid == id);
      item.quantity++;
      if (item.quantity >= 50) {
        item.quantity = 50;
      }
      this.saveToLocalStorage();
    }, tru(id) {
      $scope.congdisable = true;
      var item = this.items.find(item => item.productid == id);

      item.quantity--;
      if (item.quantity <= 0) {
        item.quantity = 0;
      }
      this.saveToLocalStorage();
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


  };
  $scope.cart.loadFromLocalStorage();
  $scope.disabledFlag = false;
  if ($scope.all == true) {
    $scope.disabledFlag = true;
  }

  

});


