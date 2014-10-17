package com.ehsuhnbehravesh.fcpersepolis.servlet;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
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
@WebServlet(name = "HTMLSnapshotServlet", urlPatterns = {"/HTMLSnapshot"})
public class HTMLSnapshotServlet extends HttpServlet {

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
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>ورزش ۳ | موبایل</title>");

      printOpenGraphTags(request, out);
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

  private void printOpenGraphTags(HttpServletRequest request, PrintWriter out) {
    String hashParameters = request.getParameter("query");

    String title = "";
    String siteName = "varzesh3mob";
    String url = "";
    String description = "";
    String image = "";
    String appId = "623178241126464";

    News news = null;
    
    if (hashParameters == null || hashParameters.trim().length() <= 0) {
      title = "ورزش ۳ | نسخه موبایل";
      url = "http://www.varzesh3mob.com";
      description = "آخرین اخبار ورزشی بهینه شده برای موبایل";
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
    } else if (hashParameters.trim().equalsIgnoreCase("/tv")) {
      title = "جدول پخش ورزش از تلویزیون";
      url = "http://varzesh3mob.com/#!/tv";      
    } else if (hashParameters.trim().startsWith("/news")) {
      String[] parts = hashParameters.trim().split("/");

      for (String part : parts) {
      }
      
      if (parts.length > 2) {
        Long id = Long.parseLong(parts[2]);
        news = newsBean.findById(id);

        if (news != null) {
          title = news.getTitle();
          url = "http://varzesh3mob.com/#!/news/" + news.getId();
          description = news.getAbstractText();
          image = "http://varzesh3mob.com/service/image/news/main/250/250/" + news.getId() + "/photo.png";
        }
      }
    }

    String template = "<meta property=\"{0}\" content=\"{1}\" />";

    out.println(MessageFormat.format(template, "og:title", title));
    out.println(MessageFormat.format(template, "og:site_name", siteName));
    out.println(MessageFormat.format(template, "og:url", url));
    out.println(MessageFormat.format(template, "og:description", description));
    out.println(MessageFormat.format(template, "og:image", image));
    
    System.out.println("999999999999999 " + image);
    
    out.println(MessageFormat.format(template, "fb:app_id", appId));

    out.println("</head>");
    out.println("<body>");

    if (news != null) {
      out.println("<h4>" + news.getPreTitle() + "</h4>");
    }
    out.println("<h1>" + title + "</h1>");

    if (news != null) {
      out.println("<p>" + news.getAbstractText() + "</p>");
      out.println("<p>" + news.getMainText() + "</p>");
    }
    
    out.println("</body>");
    out.println("</html>");
  }

}
