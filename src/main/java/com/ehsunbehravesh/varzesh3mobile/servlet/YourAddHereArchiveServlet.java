package com.ehsunbehravesh.varzesh3mobile.servlet;

import java.io.IOException;
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
@WebServlet(name = "YourAddHereArchiveServlet", urlPatterns = {"/youraddhere/320/50"})
public class YourAddHereArchiveServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    /*
     ServletContext cntx = getServletContext();
     // Get the absolute path of the image
     String filename = cntx.getRealPath("img/ad_320_50.gif");
     // retrieve mimeType dynamically
     String mime = cntx.getMimeType(filename);
     if (mime == null) {
     resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
     return;
     }

     resp.setContentType(mime);
     File file = new File(filename);
     resp.setContentLength((int) file.length());

     FileInputStream in = new FileInputStream(file);
     OutputStream out = resp.getOutputStream();

     // Copy the contents of the file to the output stream
     byte[] buf = new byte[1024];
     int count = 0;
     while ((count = in.read(buf)) >= 0) {
     out.write(buf, 0, count);
     }
     out.close();
     in.close();*/
    RequestDispatcher rd = req.getRequestDispatcher("/html/backupadarchive.html");
    rd.forward(req, resp);
  }

  @Override
  public String getServletInfo() {
    return "Image for ad 320 x 50";
  }

}
