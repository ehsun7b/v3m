app.controller("MainCtrl", function ($scope, Page, $http, $window, Server, $timeout) {
  $scope.Page = Page;
  $scope.mobile = true;  
  $scope.loadNewsInterval = 1000 * 60 * 1; // 5 minutes
  
  fixMenuHeight();
  window.addEventListener("resize", fixMenuHeight);

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
  var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
  var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

  var menu = document.getElementById("menu");
  if (menu !== null && menu !== undefined) {
    menu.style.height = h + "px";
  }
}

