app.controller("NewspapersCtrl", function ($scope, Page, $http) {
  Page.setTitle("روزنامه‌های ورزشی");
  
  $scope.collection;

  $scope.loadNewspapers = function () {
    $http({method: "GET", url: "/service/newspaper/last"}).
            success(function (data, status, headers, config) {
              $scope.collection = data;
              console.log(data);              
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching news!");
              console.log("status: " + status);
            });
  };

  $scope.back = function() {
    window.history.back();
  };

  $scope.loadNewspapers();
});