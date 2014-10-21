app.directive('googlePlus', [
  '$window', function ($window) {
    return {
      restrict: 'A',
      link: function (scope, element, attrs) {
        if (!$window.gapi) {
          // Load Google SDK
          $.getScript('//apis.google.com/js/platform.js', function () {
            renderPlusButton();
          });
        } else {
          renderPlusButton();
        }

        function renderPlusButton() {
          element.html('<div class="g-plusone" data-size="medium"></div>');
          $window.gapi.plusone.go(element.parent()[0]);
        }
      }
    };
  }
]);
