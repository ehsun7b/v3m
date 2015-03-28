"use strict";
app.controller("ArchiveCtrl", function ($scope, Page, Database, $window, $timeout) {
  $scope.allList = [];
  $scope.filteredList = [];
  $scope.shotSize = 10;
  $scope.currentSize = 0;

  $scope.loadNews = function () {
    console.info("Archive loadNews");
    var promise = Database.loadAllNews();
    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        $scope.allList = newsList;

        $scope.shot();
      }
    }, function (event) {
      console.error("loading all news failed.");
      console.error(event);
    });
  };

  $scope.shot = function () {
    console.info("shot");
    $scope.filteredList = [];
    var l = $scope.allList.length;
    var size = $scope.currentSize;

    for (var i = 0; i < l && i < size + $scope.shotSize; ++i) {
      var news = $scope.allList[i];
      $scope.filteredList.push(news);
    }

    $scope.currentSize += i;
  };

  $scope.filterByCategory = function (category) {
    var l = $scope.allList.length;
    var result = [];

    for (var i = 0; i < l && result.length < 500; ++i) {
      var news = $scope.allList[i];

      if (news.category === category) {
        result.push(news);
      }
    }

    return result;
  };

  $scope.$on("showRecentNews", $scope.loadNews);

  $scope.loadNews();
  
});
