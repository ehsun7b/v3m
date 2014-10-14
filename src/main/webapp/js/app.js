var app = angular.module("app", ["ngRoute"]);

app.config(function ($routeProvider) {
  $routeProvider
          .when("/", {
            templateUrl: "html/home.html",
            controller: "HomeCtrl"
          })
          .when("/news/:param", {
            templateUrl: "html/news.html",
            controller: "NewsCtrl"
          })
          .when("/intfoot", {
            templateUrl: "html/intfoot.html",
            controller: "IntFootCtrl"
          })
          .when("/extfoot", {
            templateUrl: "html/extfoot.html",
            controller: "ExtFootCtrl"
          })
          .when("/sports", {
            templateUrl: "html/sports.html",
            controller: "SportsCtrl"
          })
          .when("/newspapers", {
            templateUrl: "html/newspapers.html",
            controller: "NewspapersCtrl"
          })
          .when("/tv", {
            templateUrl: "html/tv.html",
            controller: "TVCtrl"
          })
          .otherwise({
            redirectTo: "/"
          });
});


