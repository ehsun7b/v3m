app.controller("IntFootCtrl", function ($scope, Page, $http, Database) {
  Page.setTitle("فوتبال داخلی");

  $scope.count = 50;
  $scope.newsList;

  $scope.loadHotNews = function () {
    console.info("loading internal football news.");

    function getFromServer() {
      console.info("getting internal football news from server.");
      $http({method: "GET", url: "/service/news/foot/int/" + $scope.count}).
              success(function (data, status, headers, config) {
                $scope.newsList = data;
                //console.log(data);
              }).
              error(function (data, status, headers, config) {
                console.error("Error in fetching hot news!");
                console.log("status: " + status);
              });
    }

    var promise = Database.loadLatestNewsByCategory($scope.count, "foot_int");

    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        $scope.newsList = newsList;
      } else {
        getFromServer();
      }
    }, function (event) {
      console.error("loading internal football news failed.");
      console.error(event);
      getFromServer();
    });
  };

  $scope.loadHotNews();
});