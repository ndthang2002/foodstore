/*
 * Author: Abdullah A Almsaeed
 * Date: 4 Jan 2014
 * Description:
 *      This is a demo file used only for the main dashboard (index.html)
 **/

/* global moment:false, Chart:false, Sparkline:false */

var app = angular.module("appHome", []);
app.controller("ctrl-home", function($scope, $http) {

  $scope.raucu = 0;
  $scope.tinhbot = 0;
  $scope.thitca = 0;

  //load thống kê các loại bán chạy





  $(function() {
    'use strict'

    // Make the dashboard widgets sortable Using jquery UI
    $('.connectedSortable').sortable({
      placeholder: 'sort-highlight',
      connectWith: '.connectedSortable',
      handle: '.card-header, .nav-tabs',
      forcePlaceholderSize: true,
      zIndex: 999999
    })
    $('.connectedSortable .card-header').css('cursor', 'move')

    // jQuery UI sortable for the todo list
    $('.todo-list').sortable({
      placeholder: 'sort-highlight',
      handle: '.handle',
      forcePlaceholderSize: true,
      zIndex: 999999
    })

    // bootstrap WYSIHTML5 - text editor
    $('.textarea').summernote()

    $('.daterange').daterangepicker({
      ranges: {
        Today: [moment(), moment()],
        Yesterday: [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
        'This Month': [moment().startOf('month'), moment().endOf('month')],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
      },
      startDate: moment().subtract(29, 'days'),
      endDate: moment()
    }, function(start, end) {
      // eslint-disable-next-line no-alert
      alert('You chose: ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
    })

  
   
   

    // The Calender
    $('#calendar').datetimepicker({
      format: 'L',
      inline: true
    })

    // SLIMSCROLL FOR CHAT WIDGET
    $('#chat-box').overlayScrollbars({
      height: '250px'
    })

 
  //thong ke don hang theo thang
    $http.get(`/rest/order/getAllOrder`).then(resp=>{
       $scope.t1=0;
      $scope.t2=0;
      $scope.t3=0;
      $scope.t4=0;
      $scope.t5=0;
      $scope.t6=0;
      $scope.t7=0;
      $scope.t8=0;
      $scope.t9=0;
      $scope.t10=0;
      $scope.t11=0;
      $scope.t12=0;
      console.log($scope.t5);
      for(const order of resp.data){
      var date = new Date(order.bookingdate);
      var month = date.getMonth()+1;
      console.log(month);
      switch(month){
          case 1:
          $scope.t1+=1;
          break;
          case 2:
          $scope.t2+=1;
          break;
          case 3:
          $scope.t3+=1;
          break;
          case 4:
          $scope.t4+=1;
          break;
          case 5:
          $scope.t5+=1;
          break;
          case 6:
          $scope.t6+=1;
          break;
          case 7:
          $scope.t7+=1;
          break;
          case 8:
          $scope.t8+=1;
          break;
          case 9:
          $scope.t9+=1;
          break;
          case 10:
          $scope.t10+=1;
          break;
          case 11:
          $scope.t11+=1;
          break;
          case 12:
          $scope.t12+=1;
          break;
          default:
          console.log("khong co don hang nao");
        }
    
      }
      $scope.t1=($scope.t1/12)*100;
      $scope.t2=($scope.t2/12)*100;
      $scope.t3=($scope.t3/12)*100;
      $scope.t4=($scope.t4/12)*100;
      $scope.t5=($scope.t5/12)*100;
      $scope.t6=($scope.t6/12)*100;
      $scope.t7=($scope.t7/12)*100;
      $scope.t8=($scope.t8/12)*100;
      $scope.t9=($scope.t9/12)*100;
      $scope.t10=($scope.t10/12)*100;
      $scope.t11=($scope.t11/12)*100;
      $scope.t12=($scope.t12/12)*100;
      console.log($scope.t5);
      console.log($scope.t1);
     
    /* Chart.js Charts */
    // Sales chart
    var salesChartCanvas = document.getElementById('revenue-chart-canvas').getContext('2d')
    // $('#revenue-chart').get(0).getContext('2d');

     
    var salesChartData = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July','August','September','October','November','December'  ],
      datasets: [
        {
          label: 'Digital Goods',
          backgroundColor: 'rgba(60,141,188,0.9)',
          borderColor: 'rgba(60,141,188,0.8)',
          pointRadius: false,
          pointColor: '#3b8bba',
          pointStrokeColor: 'rgba(60,141,188,1)',
          pointHighlightFill: '#fff',
          pointHighlightStroke: 'rgba(60,141,188,1)',
          data: [$scope.t1, $scope.t2, $scope.t3, $scope.t4, $scope.t5, $scope.t6, $scope.t7,$scope.t8,$scope.t9,$scope.t10,$scope.t11,$scope.t12]
        }
      /*  ,
        {
          label: 'Electronics',
          backgroundColor: 'rgba(210, 214, 222, 1)',
          borderColor: 'rgba(210, 214, 222, 1)',
          pointRadius: false,
          pointColor: 'rgba(210, 214, 222, 1)',
          pointStrokeColor: '#c1c7d1',
          pointHighlightFill: '#fff',
          pointHighlightStroke: 'rgba(220,220,220,1)',
          data: [100, 59, 80, 81, 56, 55, 40,21,32,80,13,20]
        }*/
      ]
    }
    
    var salesChartOptions = {
      maintainAspectRatio: false,
      responsive: true,
      legend: {
        display: false
      },
      scales: {
        xAxes: [{
          gridLines: {
            display: false
          }
        }],
        yAxes: [{
          gridLines: {
            display: false
          }
        }]
      }
    }
    
      // This will get the first returned node in the jQuery collection.
      // eslint-disable-next-line no-unused-vars
      var salesChart = new Chart(salesChartCanvas, { // lgtm[js/unused-local-variable]
        type: 'line',
        data: salesChartData,
        options: salesChartOptions
      })

      });
    // thong ke loai san pham
    $http.get(`/rest/orderitem/getallorderitem`).then(resp => {
      var listOrderItem = resp.data;
      for (const orderItem of listOrderItem) {
        console.log(orderItem.product.category.categoryid);
        if (orderItem.product.category.categoryid === 2) {
          $scope.thitca += 1;
          console.log($scope.thitca);
        } else if (orderItem.product.category.categoryid === 3) {
          $scope.raucu += 1;
          console.log($scope.raucu);
        }
        else if (orderItem.product.category.categoryid === 4) {
          $scope.tinhbot += 1;
          console.log($scope.tinhbot);
        }
      }

      var thitca = Math.floor(($scope.thitca / 3) * 100);
      var raucu = Math.floor(($scope.raucu / 3) * 100);
      var tinhbot = Math.floor(($scope.tinhbot / 3) * 100);

      console.log(thitca);
      console.log(tinhbot);
      console.log(raucu);

      // Donut Chart
      var pieChartCanvas = $('#sales-chart-canvas').get(0).getContext('2d')
      var pieData = {
        labels: [
          'Thịt Cá',
          'Tinh Bột',
          'Rau Củ'
        ],
        datasets: [
          {
            data: [thitca, tinhbot, raucu],
            backgroundColor: ['#DD0000', '#FFFFCC', '#009900']
          }
        ]
      }
      
      var pieOptions = {
        legend: {
          display: false
        },
        maintainAspectRatio: false,
        responsive: true
      }
      
      // Create pie or douhnut chart
      // You can switch between pie and douhnut using the method below.
      // eslint-disable-next-line no-unused-vars
      var pieChart = new Chart(pieChartCanvas, { // lgtm[js/unused-local-variable]
        type: 'doughnut',
        data: pieData,
        options: pieOptions
      })

    });

    // Sales graph chart
    var salesGraphChartCanvas = $('#line-chart').get(0).getContext('2d')
    // $('#revenue-chart').get(0).getContext('2d');

    var salesGraphChartData = {
      labels: ['2011 Q1', '2011 Q2', '2011 Q3', '2011 Q4', '2012 Q1', '2012 Q2', '2012 Q3', '2012 Q4', '2013 Q1', '2013 Q2'],
      datasets: [
        {
          label: 'Digital Goods',
          fill: false,
          borderWidth: 2,
          lineTension: 0,
          spanGaps: true,
          borderColor: '#efefef',
          pointRadius: 3,
          pointHoverRadius: 7,
          pointColor: '#efefef',
          pointBackgroundColor: '#efefef',
          data: [2666, 2778, 4912, 3767, 6810, 5670, 4820, 15073, 10687, 8432]
        }
      ]
    }

    var salesGraphChartOptions = {
      maintainAspectRatio: false,
      responsive: true,
      legend: {
        display: false
      },
      scales: {
        xAxes: [{
          ticks: {
            fontColor: '#efefef'
          },
          gridLines: {
            display: false,
            color: '#efefef',
            drawBorder: false
          }
        }],
        yAxes: [{
          ticks: {
            stepSize: 5000,
            fontColor: '#efefef'
          },
          gridLines: {
            display: true,
            color: '#efefef',
            drawBorder: false
          }
        }]
      }
    }

    // This will get the first returned node in the jQuery collection.
    // eslint-disable-next-line no-unused-vars
    var salesGraphChart = new Chart(salesGraphChartCanvas, { // lgtm[js/unused-local-variable]
      type: 'line',
      data: salesGraphChartData,
      options: salesGraphChartOptions
    })
  })

  // bat dau code angularjs
  $scope.donhang;
  $scope.nhanvien;
  $scope.sanpham;
  console.log('sdfsa');
  $http.get(`/rest/home/sodonhang`).then(resp => {
    $scope.donhang = resp.data;
    console.log(resp.data);
  });

  $http.get(`/rest/home/sonhanvien`).then(resp => {
    $scope.nhanvien = resp.data;
    console.log(resp.data);
  });

  $http.get(`/rest/home/sosanpham`).then(resp => {
    $scope.sanpham = resp.data;
    console.log(resp.data);
  });
  
 
  $http.get(`/rest/orderitem/getallorderitem`).then(respOrderitem=>{
     // load top san pham ban chay nhat;
   const topTenBestSellingProducts = Object.values(
    respOrderitem.data
      .reduce((acc, { product:  {productid,name}, quantityproduct }) => {
        acc[productid] = { productid, name, quantityproduct: (acc[productid]?.quantityproduct || 0) + quantityproduct };
        return acc;
      }, {})
  ).sort((a, b) => b.quantityproduct - a.quantityproduct).slice(0, 5);
    
    console.log(topTenBestSellingProducts);
    $scope.top5orders = topTenBestSellingProducts;
   
    
    });
    
  
});


