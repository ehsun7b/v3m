app.controller("HomeCtrl", function ($scope, Page, $http, Server, Database) {
  Page.setTitle("ورزش ۳");

  $scope.hotNews = [];
  $scope.videos = [];

  $scope.loadHotNews = function () {
    function load() {
      var promise = Database.loadLatestNews(10);
      promise.then(function (news) {
        $scope.hotNews = news;
      }, function (reason) {
        console.log("Loading latest news failed!");
        console.log(reason);
      });
    }

    if (Database.isReady()) {
      load();
    } else {
      setTimeout(function () {
        load();
      }, 1000);
    }
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
  $scope.loadVideos();
});