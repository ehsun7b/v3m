"use strict";
app.directive('scrollReached', function ($rootScope, $window, $timeout) {
  var instance = {
  };

  instance.link = function (scope, element, attrs) {
    var active = true;

    element.on("scroll", function () {

      var elementBottom, remaining, shouldScroll, windowBottom;
      windowBottom = $window.height() + $window.scrollTop();
      elementBottom = elem.offset().top + elem.height();
      remaining = elementBottom - windowBottom;
      shouldScroll = remaining <= $window.height() * scrollDistance;
      if (shouldScroll && scrollEnabled) {
        if ($rootScope.$$phase) {
          return scope.$eval(attrs.infiniteScroll);
        } else {
          return scope.$apply(attrs.infiniteScroll);
        }
      }

      if (active) {
        scope.$eval(attrs.handler);
        active = false;

        $timeout(function () {
          active = true;
        }, 5000);
      }
    });
  };

  return instance;
});

