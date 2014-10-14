app.controller("MenuCtrl", function ($scope) {
  $scope.showClass = "toggled";
  $scope.menuShow = false;
  $scope.toggle = function() {
    if ($scope.showClass === "") {
      $scope.menuShow = false;
      $scope.showClass = "toggled";
    } else {
      $scope.showClass = "";
      $scope.menuShow = true;
    }
  };
});


