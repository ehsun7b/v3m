"use strict";
app.factory("Page", function () {
  var title = "";
  return {
    title: function () {
      return title;
    },
    setTitle: function (newTitle) {
      title = newTitle;
    }
  };
});
