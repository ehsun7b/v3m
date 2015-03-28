"use strict";
adminapp.controller("AdminMenuCtrl", function ($scope) {
  $scope.showClass = "toggled";
  $scope.toggle = function() {
    if ($scope.showClass === "") {
      $scope.showClass = "toggled";
    } else {
      $scope.showClass = "";
    }
  };
});
