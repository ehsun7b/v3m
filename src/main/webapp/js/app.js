var app = angular.module("app", ["ngRoute"]);

app.config(function ($routeProvider, $locationProvider) {
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
          .when("/results", {
            templateUrl: "html/results.html",
            controller: "ResultsCtrl"
          })
          .when("/video", {
            templateUrl: "html/video.html",
            controller: "VideoCtrl"
          })
          .when("/play/:param", {
            templateUrl: "html/play.html",
            controller: "PlayCtrl"
          })
          .otherwise({
            redirectTo: "/"
          });

  $locationProvider
          .html5Mode(false)
          .hashPrefix("!");

});

app.run([
  '$rootScope', function ($rootScope) {
    $rootScope.facebookAppId = '623178241126464';
  }
]);


