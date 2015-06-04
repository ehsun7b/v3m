package com.ehsunbehravesh.varzesh3mobile.servlet;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.bean.NewspaperBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.bean.VideoBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperCollection;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperPage;
import com.ehsunbehravesh.varzesh3mobile.entity.Video;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HTMLSnapshotServlet", urlPatterns = {"/HTMLSnapshot"})
public class HTMLSnapshotServlet extends HttpServlet {

  @Inject
  private NewsBeanLocal newsBean;

  @Inject
  private VideoBeanLocal videoBean;

  @Inject
  private NewspaperBeanLocal newspaperBean;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    Throwable localThrowable3 = null;
    try {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>ورزش ۳ | موبایل</title>");

      printOpenGraphTags(request, out);
    } catch (Throwable localThrowable1) {
      localThrowable3 = localThrowable1;
      throw localThrowable1;
    } finally {
      if (out != null) {
        if (localThrowable3 != null) {
          try {
            out.close();
          } catch (Throwable localThrowable2) {
            localThrowable3.addSuppressed(localThrowable2);
          }
        } else {
          out.close();
        }
      }
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  public String getServletInfo() {
    return "Short description";
  }

  private void printOpenGraphTags(HttpServletRequest request, PrintWriter out) {
    String hashParameters = request.getParameter("query");

    String title = "";
    String siteName = "varzesh3mob";
    String url = "";
    String description = "";
    String image = "";
    String appId = "623178241126464";
    StringBuilder newsList = null;

    News news = null;
    Video video = null;

    if ((hashParameters == null) || (hashParameters.trim().length() <= 0)) {
      title = "ورزش ۳ | نسخه موبایل";
      url = "http://www.varzesh3mob.com";
      description = "آخرین اخبار ورزشی بهینه شده برای موبایل";

      try {
        List<News> lastNews = newsBean.lastNews(50);
        newsList = new StringBuilder("<h1>آخرین اخبار</h1>");
        
        for (News newsItem : lastNews) {
          newsList.append("<article>");
          newsList.append("<h4>".concat(newsItem.getPreTitle()).concat("</h4>"));
          newsList.append("<h1>".concat(newsItem.getTitle()).concat("</h1>"));
          newsList.append("<p>".concat(newsItem.getAbstractText()).concat("</p>"));
          newsList.append("<a href='http://varzesh3mob.com/#!/news/".concat(newsItem.getId() + "").concat("'>ادامه ...</a>"));
          newsList.append("</article>");
        }
      } catch (Exception ex) {
        Logger.getLogger(HTMLSnapshotServlet.class.getName()).log(Level.SEVERE, "Error in fetching the last news list! ".concat(ex.getMessage()));
      }

    } else if (hashParameters.trim().equalsIgnoreCase("/intfoot")) {
      title = "اخبار فوتبال hdvhk";
      url = "http://varzesh3mob.com/#!/intfoot";
      description = "آخرین اخبار فوتبالی در ایران";
    } else if (hashParameters.trim().equalsIgnoreCase("/extfoot")) {
      title = "اخبار فوتبال جهان";
      url = "http://varzesh3mob.com/#!/extfoot";
      description = "آخرین اخبار فوتبالی در جهان";
    } else if (hashParameters.trim().equalsIgnoreCase("/sports")) {
      title = "اخبار ورزشی";
      url = "http://varzesh3mob.com/#!/sports";
      description = "آخرین اخبار ورزشی ایران و جهان";
    } else if (hashParameters.trim().equalsIgnoreCase("/newspapers")) {
      title = "روزنامه‌های ورزشی";
      url = "http://varzesh3mob.com/#!/newspapers";
      description = "صفحه اول روزنامه‌های ورزشی ایران";
      image = "http://varzesh3mob.com/img/newspapers.png";

      try {
        NewspaperCollection lastNewspaper = newspaperBean.lastNewspaper();
        NewspaperPage page = lastNewspaper.getNewspapers().get(0);
        Long id = page.getId();

        if (id != null && id < 0) {
          image = "http://varzesh3mob.com/service/image/newspaper/" + id + "/photo.png";
        }
      } catch (Exception ex) {
        Logger.getLogger(HTMLSnapshotServlet.class.getName()).log(Level.SEVERE, "Error in fetching the last newspaper! ".concat(ex.getMessage()));
      }

    } else if (hashParameters.trim().equalsIgnoreCase("/results")) {
      title = "نتایج زنده و جدول لیگ برتر";
      url = "http://varzesh3mob.com/#!/results";
      description = "نتایج لحظه به لحظه بازی‌ها و جدول کامل لیگ برتر";
      //image = "http://varzesh3mob.com/img/newspapers.png";
    } else if (hashParameters.trim().equalsIgnoreCase("/tv")) {
      title = "جدول پخش ورزش از تلویزیون";
      url = "http://varzesh3mob.com/#!/tv";
      image = "http://varzesh3mob.com/img/tv.png";
    } else if (hashParameters.trim().startsWith("/news")) {
      String[] parts = hashParameters.trim().split("/");

      if (parts.length > 2) {
        int i = 0;

        for (; i < parts.length; i++) {
          String part = parts[i];
          if (part.equalsIgnoreCase("news")) {
            i++;
            break;
          }
        }

        Long id = Long.parseLong(parts[i].replace(",", ""));
        news = newsBean.findById(id);

        if (news != null) {
          title = news.getTitle();
          url = "http://varzesh3mob.com/#!/news/" + news.getId();
          description = news.getAbstractText();
          image = "http://varzesh3mob.com/service/image/news/main/250/250/" + news.getId() + "/photo.png";
        }
      }
    } else if (hashParameters.trim().startsWith("/play")) {
      String[] parts = hashParameters.trim().split("/");

      if (parts.length > 2) {
        int i = 0;

        for (; i < parts.length; i++) {
          String part = parts[i];
          if (part.equalsIgnoreCase("play")) {
            i++;
            break;
          }
        }

        Long id = Long.parseLong(parts[i].replace(",", ""));
        video = videoBean.findByID(id);

        if (video != null) {
          title = video.getTitle() + " (ویدئو)";
          url = "http://varzesh3mob.com/#!/play/" + video.getId();
          description = video.getDescription();
          image = video.getThumbnailURL();
        }
      }
    }

    String template = "<meta property=\"{0}\" content=\"{1}\" />";

    out.println(MessageFormat.format(template, new Object[]{"og:title", title}));
    out.println(MessageFormat.format(template, new Object[]{"og:site_name", siteName}));
    out.println(MessageFormat.format(template, new Object[]{"og:url", url}));
    out.println(MessageFormat.format(template, new Object[]{"og:description", description}));
    out.println(MessageFormat.format(template, new Object[]{"og:image", image}));

    if (video != null) {
      out.println(MessageFormat.format(template, new Object[]{"og:video", video.getContentURL()}));
      out.println(MessageFormat.format(template, new Object[]{"og:video:height", video.getHeight()}));
      out.println(MessageFormat.format(template, new Object[]{"og:video:width", video.getWidth()}));
      out.println(MessageFormat.format(template, new Object[]{"og:video:type", "video/mp4"}));

      out.println(MessageFormat.format(template, new Object[]{"thumbnailURL", video.getThumbnailURL()}));
      out.println(MessageFormat.format(template, new Object[]{"contentURL", video.getContentURL()}));
      out.println(MessageFormat.format(template, new Object[]{"width", video.getWidth()}));
      out.println(MessageFormat.format(template, new Object[]{"height", video.getHeight()}));
    }

    out.println(MessageFormat.format(template, new Object[]{"fb:app_id", appId}));

    out.println("</head>");
    out.println("<body style='direction: rtl'>");

    if (news != null) {
      out.println("<h4>" + news.getPreTitle() + "</h4>");
    }
    out.println("<h1>" + title + "</h1>");

    if (news != null) {
      out.println("<p>" + news.getAbstractText() + "</p>");
      out.println("<p>" + news.getMainText() + "</p>");
    }
    
    if (newsList != null) {
      out.println(newsList.toString());
    }

    out.println("</body>");
    out.println("</html>");
  }
}
