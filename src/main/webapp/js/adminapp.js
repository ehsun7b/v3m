"use strict";
var adminapp = angular.module("adminapp", ["ngRoute"]);

adminapp.config(function ($routeProvider, $locationProvider) {
  $routeProvider
          .when("/", {
            templateUrl: "html/admin_home.html",
            controller: "AdminHomeCtrl"
          })
          .otherwise({
            redirectTo: "/"
          });

  $locationProvider
          .html5Mode(false)
          .hashPrefix("!");

});

adminapp.run(function ($rootScope, $interval) {
  console.log("admin app run");

  $rootScope.onlineUsersCount = 0;

});


