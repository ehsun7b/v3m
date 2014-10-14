app.controller("HomeCtrl", function ($scope, Page, $http) {
  Page.setTitle("ورزش ۳");

  $scope.hotNews = [];
  $scope.footNews = [];

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

  $scope.loadFootNews = function () {
    $http({method: "GET", url: "/service/news/foot/int/10"}).
            success(function (data, status, headers, config) {
              $scope.footNews = $scope.footNews.concat(data);
              console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching internal football news!");
              console.log("status: " + status);
            });

    $http({method: "GET", url: "/service/news/foot/ext/10"}).
            success(function (data, status, headers, config) {
              $scope.footNews = $scope.footNews.concat(data);
              console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching external football news!");
              console.log("status: " + status);
            });
  };



  $scope.loadHotNews();
  $scope.loadFootNews();
});