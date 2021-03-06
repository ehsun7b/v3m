"use strict";
app.factory("Server", function ($http, $q, Database) {
  var count = 50;

  return {
    loadNews: function () {
      $http({method: "GET", url: "/service/news/foot/ext/" + count}).
              success(this.onSuccess).
              error(this.onError);

      $http({method: "GET", url: "/service/news/foot/int/" + count}).
              success(this.onSuccess).
              error(this.onError);

      $http({method: "GET", url: "/service/news/sports/" + count}).
              success(this.onSuccess).
              error(this.onError);
    },
    loadNewsById: function (id) {
      var deferred = $q.defer();

      $http({method: "GET", url: "/service/news/" + id}).
              success(function (data) {
                deferred.resolve(data);
              }).
              error(function (data) {
                deferred.reject(data);
              });

      return deferred.promise;
    },
    onSuccess: function (data, status, headers, config) {
      //console.log(data);        
      Database.saveNewsList(data);
    },
    onError: function (data, status, headers, config) {
      console.error(data);
      console.error(status);
    }
  };
});


