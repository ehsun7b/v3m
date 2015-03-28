"use strict";
app.controller("MainCtrl", function ($rootScope, $scope, Page, $http, $window, Server, $timeout) {
  $scope.Page = Page;
  $scope.mobile = true;
  $scope.loadNewsInterval = 1000 * 60 * 5; // 5 minutes
  $scope.width = 0;
  $scope.newsImageWidth = 80;
  $scope.newsImageHeight = 60;
  $scope.newsReceived = false;

  fixMenuHeight();

  $scope.onResize = function () {
    $scope.width = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    $scope.newsImageWidth = Math.max(80, Math.round($scope.width / 8));
    $scope.newsImageHeight = Math.max(60, Math.round($scope.newsImageWidth * 80 / 100));
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

  $scope.showDesktop = function () {
    $window.location.href = 'http://www.varzesh3.com';
  };

  $scope.loadNewsRepeat = function () {
    Server.loadNews();
    $timeout($scope.loadNewsRepeat, $scope.loadNewsInterval);
  };

  if (!$rootScope.wsSupported) {
    console.info("WebSocket is not supported");
    $scope.loadNewsRepeat();
  }

  $scope.$on("newsReceived", function (event) {
    console.info("event newsReceived handled");
    $scope.newsReceived = true;
  });
  
  $scope.$on("newsDisplayed", function (event) {
    console.info("event newsDisplayed handled");
    $scope.newsReceived = false;
  });

  $scope.showRecentNews = function() {
    console.info("event showRecentNews fired");
    $scope.$broadcast("showRecentNews");
    $scope.newsReceived = false;
  };

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

