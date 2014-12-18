app.factory("Database", function ($rootScope, $q) {
  var instance = {
    dbVersion: 15,
    dbName: "varzesh3mob.com"
  };

  //console.log("root scope");
  //console.log($rootScope);

  instance.onUpgrade = function (event) {
    console.log("Upgrading the indexedDB.");
    this.db = event.target.result;

    if (this.db.objectStoreNames.contains("news")) {
      this.db.deleteObjectStore("news");
    }

    var objectStore = this.db.createObjectStore("news", {keyPath: "id"});
    objectStore.createIndex("category", "category", {unique: false});
  };

  instance.open = function () {
    console.info("Opening database.");
    var deferred = $q.defer();

    var request = window.indexedDB.open(this.dbName, this.dbVersion);

    request.onupgradeneeded = this.onUpgrade;

    request.onsuccess = function (event) {
      deferred.resolve(event.target.result);
    };

    request.onerror = function (event) {
      deferred.reject(event.target.result);
    };

    return deferred.promise;
  };

  instance.saveNewsList = function (newsList) {
    console.info("saving news ...");
    var promise = this.open();

    promise.then(function (db) {
      var len = newsList.length;

      for (var i = 0; i < len; ++i) {
        var transaction = db.transaction(["news"], "readwrite");
        var objectStore = transaction.objectStore("news");

        var news = newsList[i];

        var request = objectStore.add(news);
        request.onerror = function (event) {
          //console.log(event);
          //var error = event.target.transaction.error;                                    
          //console.error(error);            
        };

        request.onsuccess = function (event) {
          $rootScope.lastInsert = new Date().getTime();
        };
      }
    }, function (event) {
      console.error(event);
    });
  };

  instance.loadLatestNews = function (top) {
    console.info("loading latest news ...");
    var deferred = $q.defer();

    var promise = this.open();

    promise.then(function (db) {

      var transaction = db.transaction(["news"], "readonly");
      var objectStore = transaction.objectStore("news");

      var news = [];
      var request = objectStore.openCursor(null, "prev"); // 'prev' is for descening order
      var i = 0;

      request.onsuccess = function (event) {
        var cursor = event.target.result;

        if (cursor && i < top) {
          i++;
          news.push(cursor.value);
          cursor['continue']();
        } else {
          deferred.resolve(news);
          $rootScope.lastLoad = new Date().getTime();
        }
      };

      request.onerror = function (event) {
        deferred.reject(event);
      };
    }, function (event) {
      deferred.reject(event);
    });

    return deferred.promise;
  };

  instance.loadLatestNewsByCategory = function (top, category) {
    console.info("loading latest news of " + category);
    var deferred = $q.defer();

    var promise = this.open();

    promise.then(function (db) {
      var transaction = db.transaction(["news"], "readonly");
      var objectStore = transaction.objectStore("news");

      var news = [];
      var index = objectStore.index("category");
      var range = IDBKeyRange.only(category);
      var request = index.openCursor(range, "prev");

      var i = 0;

      request.onsuccess = function (event) {
        var cursor = event.target.result;

        if (cursor && i < top) {
          i++;
          news.push(cursor.value);
          cursor['continue']();
        } else {
          deferred.resolve(news);
        }
      };

      request.onerror = function (event) {
        deferred.reject(event);
      };
    }, function (event) {
      deferred.reject(event);
    });


    return deferred.promise;
  };


  instance.loadNews = function (id) {
    //console.info("loading news " + id);
    var deferred = $q.defer();

    var promise = this.open();

    promise.then(function (db) {
      var transaction = db.transaction(["news"], "readonly");
      var objectStore = transaction.objectStore("news");
      var request = objectStore.get(parseInt(id));

      request.onsuccess = function (event) {
        deferred.resolve(event.target.result);
      };

      request.onerror = function (event) {
        deferred.reject(event);
      };
    }, function (event) {
      deferred.reject(event);
    });

    return deferred.promise;
  };

  instance.clear = function () {
    console.warn("clearing database.");

    var promise = this.open();

    promise.then(function (db) {
      var transaction = db.transaction(["news"], "readwrite");
      var objectStore = transaction.objectStore("news");

      var request = objectStore.clear();

      request.onsuccess = function (event) {
        console.info("database cleared.");
      };

      request.onerror = function (event) {
        console.error(event);
      };
    }, function (event) {
      console.error(event);
    });
  };

  instance.isReady = function () {
    return this.db !== null;
  };

  return instance;
});


