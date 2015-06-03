package com.ehsunbehravesh.varzesh3mobile.servlet.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "SEOFilter", urlPatterns = {"/*"})
public class SEOFilter implements Filter {

  private static final boolean debug = false;
  private FilterConfig filterConfig = null;

  private void doBeforeProcessing(ServletRequest request, ServletResponse response)
          throws IOException, ServletException {
    for (Enumeration en = request.getParameterNames(); en.hasMoreElements();) {
      String name = (String) en.nextElement();
      String[] values = request.getParameterValues(name);

      if (name.trim().equals("_escaped_fragment_")) {
        String query = "";

        if ((values != null) && (values.length > 0)) {
          for (String value : values) {
            query = query + value;
          }
        }

        ((HttpServletResponse) response).sendRedirect("http://www.varzesh3mob.com/HTMLSnapshot?query=" + query);
      }
    }
  }

  private void doAfterProcessing(ServletRequest request, ServletResponse response)
          throws IOException, ServletException {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    doBeforeProcessing(request, response);

    Throwable problem = null;
    try {
      chain.doFilter(request, response);
    } catch (Throwable t) {
      problem = t;
      t.printStackTrace();
    }

    doAfterProcessing(request, response);

    if (problem != null) {
      if ((problem instanceof ServletException)) {
        throw ((ServletException) problem);
      }
      if ((problem instanceof IOException)) {
        throw ((IOException) problem);
      }
      sendProcessingError(problem, response);
    }
  }

  public FilterConfig getFilterConfig() {
    return this.filterConfig;
  }

  public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  public void destroy() {
  }

  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
    if (filterConfig != null);
  }

  public String toString() {
    if (this.filterConfig == null) {
      return "SEOFilter()";
    }
    StringBuffer sb = new StringBuffer("SEOFilter(");
    sb.append(this.filterConfig);
    sb.append(")");
    return sb.toString();
  }

  private void sendProcessingError(Throwable t, ServletResponse response) {
    String stackTrace = getStackTrace(t);

    if ((stackTrace != null) && (!stackTrace.equals(""))) {
      try {
        response.setContentType("text/html");
        PrintStream ps = new PrintStream(response.getOutputStream());
        PrintWriter pw = new PrintWriter(ps);
        pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n");

        pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
        pw.print(stackTrace);
        pw.print("</pre></body>\n</html>");
        pw.close();
        ps.close();
        response.getOutputStream().close();
      } catch (Exception ex) {
      }
    } else {
      try {
        PrintStream ps = new PrintStream(response.getOutputStream());
        t.printStackTrace(ps);
        ps.close();
        response.getOutputStream().close();
      } catch (Exception ex) {
      }
    }
  }

  public static String getStackTrace(Throwable t) {
    String stackTrace = null;
    try {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      pw.close();
      sw.close();
      stackTrace = sw.getBuffer().toString();
    } catch (Exception ex) {
    }
    return stackTrace;
  }

  public void log(String msg) {
    this.filterConfig.getServletContext().log(msg);
  }
}
