app.factory("Database", ["$q", function ($q) {
    var instance = {
      dbVersion: 10,
      dbName: "varzesh3mob.com",
      db: null
    };

    instance.onOpenSuccess = function (event) {
      console.log("IndexedDb opened success.");
      this.db = event.target.result;
      console.log(this);
    };

    instance.onError = function (event) {
      console.log("Error in database!!!");
      console.log(event);
    };

    instance.onUpgrade = function (event) {
      console.log("Upgrading the indexedDB.");
      this.db = event.target.result;

      if (this.db.objectStoreNames.contains("news")) {
        this.db.deleteObjectStore("news");
      }

      var objectStore = this.db.createObjectStore("news", {keyPath: "id"});
      //objectStore.createIndex("id", "id", { unique: true });
    };

    console.log("requesting ...");
    var request = window.indexedDB.open(instance.dbName, instance.dbVersion);

    request.onerror = instance.onError;
    request.onsuccess = instance.onOpenSuccess.bind(instance);
    request.onupgradeneeded = instance.onUpgrade;

    instance.saveNewsList = function (newsList) {
      console.log("saving news ...");
      if (this.db !== null) {
        console.log(this.db);
        var transaction = this.db.transaction(["news"], "readwrite");
        var objectStore = transaction.objectStore("news");

        var len = newsList.length;
        for (var i = 0; i < len; ++i) {
          var news = newsList[i];
          objectStore.add(news);
        }
      } else {
        console.log("Database is not opened yet. Retry in 5 seconds");
        setTimeout(function () {
          console.log("retry!");
          this.saveNewsList(newsList);
        }.bind(this), 5000);
      }
    };

    instance.loadLatestNews = function (top) {
      console.log("loading latest news ...");
      var deferred = $q.defer();

      var transaction = this.db.transaction(["news"], "readonly");
      var objectStore = transaction.objectStore("news");

      var news = [];
      var request = objectStore.openCursor(null, "prev");
      //var index = objectStore.index("id");
      //var request = index.openCursor();
      request.onsuccess = function (event) {
        var cursor = event.target.result;
        
        if (cursor) {
          news.push(cursor.value);
          cursor.continue();
        } else {
          deferred.resolve(news);
        }
      };

      request.onerror = function (event) {
        deferred.reject(event);
      };

      return deferred.promise;
    };

    instance.isReady = function () {
      return this.db !== null;
    };

    return instance;
  }]);


