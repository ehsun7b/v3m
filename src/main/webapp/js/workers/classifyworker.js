"use strict";
//var GENERAL_WORDS = ['', '', '', '', '', ''];

var MAX_RESULT = 10;

var SCORE_TITLE_WORD_IN_LEVEL1_KEWORDS = 100;
var SCORE_TITLE_WORD_IN_LEVEL2_KEWORDS = 50;
var SCORE_ABSTRACT_WORD_IN_LEVEL1_KEWORDS = 80;
var SCORE_ABSTRACT_WORD_IN_LEVEL2_KEWORDS = 35;
var SCORE_TEXT_WORD_IN_LEVEL1_KEWORDS = 70;
var SCORE_TEXT_WORD_IN_LEVEL2_KEWORDS = 30;

var SCORE_TITLE_WORD_IN_LEVEL1_NEGATIVE_KEWORDS = -100;
var SCORE_TITLE_WORD_IN_LEVEL2_NEGATIVE_KEWORDS = -50;
var SCORE_ABSTRACT_WORD_IN_LEVEL1_NEGATIVE_KEWORDS = -80;
var SCORE_ABSTRACT_WORD_IN_LEVEL2_NEGATIVE_KEWORDS = -35;
var SCORE_TEXT_WORD_IN_LEVEL1_NEGATIVE_KEWORDS = -70;
var SCORE_TEXT_WORD_IN_LEVEL2_NEGATIVE_KEWORDS = -30;

var topics = [];

var topic1 = {
  name: "persepolis",
  title: "پرسپولیس",
  level1Keywords: ["پرسپوليس", "پرسپولیس", "نژادفلاح", "نژاد‌فلاح", "درخشان"],
  level2Keywords: ["پيام صادقيان", "محمد نوری", "حمدرضا علی‌عسگری", "رضا نورمحمدی", "هادی نوروزی", "مکانی"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic1);

var topic2 = {
  name: "esteghlal",
  title: "استقلال",
  level1Keywords: ["استقلال", "قلعه نویی", "امیر قلعه نویی"],
  level2Keywords: ["منصوریان", "علیرضا منصوریان", "کرار"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic2);

var topic3 = {
  name: "messi",
  title: "مسی",
  level1Keywords: ["مسی", "لئو مسي"],
  level2Keywords: ["بارسا", "بارسلون"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic3);

var topic4 = {
  name: "saipa",
  title: "سايپا",
  level1Keywords: ["سايپا", "جلالي"],
  level2Keywords: ["حامد شیری", "ابراهیم شکوری"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic4);

var topic5 = {
  name: "sepahan",
  title: "سپاهان",
  level1Keywords: ["سپاهان", "حسین فرکی", "فرکی"],
  level2Keywords: ["مهدی شریفی"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic5);

var topic6 = {
  name: "zobahan",
  title: "ذوب‌آهن",
  level1Keywords: ["ذوب‌آهن", "یحیی گل‌محمدی", "گل‌محمدی", "ذوب آهن"],
  level2Keywords: ["محسن مسلمان"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic6);

var topic7 = {
  name: "saba",
  title: "صبا",
  level1Keywords: ["صبا"],
  level2Keywords: ["کاظمیان"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic7);

var topic8 = {
  name: "bascketball",
  title: "بسکتبال",
  level1Keywords: ["بسکتبال", "FIBA"],
  level2Keywords: ["بهنام یخچالی", "صمد نیکخواه بهرامی", "حامد حدادی"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic8);

var topic9 = {
  name: "volleyball",
  title: "واليبال",
  level1Keywords: ["واليبال", "کواچ"],
  level2Keywords: [],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic9);

var topic10 = {
  name: "wrestling",
  title: "کشتی",
  level1Keywords: ["کشتی", "کشتی‌فرنگی"],
  level2Keywords: ["فرنگی", "دایره طلایی"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic10);

var topic11 = {
  name: "handball",
  title: "هندبال",
  level1Keywords: ["هندبال"],
  level2Keywords: [],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic11);

var topic12 = {
  name: "foolad",
  title: "فولاد",
  level1Keywords: ["فولاد", "اسکوچیچ"],
  level2Keywords: [],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic12);

var topic13 = {
  name: "ronaldo",
  title: "رونالدو",
  level1Keywords: ["رونالدو", "کريستيانو"],
  level2Keywords: ["رئال مادرید"],
  level1NegativeKeywords: [],
  level2NegativeKeywords: []
};
topics.push(topic13);

// main entry point of the thread
self.addEventListener('message', function (e) {
  console.log('message received: ', e.data);

  // received news which should be classified
  var news = e.data[0];
  var newsList = e.data[1];
  var result = [];

  var newsScores = calculateTopicScoresOfNews(news);

  if (newsScores.length > 0) {
    var len = newsList.length;
    for (var i = 0; i < len && result.length < MAX_RESULT; ++i) {
      var n = newsList[i];

      if (news.id !== n.id) {
        var scores = calculateTopicScoresOfNews(n);

        if (scores.length > 0) {
          if (scores[0].topicName === newsScores[0].topicName) {
            result.push(n);
          }
        }                
      }
    }
  }

  if (result.length <= 0) {
    result = newsList.slice(0, MAX_RESULT - 1);
  }

  postMessage([newsScores, result]);
}, false);

function calculateTopicScoresOfNews(news) {
  var scores = [];
  var lenthOfTopics = topics.length;

  for (var i = 0; i < lenthOfTopics; ++i) {
    var topic = topics[i];

    var score = calculateScore(topic, news);

    if (score > 0) {
      scores.push({score: score, topicName: topic.name, topicTitle: topic.title});
    }
  }

  scores = scores.sort(function (a, b) {
    if (a.score > b.score) {
      return 1;
    } else if (a.score < b.score) {
      return -1;
    } else {
      return 0;
    }
  });

  scores = scores.reverse();

  return scores;
}

/**
 * 
 * @param {Topic} topic
 * @param {News} news
 * @returns {Number}
 */
function calculateScore(topic, news) {
  var result = 0;

  // level 1 keywords
  var len = topic.level1Keywords.length;
  for (var i = 0; i < len; ++i) {
    var keyWord = topic.level1Keywords[i];

    // preTitle
    if (news.preTitle !== null &&
            news.preTitle !== undefined) {
      var count = countsOfWordInString(keyWord, news.preTitle);
      result += count * SCORE_TITLE_WORD_IN_LEVEL1_KEWORDS;
    }

    // title
    if (news.title !== null &&
            news.title !== undefined) {
      var count = countsOfWordInString(keyWord, news.title);
      result += count * SCORE_TITLE_WORD_IN_LEVEL1_KEWORDS;
    }

    // abstractText
    if (news.abstractText !== null &&
            news.abstractText !== undefined) {
      var count = countsOfWordInString(keyWord, news.abstractText);
      result += count * SCORE_ABSTRACT_WORD_IN_LEVEL1_KEWORDS;
    }

    // mainText
    if (news.mainText !== null &&
            news.mainText !== undefined) {
      var count = countsOfWordInString(keyWord, news.mainText);
      result += count * SCORE_TEXT_WORD_IN_LEVEL1_KEWORDS;
    }
  }

  // level 2 keywords
  len = topic.level2Keywords.length;
  for (var i = 0; i < len; ++i) {
    var keyWord = topic.level2Keywords[i];

    // preTitle
    if (news.preTitle !== null &&
            news.preTitle !== undefined) {
      var count = countsOfWordInString(keyWord, news.preTitle);
      result += count * SCORE_TITLE_WORD_IN_LEVEL2_KEWORDS;
    }

    // title
    if (news.title !== null &&
            news.title !== undefined) {
      var count = countsOfWordInString(keyWord, news.title);
      result += count * SCORE_TITLE_WORD_IN_LEVEL2_KEWORDS;
    }

    // abstractText
    if (news.abstractText !== null &&
            news.abstractText !== undefined) {
      var count = countsOfWordInString(keyWord, news.abstractText);
      result += count * SCORE_ABSTRACT_WORD_IN_LEVEL2_KEWORDS;
    }

    // mainText
    if (news.mainText !== null &&
            news.mainText !== undefined) {
      var count = countsOfWordInString(keyWord, news.mainText);
      result += count * SCORE_TEXT_WORD_IN_LEVEL2_KEWORDS;
    }
  }

  // level 1 negative keywords
  len = topic.level1NegativeKeywords.length;
  for (var i = 0; i < len; ++i) {
    var keyWord = topic.level1NegativeKeywords[i];

    // preTitle
    if (news.preTitle !== null &&
            news.preTitle !== undefined) {
      var count = countsOfWordInString(keyWord, news.preTitle);
      result += count * SCORE_TITLE_WORD_IN_LEVEL1_NEGATIVE_KEWORDS;
    }

    // title
    if (news.title !== null &&
            news.title !== undefined) {
      var count = countsOfWordInString(keyWord, news.title);
      result += count * SCORE_TITLE_WORD_IN_LEVEL1_NEGATIVE_KEWORDS;
    }

    // abstractText
    if (news.abstractText !== null &&
            news.abstractText !== undefined) {
      var count = countsOfWordInString(keyWord, news.abstractText);
      result += count * SCORE_ABSTRACT_WORD_IN_LEVEL1_NEGATIVE_KEWORDS;
    }

    // mainText
    if (news.mainText !== null &&
            news.mainText !== undefined) {
      var count = countsOfWordInString(keyWord, news.mainText);
      result += count * SCORE_TEXT_WORD_IN_LEVEL1_NEGATIVE_KEWORDS;
    }
  }

  // level 2 negative keywords
  len = topic.level2NegativeKeywords.length;
  for (var i = 0; i < len; ++i) {
    var keyWord = topic.level2NegativeKeywords[i];

    // preTitle
    if (news.preTitle !== null &&
            news.preTitle !== undefined) {
      var count = countsOfWordInString(keyWord, news.preTitle);
      result += count * SCORE_TITLE_WORD_IN_LEVEL2_NEGATIVE_KEWORDS;
    }

    // title
    if (news.title !== null &&
            news.title !== undefined) {
      var count = countsOfWordInString(keyWord, news.title);
      result += count * SCORE_TITLE_WORD_IN_LEVEL2_NEGATIVE_KEWORDS;
    }

    // abstractText
    if (news.abstractText !== null &&
            news.abstractText !== undefined) {
      var count = countsOfWordInString(keyWord, news.abstractText);
      result += count * SCORE_ABSTRACT_WORD_IN_LEVEL2_NEGATIVE_KEWORDS;
    }

    // mainText
    if (news.mainText !== null &&
            news.mainText !== undefined) {
      var count = countsOfWordInString(keyWord, news.mainText);
      result += count * SCORE_TEXT_WORD_IN_LEVEL2_NEGATIVE_KEWORDS;
    }
  }

  return result;
}

/**
 * Counts of occurances of a word in a text
 * @param {type} word
 * @param {type} text
 * @returns {undefined}
 */
function countsOfWordInString(word, text) {
  var result = 0;

  try {
    var reg = new RegExp(word, "g");
    result = (text.match(reg) || []).length;
  } catch (e) {
    console.error(e);
  }

  /*
   console.info("-----------------------------");
   console.info("word: " + word);
   console.info("text: " + text);
   console.info("score: " + result);
   */

  return result;
}

/**
 * 
 * @param {array} strings
 * @returns {Array|getWordsOfStrings.result}
 */
function getWordsOfStrings(strings) {
  var result = [];

  var l = strings.length;

  for (var i = 0; i < l; ++i) {
    var str = strings[i];

    if (str !== null && str !== undefined) {
      var parts = str.split(" ");
      var l2 = parts.length;

      for (var j = 0; j < l2; ++j) {
        var part = parts[j].toString().trim();

        //if (result.indexOf(part) < 0) {
        result.push(part);
        //}
      }
    }
  }

  return result;
}

// rule based Topic class
/*
function Topic(name, title) {
  this.name = name;
  this.title = title;
  this.rules = [];
}

Topic.prototype.addRule = function(ruleFunction) {
  this.rules.push(ruleFunction);
};

Topic.prototype.checkNews = function(news) {
  var len = this.rules.length;
  
  console.log("len: " + len);
  for (var i; i < len; ++i) {
    var rule = this.rules[i];
    console.log(i);
  }
};

var t1 = new Topic("topic1", "title1");
t1.addRule(function(news) {
  alert(news);
});
*/