<%-- 
    Document   : tables
    Created on : Jun 5, 2015, 3:46:55 PM
    Author     : Ehsun Behravesh <ehsun.behravesh@mimos.my>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-ng-app="appTables" data-ng-controller="TablesCtrl">
  <head>
    <!-- Google Analytics -->
    <script>
          (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
              (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
          })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

          ga('create', 'UA-55220061-1', 'auto');
          ga('send', 'pageview');
          //ga('set', '&uid', {{USER_ID}}); // Set the user ID using signed-in user_id.
    </script>
    <title><c:out value="${news.title}"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap-rtl.min.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <style type="text/css">
      @font-face {
        font-family: "Nassim";
        src: url("/fnt/nassim-regular.eot");
        src: local("☺"),
          url("/fnt/nassim-regular.woff") format("woff"),
          url("/fnt/nassim-regular.ttf") format("truetype");
        font-weight: normal;
        font-style: normal;
      }
      @font-face {
        font-family: "Nassim";
        src: url("/fnt/nassim-bold.eot");
        src: local("☺"),
          url("/fnt/nassim-bold.woff") format("woff"),
          url("/fnt/nassim-bold.ttf") format("truetype");
        font-weight: bold;
        font-style: normal;
      }

      bod {
        direction: rtl;  
      }

      .nasim {
        font-family: "Nassim",Arial,Verdana,Geneva,Helvetica,sans-serif;
      }

      /* NEWS */

      div.news {
        border: 0px dashed red;
      }

      div.news div.newsImage {  
        float: left;
        padding-right: 10px;
        padding-bottom: 10px;
        text-align: left;
        border: 0px dotted green;
        max-width: 50%;
      }

      div.news div.newsImage img {
        border-radius: 5px;
        max-width: 99%;
      }

      div.news div.newsDetails {
        font-size: 12px;
        text-align: left;
        color: #6633ff;
      }

      div.news .newsDetails {

      }

      div.news div.newsPreTitle {
        font-size: 14px;
        color: #999;
        width: 100%;
      }

      div.news div.newsTitle {
        font-size: 22px;
        font-weight: bold;
        color: black;
        width: 100%;
      }

      div.news div.newsAbstract, div.news div.newsText {
        text-align: justify;
        font-size: 16px;
        width: 100%;
      }

      div.newsText div[align=center] img {
        margin: 5px 0;
        display: block;
        padding: 3px;
        box-shadow: 0px 0px 3px 1px #CCC;  
      }

      div.newsText img {
        max-width: 99%;
      }

      div.news {
        padding: 20px;
      }

      .vertical-adver {
        margin: 10px 0px; text-align: center;
      }
    </style>
  </head>
  <body class="nasim">
    <div class="container-fluid">
      <div class="row">        

        <div class="vertical-adver col-lg-2 col-md-2 hidden-xs hidden-sm">
          <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
          <!-- varzesh3mob_news_desktop_vertical -->
          <ins class="adsbygoogle"
               style="display:inline-block;width:120px;height:600px"
               data-ad-client="ca-pub-1305937802991389"
               data-ad-slot="9851799999"></ins>
          <script>
          (adsbygoogle = window.adsbygoogle || []).push({});
          </script>
        </div>

        <div class="news col-lg-8 col-md-8 col-sm-12 col-xs-12">
          <div class="newsImage"><img src="/service/image/news/main/480/320/<c:out value="${news.id}"/>/photo.png" alt="<c:out value="${news.title}"/>"></div>

          <div class="newsDetails"><span class="newsPublishTime ng-binding"><c:out value="${news.publishTime}"/> &nbsp;&nbsp;</span>&nbsp;کد <span class="newsCode ng-binding"><c:out value="${news.code}" />&nbsp;</span></div>
          <div class="newsPreTitle"><c:out value="${news.preTitle}" /></div>
          <div class="newsTitle"><c:out value="${news.title}"/></div>
          <div class="newsAbstract"><c:out value="${news.abstractText}"/></div>
          <div class="newsText" id="mainText"><c:out value="${news.mainText}" escapeXml="false"/></div>
          <br/>
          <a class="btn btn-default" href="/">صفحه اصلی</a>
        </div>

        <div class="vertical-adver col-lg-2 col-md-2 hidden-xs hidden-sm">
          <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
          <!-- varzesh3mob_news_desktop_vertical -->
          <ins class="adsbygoogle"
               style="display:inline-block;width:120px;height:600px"
               data-ad-client="ca-pub-1305937802991389"
               data-ad-slot="9851799999"></ins>
          <script>
          (adsbygoogle = window.adsbygoogle || []).push({});
          </script>
        </div>

        <div class="hidden-md hidden-lg col-xs-12 col-sm-12">
          <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
          <!-- varzesh3mob_news_mobile_horizontal -->
          <ins class="adsbygoogle"
               style="display:inline-block;width:320px;height:100px"
               data-ad-client="ca-pub-1305937802991389"
               data-ad-slot="2328533193"></ins>
          <script>
          (adsbygoogle = window.adsbygoogle || []).push({});
          </script>
        </div>
      </div>
    </div>


    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
    <script src="/js/appTables.js"></script>
    <script src="/js/controllers/TablesCtrl.js"></script>
  </body>
</html>

