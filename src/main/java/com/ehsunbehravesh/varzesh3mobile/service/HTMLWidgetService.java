package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.fetch.FetchTVPrograms;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author ehsun7b
 */
@Path("widget")
public class HTMLWidgetService {

  @GET
  @Path("tv")
  @Produces("text/html; charset=UTF-8")
  public Response tvPrograms() {
    FetchTVPrograms fetch = new FetchTVPrograms();
    try {
      String content = fetch.fetch();
      Response response = Response.ok(content).build();
      return response;
    } catch (Exception ex) {
      Logger.getLogger(HTMLWidgetService.class.getName()).log(Level.WARNING, "Error in fetching TV programs. {0}", ex);
      Response response = Response.serverError().build();
      return response;
    }
  }
}
