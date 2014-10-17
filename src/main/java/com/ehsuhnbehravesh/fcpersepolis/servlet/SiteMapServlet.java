package com.ehsuhnbehravesh.fcpersepolis.servlet;

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

/**
 *
 * @author Ehsun Behravesh
 */
@WebServlet(name = "SiteMapServlet", urlPatterns = {"/sitemap"})
public class SiteMapServlet extends HttpServlet {

  @Inject
  private NewsBeanLocal newsBean;

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/xml;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      out.println("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\""
              + " xmlns:mobile=\"http://www.google.com/schemas/sitemap-mobile/1.0\""
              + " xmlns:news=\"http://www.google.com/schemas/sitemap-news/0.9\""
              + " xmlns:video=\"http://www.google.com/schemas/sitemap-video/1.1\">");

      out.println("<url><loc>http://varzesh3mob.com/#!/intfoot</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/extfoot</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/sports</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/newspapers</loc></url>");
      out.println("<url><loc>http://varzesh3mob.com/#!/tv</loc></url>");

      printNewsSiteMap(out);

      out.println("</urlset>");
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void printNewsSiteMap(PrintWriter out) {
    List<News> hotNews = newsBean.hotNews();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
    
    for (News hotNew : hotNews) {
      out.println("<url>");
      out.println(MessageFormat.format("<loc>http://varzesh3mob.com/#!/news/{0}</loc>", hotNew.getId()));
      out.println("<news:news>");
      out.println("<news:publication>");

      out.println("<news:name>ورزش ۳ | موبایل</news:name>");
      out.println("<news:language>fa</news:language>");

      out.println("</news:publication>");
            
      out.println("<news:genres>PressRelease</news:genres>");
      out.println(MessageFormat.format("<news:publication_date>{0}</news:publication_date>", format.format(hotNew.getFetchTime())));
      out.println(MessageFormat.format("<news:title>{0}</news:title>", hotNew.getTitle()));

      out.println("</news:news>");
      out.println("</url>");

    }
  }

}
