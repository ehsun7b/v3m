app.controller("NewsCtrl", function ($scope, Page, $http, $routeParams, Database, $location) {
  var newsId = $routeParams.param;
  //console.info("news id: " + newsId);

  $scope.news;

  $scope.loadNews = function () {

    function getFromServer() {
      $http({method: "GET", url: "/service/news/" + newsId}).
              success(function (data, status, headers, config) {
                $scope.news = data;
                //console.log(data);
                Page.setTitle($scope.news.title);
                $scope.news.mainText = $scope.process($scope.news.mainText);
              }).
              error(function (data, status, headers, config) {
                console.error("Error in fetching news!");
                console.error("status: " + status);
              });
    }

    var promise = Database.loadNews(newsId);
    promise.then(function (news) {
      if (news) {
        $scope.news = news;
        $scope.news.mainText = $scope.process($scope.news.mainText);
      } else {
        getFromServer();
      }
      //console.info(news);
    }, function (reason) {
      console.log("Loading news failed! id: " + newsId);
      console.log(reason);
      getFromServer();
    });
  };

  $scope.back = function () {
    window.history.back();
  };

  $scope.process = function (html) {
    try {
      html = $scope.clean(html);
      document.getElementById("mainText").innerHTML = html;
    } catch (e) {
      console.error(e);
    }
  };

  $scope.clean = function (html) {
    //console.log("html");
    //console.log(html);

    var div = document.createElement("div");
    div.innerHTML = html;

    //console.log("div");
    //console.log(div);

    var nodes = div.childNodes;
    var l = nodes.length;

    for (var i = 0; i < l; ++i) {
      var node = nodes[i];
      $scope.cleanNode(node);
    }

    return div.innerHTML;
  };

  $scope.cleanNode = function (n) {
    if (n !== null && n !== undefined) {
      if (n.hasChildNodes()) {
        var ns = n.childNodes;

        if (ns !== null && ns !== undefined) {
          var l = ns.length;
          for (var i = 0; i < l; ++i) {
            var n1 = ns[i];
            $scope.cleanNode(n1);
          }
        }
      }

      var nodeType = n.nodeType;
      if (nodeType === 1) {
        var tagName = n.tagName.toString().toLowerCase();
        if (tagName === "a") {
          n.removeAttribute("href");
        } else if (tagName === "img") {
          n.setAttribute("class", "newsContentImage");
          var w = n.getAttribute("width");
          var h = n.getAttribute("height");

          n.removeAttribute("width");
          n.removeAttribute("height");

          var styleString = "";
          if ((w !== undefined && w !== null)) {
            styleString += "max-width: " + w + "px;";
          }

          if ((h !== undefined && h !== null)) {
            styleString += "max-height: " + h + "px;";
          }

          if (styleString.length > 0) {
            n.setAttribute("style", styleString);
          }
        }
      }
    } else {
      console.error("Node is null!");
    }
  };

  $scope.$on("showRecentNews", function () {
    $location.path("/");
  });


  $scope.loadNews();
});

