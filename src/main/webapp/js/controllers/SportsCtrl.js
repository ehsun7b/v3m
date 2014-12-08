app.controller("SportsCtrl", function ($scope, Page, $http, Database) {
  Page.setTitle("غیر فوتبالی");

  $scope.count = 50;
  $scope.newsList;

  $scope.loadHotNews = function () {
    console.info("loading sports news.");

    function getFromServer() {
      console.info("getting sports news from server.");

      $http({method: "GET", url: "/service/news/sports/" + $scope.count}).
              success(function (data, status, headers, config) {
                $scope.newsList = data;
                console.log(data);
              }).
              error(function (data, status, headers, config) {
                console.error("Error in fetching hot news!");
                console.log("status: " + status);
              });
    }

    var promise = Database.loadLatestNewsByCategory($scope.count, "sports");

    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        $scope.newsList = newsList;
      } else {
        getFromServer();
      }
    }, function (event) {
      console.error("loading sports news failed.");
      console.error(event);
      getFromServer();
    });
  };

  $scope.loadHotNews();
});