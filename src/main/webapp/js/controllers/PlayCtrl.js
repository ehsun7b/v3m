"use strict";
app.controller("PlayCtrl", function ($scope, Page, $http, $routeParams, $sce, $location) {
  var videoId = $routeParams.param;
  console.info(videoId);

  $scope.video;

  $scope.loadVideo = function () {
    $http({method: "GET", url: "/service/video/" + videoId}).
            success(function (data, status, headers, config) {
              $scope.video = data;
              console.log(data);
              Page.setTitle($scope.video.title);

              $("video#video").attr("poster", $scope.video.thumbnailURL);
              $("video#video").attr("width", $scope.video.width);
              $("video#video").attr("height", $scope.video.height);
              $("source#source").attr("src", $scope.video.contentURL);

              document.getElementById("video").load();
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching video!");
              console.log("status: " + status);
            });
  };

  $scope.trustAsResource = function (src) {
    return $sce.trustAsResourceUrl(src);
  };

  $scope.back = function () {
    window.history.back();
  };

  $scope.$on("showRecentNews", function () {
    $location.path("/");
  });

  $scope.loadVideo();
});

