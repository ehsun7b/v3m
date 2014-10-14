app.controller("IntFootCtrl", function ($scope, Page, $http) {
  Page.setTitle("فوتبال داخلی");

  $scope.count = 50;
  $scope.newsList;

  $scope.loadHotNews = function () {
    $http({method: "GET", url: "/service/news/foot/int/" + $scope.count}).
            success(function (data, status, headers, config) {
              $scope.newsList = data;
              console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching hot news!");
              console.log("status: " + status);
            });
  };

  $scope.loadHotNews();
});