app.controller("HomeCtrl", function ($rootScope, $scope, Page, $http, Database, Server, $interval) {
  Page.setTitle("ورزش ۳");

  $scope.count = 20;
  $scope.hotCount = 10;
  $scope.lastNews = [];
  $scope.hotNews = [];
  $scope.videos = [];
  $scope.currentHotNewsIndex = 0;
  //$scope.currentHotNews = {};
  $scope.hotNewsInterval = 10000;

  $scope.loadLastNews = function () {
    console.info("loading latest news.");

    function getFromServer() {
      console.info("getting latest news from server.");
      $http({method: "GET", url: "/service/news/last/" + $scope.count}).
              success(function (data, status, headers, config) {
                $scope.lastNews = data;
              }).
              error(function (data, status, headers, config) {
                console.error("Error in fetching latest news!");
                console.log("status: " + status);
              });
    }

    var promise = Database.loadLatestNews($scope.count);

    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        $scope.lastNews = newsList;
      } else {
        getFromServer();
      }
    }, function (event) {
      console.error("loading latest news failed.");
      console.error(event);
      getFromServer();
    });
  };

  $scope.loadHotNews = function () {
    console.info("getting hot news from server.");
    $http({method: "GET", url: "/service/news/hot/" + $scope.hotCount}).
            success(function (data, status, headers, config) {
              $scope.hotNews = data;
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching hot news!");
              console.log("status: " + status);
            });
  };

  $scope.showNextHotNews = function () {
    if ($scope.currentHotNewsIndex < $scope.hotNews.length - 1) {
      $scope.currentHotNewsIndex++;
    } else {
      $scope.currentHotNewsIndex = 0;
    }

    $scope.currentHotNews = $scope.hotNews[$scope.currentHotNewsIndex];
  };

  $scope.loadVideos = function () {
    $http({method: "GET", url: "/service/video/all/10"}).
            success(function (data, status, headers, config) {
              $scope.videos = data;
              //console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching Videos!");
              console.log("status: " + status);
            });
  };

  $scope.manualLoadNews = function () {
    $scope.loadLastNews();
    $scope.loadHotNews();
    console.info("event newsDisplayed fired");
    $scope.$emit("newsDisplayed");
  };

  if (!$rootScope.wsSupported) {
    console.info("WebSocket is not supported");
    Server.loadNews();
  } else {
    $scope.$on("showRecentNews", $scope.manualLoadNews);
  }

  $interval($scope.showNextHotNews, $scope.hotNewsInterval);

  $scope.loadLastNews();
  $scope.loadHotNews();
  $scope.loadVideos();
  $scope.newsReceived = false;
  console.info("event newsDisplayed fired");
  $scope.$emit("newsDisplayed");
});