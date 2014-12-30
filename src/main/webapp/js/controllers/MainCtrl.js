app.controller("MainCtrl", function ($scope, Page, $http, $window, Server, $timeout) {
  $scope.Page = Page;
  $scope.mobile = true;  
  $scope.loadNewsInterval = 1000 * 60 * 5; // 5 minutes
  $scope.width = 0;
  $scope.newsImageWidth = 80;
  $scope.newsImageHeight = 60;
  
  fixMenuHeight();
  
  $scope.onResize = function() {
    $scope.width = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    $scope.newsImageWidth = Math.max(80, Math.round($scope.width / 8));
    //console.info($scope.width);
    //console.info($scope.newsImageWidth);
    $scope.newsImageHeight = Math.max(60, Math.round($scope.newsImageWidth * 80 / 100));
    //console.info($scope.newsImageHeight);
    
  };
  
  $scope.onResize();
  
  window.addEventListener("resize", fixMenuHeight);
  window.addEventListener("resize", $scope.onResize);

  $scope.isMobile = function () {
    $http({method: "GET", url: "/service/common/mobile"}).
            success(function (data, status, headers, config) {
              $scope.mobile = data.result;
              //console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in checking is mobile!");
              console.log("status: " + status);
            });
  };
  
  $scope.showDesktop = function() {
    $window.location.href = 'http://www.varzesh3.com';
  };
  
  $scope.loadNewsRepeat = function() {
    Server.loadNews();
    $timeout($scope.loadNewsRepeat, $scope.loadNewsInterval);
  };
  
  $scope.loadNewsRepeat();
  $scope.isMobile();
});

function fixMenuHeight() {
  //console.log("fix height");
  var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
  var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

  var menu = document.getElementById("menu");
  if (menu !== null && menu !== undefined) {
    menu.style.height = h + "px";
  }
}

