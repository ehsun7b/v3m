app.controller("HomeCtrl", function ($scope, Page, $http, Database, Server) {
  Page.setTitle("ورزش ۳");

  $scope.count = 20;
  $scope.hotNews = [];
  $scope.videos = [];
  $scope.newsReceived = false;

  $scope.loadHotNews = function () {
    console.info("loading latest news.");

    function getFromServer() {
      console.info("getting latest news from server.");
      $http({method: "GET", url: "/service/news/hot/" + $scope.count}).
              success(function (data, status, headers, config) {
                $scope.hotNews = data;
                //console.log(data);
              }).
              error(function (data, status, headers, config) {
                console.error("Error in fetching latest news!");
                console.log("status: " + status);
              });
    }

    var promise = Database.loadLatestNews($scope.count);

    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        $scope.hotNews = newsList;
      } else {
        getFromServer();
      }
    }, function (event) {
      console.error("loading latest news failed.");
      console.error(event);
      getFromServer();
    });
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

  $scope.manualLoadNews = function() {
    $scope.loadHotNews();
    $scope.newsReceived = false;
  };

  $scope.$on("newsReceived", function(event) {
    console.info("event newsReceived handled");
    //$scope.loadHotNews();
    $scope.newsReceived = true;
    angular.element("#sndDing")[0].play();
  });

  Server.loadNews();
  $scope.loadHotNews();
  $scope.loadVideos();  
  
  scope = $scope;
});