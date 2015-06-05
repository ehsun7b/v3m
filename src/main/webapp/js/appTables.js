"use strict";
var appTables = angular.module("appTables", []);

appTables.config(function ($logProvider) {  
  $logProvider.debugEnabled(true);
});

appTables.run(function ($rootScope, $interval, $window, $log) {
  $log.debug("app tables run");
});


