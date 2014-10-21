app.controller("ResultsCtrl", function ($scope, Page, $http, $sce, $interval) {
  Page.setTitle("نتایج زنده");

  $scope.resultsTable;
  $scope.rankingTable;
  $scope.loading = true;
  $scope.errors = 0;

  $scope.loadResults = function () {
    //$scope.loading = true;
    $http({method: "GET", url: "/service/results"}).
            success(function (data, status, headers, config) {
              if (status !== 200 || data.length <= 0) {
                $scope.errors++;

                if ($scope.errors > 3) {
                  $scope.resultsTable = "بروز خطا در بازیابی نتایج!";
                }

              } else {
                $scope.resultsTable = $sce.trustAsHtml(data);
                $scope.loading = false;
                $scope.errors = 0;
              }
            }).
            error(function (data, status, headers, config) {
              console.error("Error in fetching results!");
              console.log("status: " + status);
              $scope.loading = false;

              $scope.errors++;

              if ($scope.errors > 3) {
                $scope.resultsTable = "بروز خطا در بازیابی نتایج!";
              }
            });
  };

  $scope.loadRanking = function () {
    //$scope.loading = true;
    $http({method: "GET", url: "/service/ranking/iran"}).
            success(function (data, status, headers, config) {
              $scope.rankingTable = $sce.trustAsHtml(data);
            }).
            error(function (data, status, headers, config) {
              $scope.rankingTable = "بروز خطا در بازیابی جدول";
            });
  };

  $scope.loadResults();
  $scope.loadRanking();
  
  $interval($scope.loadResults, 15000);
});