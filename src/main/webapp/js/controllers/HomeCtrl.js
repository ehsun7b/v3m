app.controller("HomeCtrl", function ($scope, Page, $http) {
  Page.setTitle("ورزش ۳");

  $scope.hotNews = [];
  $scope.videos = [];

  $scope.loadHotNews = function () {
    $http({method: "GET", url: "/service/news/hot"}).
            success(function (data, status, headers, config) {
              $scope.hotNews = data;
              console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching hot news!");
              console.log("status: " + status);
            });
  };

  $scope.loadVideos = function () {
    $http({method: "GET", url: "/service/video/all/10"}).
            success(function (data, status, headers, config) {
              $scope.videos = data;
              console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching Videos!");
              console.log("status: " + status);
            });
  };



  $scope.loadHotNews();
  $scope.loadVideos()
});