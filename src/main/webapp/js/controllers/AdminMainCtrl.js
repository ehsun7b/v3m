adminapp.controller("AdminMainCtrl", function ($rootScope, $scope, $http, $window, $timeout) {
  $scope.width = 0;
  fixMenuHeight();

  $scope.onResize = function () {
    $scope.width = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    $scope.newsImageWidth = Math.max(80, Math.round($scope.width / 8));
    $scope.newsImageHeight = Math.max(60, Math.round($scope.newsImageWidth * 80 / 100));
  };

  $scope.onResize();

  window.addEventListener("resize", fixMenuHeight);
  window.addEventListener("resize", $scope.onResize);
});

function fixMenuHeight() {
  var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
  var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

  var menu = document.getElementById("menu");
  if (menu !== null && menu !== undefined) {
    menu.style.height = h + "px";
  }
}

