app.controller("MainCtrl", function ($scope, Page) {
  $scope.Page = Page;

  fixMenuHeight();
  window.addEventListener("resize", fixMenuHeight);
});

function fixMenuHeight() {
  var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
  var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
  
  var menu = document.getElementById("menu");
  if (menu !== null && menu !== undefined) {
    menu.style.height = h + "px";
  }
}

