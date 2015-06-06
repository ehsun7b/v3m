package com.ehsunbehravesh.varzesh3mobile.servlet;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="SiteMapServlet", urlPatterns={"/sitemap"})
public class SiteMapServlet extends HttpServlet
{

  @Inject
  private NewsBeanLocal newsBean;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/xml;charset=UTF-8");
    PrintWriter out = response.getWriter(); Throwable localThrowable3 = null;
    try {
      out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      out.println("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:mobile=\"http://www.google.com/schemas/sitemap-mobile/1.0\" xmlns:news=\"http://www.google.com/schemas/sitemap-news/0.9\" xmlns:video=\"http://www.google.com/schemas/sitemap-video/1.1\">");

      out.println("<url><loc>http://varzesh3mob.com/#!/intfoot</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/extfoot</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/sports</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/newspapers</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/tv</loc></url>");

      printNewsSiteMap(out);

      out.println("</urlset>");
    }
    catch (Throwable localThrowable1)
    {
      localThrowable3 = localThrowable1; throw localThrowable1;
    }
    finally
    {
      if (out != null) if (localThrowable3 != null) try { out.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else out.close();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  public String getServletInfo()
  {
    return "Short description";
  }

  private void printNewsSiteMap(PrintWriter out) {
    List<News> hotNews = this.newsBean.lastNews(100);

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    for (News hotNew : hotNews) {
      out.println("<url>");
      out.println(MessageFormat.format("<loc>http://varzesh3mob.com/news/{0}</loc>", new Object[] { hotNew.getId().toString() }));
      out.println("<news:news>");
      out.println("<news:publication>");

      out.println("<news:name>ورزش ۳ | موبایل</news:name>");
      out.println("<news:language>fa</news:language>");

      out.println("</news:publication>");

      out.println("<news:genres>PressRelease</news:genres>");
      out.println(MessageFormat.format("<news:publication_date>{0}</news:publication_date>", new Object[] { format.format(hotNew.getFetchTime()) }));
      out.println(MessageFormat.format("<news:title>{0}</news:title>", new Object[] { hotNew.getTitle() }));

      out.println("</news:news>");
      out.println("</url>");
    }
  }
}