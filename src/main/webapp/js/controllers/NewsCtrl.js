app.controller("NewsCtrl", function ($scope, Page, $http, $routeParams) {
  var newsId = $routeParams.param;
  console.info(newsId);

  $scope.news;

  $scope.loadNews = function () {
    $http({method: "GET", url: "/service/news/" + newsId}).
            success(function (data, status, headers, config) {
              $scope.news = data;
              console.log(data);
              Page.setTitle($scope.news.title);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching news!");
              console.log("status: " + status);
            });
  };

  $scope.back = function() {
    window.history.back();
  };

  $scope.loadNews();
});