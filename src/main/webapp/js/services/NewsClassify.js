"use strict";
app.factory("NewsClassify", function ($rootScope, $q, Database) {
  var NEWS_COUNT_TO_CHECK = 500;
  var instance = {
  };

  var worker = new Worker('js/workers/classifyworker2.js');
  var defer = $q.defer();
  worker.addEventListener('message', function (e) {
    console.log('Worker said: ', e.data);
    defer.resolve(e.data);
  }, false);

  instance.classify = function(news) {
    defer = $q.defer();
    var promise = Database.loadLatestNewsByCategory(NEWS_COUNT_TO_CHECK, news.category);
    
    promise.then(function(newsList) {
      worker.postMessage([news, newsList]);
    }, function(e) {
      defere.reject(e);
    });    
    
    return defer.promise;
  };

  return instance;
});


