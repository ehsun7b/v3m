"use strict";

var MAX_RESULT = 10;
var KEYWORDS = [
  ["راه‌آهن"],
  ["تراکتورسازی"],
  ["اولیویرا"],
  /*["لیگ قهرمانان"],*/
  ["پرسپولیس"],
  /*["استادیوم آزادی"],*/
  ["تيري هانري", "هانری"],
  ["يحيي گل محمدي", "گل‌محمدی", "گل محمدي"],
  ["ذوب آهن"],
  ["کارلوس کي‌روش", "کی‌روش"],
  ["علی دایی", "دایی"],
  ["پژمان جمشيدي", "جمشیدی"],
  ["عليرضا منصوريان", "منصوریان"],
  ["پديده"],
  ["نژاد‌فلاح"],
  ["کفاشیان"],
  ["نفت تهران"],
  ["گودرزی"],
  ["تکواندو"],
  ["شمشیربازی"],
  ["کشتي"],
  ["والیبال"],
  ["قلعه‌نویی"],
  ["سپاهان"],
  ["احسان حاج صفی"],
  ["وریا غفوری"],
  ["علی کریمی"],
  ["کفاشیان"],
  ["هدایتی"],
  ["فرشاد پيوس", "پیوس"],
  ["حمید درخشان", "درخشان"],
  ["عابدینی"],
  ["کرار"],
  ["خسرو حیدری", "حیدری"],
  ["محمد نوري", "نوری", "نوري"],
  ["عابدزاده"],
  ["استقلال‌"],
  ["عبدولی"],
  ["سوریان"],
  ["وزنه‌برداری"],
  ["کبدی"],
  ["بسکتبال"],
  
  // international
  ["منچسترسيتي", "سیتی"],
  ["بارسا", "بارسلونا"],
  ["سوارز"],
  ["گرت بیل"],
  ["رئال مادرید", "رئال"],
  ["آرين روبن", "روبن"],
  ["لئو مسي", "مسي"],
  ["ژاوی"],
  ["مارچلو ليپي", "لیپی"],
  ["لادروپ"],
  ["کازورلا"],
  ["مالاگا"],
  ["کريس رونالدو","رونالدو"],
  ["مودریچ"],
  ["راموس"],
  ["خدیرا"],
  ["سیمئونه"],
  ["اتلتیکو"],
  ["بایرن"],
  ["کاسیاس"],
  ["آنچلوتی"],
  ["اینیستا"],
  ["لوئيس سوارز", "سوارز"],
  ["اتلتیکومادرید"],
  ["بایرلورکوزن", "لورکوزن"],
  ["اینتر"],
  ["ميلان"],
  ["پاريس سنت ژرمن"],
  ["منچستريونايتد"],
  ["آرسنال"],
  ["لیورپول"]
];

// main entry point of the thread
self.addEventListener('message', function (e) {
  console.log('message received: ', e.data);

  // received news which should be classified
  var news = e.data[0];
  var newsList = e.data[1];
  var result = [];

  var newsWords = getWordsObjectsOfNews(news);
  /*
  console.log("******************");
  console.log(newsWords);
  console.log("******************");
*/
  var len = newsList.length;
  for (var i = 0; i < len; ++i) {
    var currentNews = newsList[i];
    if (currentNews.id !== news.id) {
      var currentWords = getWordsObjectsOfNews(currentNews);
      var score = Words.calcScore(newsWords, currentWords);
      if (score > 0) {
        /*
        console.log("---------------");
        console.log(currentNews.title);
        console.log(score);
        console.log(currentWords);
        console.log("---------------");*/
        currentNews.score = score;
        result.push(currentNews);
      }
    }
  }
  
  result.sort(function (a, b) { return a.score - b.score;});
  result.reverse();

  result = result.slice(0, MAX_RESULT);
  postMessage(result);
}, false);

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

function getWordsObjectsOfNews(news) {
  var result = new Words();

  var len = KEYWORDS.length;
  for (var i = 0; i < len; ++i) {
    var keyword = KEYWORDS[i];
    var l = keyword.length;
    var newsText = (news.preTitle ? news.preTitle : "") + (news.title ? news.title : "") + (news.abstractText ? news.abstractText : "") + (news.mainText ? news.mainText : "");
    var count = 0;
    for (var j = 0; j < l; ++j) {
      var word = keyword[j];
      count += countsOfWordInString(word, newsText);
      newsText = removeAll(word, newsText);
    }

    if (count > 0) {
      result.add(new Word(keyword[0], count));
    }
  }

  result.sort();
  result.reverese();

  return result;
}

function removeAll(word, text) {
  var re = new RegExp(word, 'g');
  return text.replace(re, '');
}

/**
 * Class Word
 * @param {type} value
 * @returns {Word}
 */
function Word(value, count) {
  this.value = value;
  this.count = count;
}

Word.prototype.equal = function (word) {
  if (word.value !== undefined && word.value !== null && this.value !== null) {
    return this.value === word.value;
  }
};

Word.prototype.increment = function () {
  if (this.count !== null && this.count !== undefined) {
    this.count++;
  }
};

/******************************************/

/**
 * class Words
 * @returns {Words}
 */
function Words() {
  this.words = [];
}

Words.prototype.add = function (word) {
  this.words.push(word);
};

Words.prototype.get = function (index) {
  return this.words[index];
};

Words.prototype.indexOf = function (word) {
  var len = this.words.length;
  for (var i = 0; i < len; ++i) {
    if (word.equal(this.words[i])) {
      return i;
    }
  }

  return -1;
};

Words.prototype.sort = function () {
  this.words.sort(function (a, b) {
    return a.count - b.count;
  });
};

Words.prototype.reverese = function () {
  this.words.reverse();
};

/**
 * Static method
 * @param {type} words1
 * @param {type} words2
 * @returns {undefined}
 */
Words.calcScore = function (words1, words2) {
  var result = 0;

  var len = words1.words.length;
  for (var i = 0; i < len; ++i) {
    var word1 = words1.get(i);
    var index2 = words2.indexOf(word1);
    if (index2 > -1) {
      if (i === index2) {
        result += 100;
      } else if (Math.abs(i - index2) < 3) {
        result += 50;
      } else if (Math.abs(i - index2) < 6) {
        result += 20;
      } else if (Math.abs(i - index2) < 10) {
        result += 10;
      } else {
        result += 1;
      }
    }
  }

  return result;
};
/*****************************************************/