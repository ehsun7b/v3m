package com.ehsunbehravesh.varzesh3mobile.servlet;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ehsun Behravesh <post@ehsunbehravesh.com>
 */
@WebServlet(name = "MainEntryServlet", urlPatterns = {"/news/*"})
public class NewsServlet extends HttpServlet {

  @Inject
  private NewsBeanLocal newsBean;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    Long newsId;
    String url = request.getRequestURI();
    String strId = url.replaceFirst("/news/", "");
    
    try {
      newsId = Long.parseLong(strId);
      News news = newsBean.findById(newsId);
      request.setAttribute("news", news);
    } catch (Exception ex) {
      Logger.getLogger(NewsServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/news.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  public String getServletInfo() {
    return "Main entry point.";
  }

  

}
