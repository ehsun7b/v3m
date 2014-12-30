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

app.run(function ($rootScope, $interval) {
  console.log("app run");

  $rootScope.facebookAppId = '623178241126464';
  $rootScope.lastInsert = 0;
  $rootScope.lastLoad = 0;


  $interval(function() {
    $rootScope.checkNews();
  }, 5000);
  
  $rootScope.checkNews = function() {
    if ($rootScope.lastInsert > $rootScope.lastLoad) {
      $rootScope.$broadcast("newsReceived");
    }
  };
  
  $rootScope.welcome = function() {
    console.log("welcome haha");
  };  
});


