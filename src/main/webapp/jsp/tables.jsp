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
    <title>جدول لیگ های فوتبال</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-rtl.min.css"/>
    <link rel="stylesheet" href="../css/layout.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <style type="text/css">
      body {
        direction: rtl;
      }

      table {
        border: 1px solid black;
        border-collapse: collapse;
        border-radius: 3px;
        margin: 5px 0px;
        border-spacing: 0px;
      }

      table table {
        border: 0px solid black;
        border-collapse: collapse;
        border-radius: 03px;
        margin: 0px;
      }

      tr.trheader td {
        background-color: black;
        color: white;
        font-weight: bold;
      }

      tr.tr2header td {
        background-color: white;
        color: black;
        font-weight: bold;
      }

      td {
        padding: 2px;
      }

      tr.row1 td {
        background-color: #ffffee;
        color: black;
      }      

      tr.row2 td {
        background-color: #ffffcc;
        color: black;
      }
    </style>
  </head>
  <body class="nasim">
    <div class="container">
      <div class="row">        
        <c:out value="${iranLeagueTable}" escapeXml="false"/>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- varzesh3mob_news -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7135665999"
             data-ad-format="auto"></ins>
        <script>
                  (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
      <div class="row">
        <c:out value="${spainLeagueTable}" escapeXml="false"/>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- varzesh3mob_news -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7135665999"
             data-ad-format="auto"></ins>
        <script>
                  (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
      <div class="row">
        <c:out value="${englandLeagueTable}" escapeXml="false"/>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- varzesh3mob_news -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7135665999"
             data-ad-format="auto"></ins>
        <script>
                  (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
      <div class="row">
        <c:out value="${italyLeagueTable}" escapeXml="false"/>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- varzesh3mob_news -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7135665999"
             data-ad-format="auto"></ins>
        <script>
                  (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
      <div class="row">
        <c:out value="${germanyLeagueTable}" escapeXml="false"/>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- varzesh3mob_news -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7135665999"
             data-ad-format="auto"></ins>
        <script>
                  (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
      <div class="row">
        <c:out value="${franceLeagueTable}" escapeXml="false"/>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- varzesh3mob_news -->
        <ins class="adsbygoogle"
             style="display:block"
             data-ad-client="ca-pub-1305937802991389"
             data-ad-slot="7135665999"
             data-ad-format="auto"></ins>
        <script>
                  (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
      </div>
    </div>


    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
    <script src="/js/appTables.js"></script>
    <script src="/js/controllers/TablesCtrl.js"></script>
  </body>
</html>

