app.controller("ExtFootCtrl", function ($scope, Page, $http, Database, $location) {
  Page.setTitle("فوتبال خارجی");

  $scope.count = 50;
  $scope.newsList;

  $scope.loadHotNews = function () {
    console.info("loading external football news.");

    function getFromServer() {
      console.info("getting external football news from server.");
      $http({method: "GET", url: "/service/news/foot/ext/" + $scope.count}).
              success(function (data, status, headers, config) {
                $scope.newsList = data;
                console.log(data);
              }).
              error(function (data, status, headers, config) {
                console.error("Error in fetching hot news!");
                console.log("status: " + status);
              });
    }

    var promise = Database.loadLatestNewsByCategory($scope.count, "foot_ext");

    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        $scope.newsList = newsList;
      } else {
        getFromServer();
      }
    }, function (event) {
      console.error("loading external football news failed.");
      console.error(event);
      getFromServer();
    });
  };

  $scope.$on("showRecentNews", function () {
    $location.path("/");
  });

  $scope.loadHotNews();
});