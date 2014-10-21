app.directive('fbLike', function ($window, $rootScope) {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      if (!$window.FB) {
        // Load Facebook SDK        
        $.getScript('//connect.facebook.net/en_US/sdk.js', function () {
          $window.FB.init({
            appId: $rootScope.facebookAppId,
            xfbml: true,            
            version: 'v2.0'
          });
          renderLikeButton();
        });
      } else {
        renderLikeButton();
      }

      function renderLikeButton() {
        element.html('<div class="fb-like" data-width="200" data-layout="standard" data-action="like" data-show-faces="false" data-share="true"></div>');
        $window.FB.XFBML.parse(element.parent()[0]);
      }
    }
  };
});