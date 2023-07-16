var app = angular.module("appSanpham", []);
app.controller("ctrl-sanpham", function($scope, $http) {
  
  $scope.categorys=[];
  $scope.products =[];
  $scope.checkedit=true;
  $scope.imageChinh;
  $scope.imageF1;
  $scope.imageF2;
  $scope.imageF3;

    $http.get(`/rest/getcategory`).then(resp =>{
      $scope.categorys= resp.data;
      console.log(resp.data);
    });
  
  $http.get(`/rest/getAllProduct`).then(resp =>{
    $scope.products = resp.data;
  });
  
  
  $scope.create = function(){
    console.log($scope.image);
    var file = $scope.image[0];
    console.log(file.name);
  /*  const canvas = $scope.image.getImage();
      const dataURL = canvas.toDataURL();
    console.log(dataURL);*/
  }
  
  $scope.delete = function(id,index){
    var deleted = false;
    $http.get(`/rest/getAllCartProduct`).then(resp1=>{
       for(const order of resp1.data){
         if(order.product.productid === id){}
         deleted=true;
         break;
       }
    $http.get(`/rest/getallcomment`).then(resp2 =>{
      for(const comment of resp2.data){
        if(comment.product.productid === id){
          deleted=true;
          break;
        }
      }
    $http.get(`/rest/orderitem/getallorderitem`).then(resp3 => {
      for(const orderitem of resp3.data){
        if(orderitem.product.productid === id){
          deleted=true;
          break;
        }
      }
      if(!deleted){
         $scope.products.splice(index,1);
    $http.delete(`/rest/delete/${id}`);
      }else{
         alert("Không được xóa sản phẩm này ");
      }
     
     
    });
    });
    });
    
  }
  
  $scope.productid ;
  $scope.index;
  $scope.edit = function(id,index){
    $scope.productid =id;
    $scope.index = index;
    console.log("sadf");
    $scope.checkedit=false;
    var item = $scope.products.find(item => item.productid===id);
    $scope.name = item.name;
    $scope.model =item.model;
    $scope.quantity= item.quantity;
    $scope.price = item.price;
    
    $http.get(`/rest/getCateByIdPro/${id}`).then(resp =>{
      $scope.selectedCa=resp.data.categoryid;
    })
  }
  
   $scope.file1 = null;
  $scope.file2 = null;
  $scope.file3 = null;
   $scope.file4 = null;
    $scope.setFile = function(files, index) {
    if (index == 1) {
      $scope.file1 = files[0];
    } else if (index == 2) {
      $scope.file2 = files[0];
    } else if (index == 3) {
      $scope.file3 = files[0];
    }
    else if (index == 4) {
      $scope.file4 = files[0];
    }
  };
  console.log($scope.file1);
   console.log($scope.file2);
    console.log($scope.file3);
     console.log($scope.file4);
  $scope.update = function(idp){
   console.log("xo say");
    var product ={
    "name":$scope.name,
    "model":$scope.model,
    "quantity":$scope.quantity,
    "price":$scope.price
    }
  /*  $http.post(`/rest/updateProduct/${idp}`,product, imageChinh,
  imageF1,
  imageF2
  imageF3);*/
  
   var formData = new FormData();
if ($scope.file1) {
  formData.append("file1", $scope.file1);
}
if ($scope.file2) {
  formData.append("file2", $scope.file2);
}
if ($scope.file3) {
  formData.append("file3", $scope.file3);
}
if ($scope.file4) {
  formData.append("file4", $scope.file4);
}

    formData.append("name",$scope.name);
    formData.append("model",$scope.model);
    formData.append("quantity",$scope.quantity);
    formData.append("price",$scope.price);
    formData.append("categoryid",$scope.selectedCa)

   $http.post(`/rest/updateProduct/${idp}`, formData, {
      transformRequest: angular.identity,
      headers: {'Content-Type': undefined}
    }).then(function(response) {
      console.log(response.data);
      var item = response.data;
      var index =  $scope.products.findIndex(i=>i.productid == item.productid);
       $scope.products.splice(index, 1, item);
      
      console.log('Upload success: ', response);
    }, function(response) {
      console.error('Upload error: ', response);
    });
    
  }
  
  $scope.reset = function(){
     $scope.checkedit=true;
    $scope.name = "";
    $scope.model ="";
    $scope.quantity= "";
    $scope.price = "";
    
  }
  
  
});