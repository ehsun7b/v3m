app.factory("NewsWS", function ($rootScope, Database, Server) {
  var instance = {
    wsNews: null
  };

  // ws error
  instance.wsNewsError = function (e) {
    console.error(e);
    console.error("error websocket");
  };

  // ws open
  instance.wsNewsOpen = function (e) {
    console.info("ws opened");
    var lastNewsId = 0;

    var promise = Database.loadLatestNews(1);

    promise.then(function (newsList) {
      if (newsList && newsList.length > 0) {
        lastNewsId = newsList[0].id;
      }
      instance.wsNews.send(lastNewsId);
    }, function (event) {
      console.error("loading latest news failed.");
      console.error(event);
    });
  };

  // ws close
  instance.wsNewsClose = function (e) {
    console.info("ws closed. init again ...");
    instance.init();
  };

  // ws message
  instance.wsNewsMessage = function (e) {
    console.info("ws message");
    var newsIds = JSON.parse(e.data);
    var newsList = [];
    var unreadIntFoot = 0;
    var unreadExtFoot = 0;
    var unreadSports = 0;

    for (var i = 0; i < newsIds.length; ++i) {
      var id = newsIds[i];
      var promise = Server.loadNewsById(id);

      promise.then(function (news) {
        news.clientFetchTime = new Date();

        if (news.category === "foot_ext") {
          unreadExtFoot++;
        } else if (news.category === "foot_int") {
          unreadIntFoot++;
        } else if (news.category === "sports") {
          unreadSports++;
        }

        newsList.push(news);
        console.log(news);

        if (newsIds.length === newsList.length) {
          Database.saveNewsList(newsList);
          $rootScope.$broadcast("newsReceived");

          $rootScope.unreadExtFootCount = unreadExtFoot;
          $rootScope.unreadIntFootCount = unreadIntFoot;
          $rootScope.unreadSportsCount = unreadSports;
          $rootScope.unreadCount = unreadExtFoot + unreadIntFoot + unreadSports;
        }
      }, function (error) {
        console.log(error);
      });
    }
  };

  instance.init = function () {
    try {
      instance.wsNews = new WebSocket("ws://" + window.location.host + ":8080" + "/news");
      instance.wsNews.onerror = instance.wsNewsError;
      instance.wsNews.onopen = instance.wsNewsOpen;
      instance.wsNews.onclose = instance.wsNewsClose;
      instance.wsNews.onmessage = instance.wsNewsMessage;
    } catch (e) {
      console.error(e);
    }
  };

  return instance;
});


