"use strict";
app.controller("TVCtrl", function ($scope, Page, $http, $sce, $location) {
  Page.setTitle("جدول پخش ورزش از تلویزیون");

  $scope.timeTable;

  $scope.loadTVPrograms = function () {
    $http({method: "GET", url: "/service/widget/tv"}).
            success(function (data, status, headers, config) {
              $scope.timeTable = $sce.trustAsHtml(data);
              console.log(data);
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching TV programs!");
              console.log("status: " + status);
            });
  };

  $scope.$on("showRecentNews", function () {
    $location.path("/");
  });

  $scope.loadTVPrograms();
});