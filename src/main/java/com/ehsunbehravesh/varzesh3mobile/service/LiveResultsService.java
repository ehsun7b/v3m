package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.fetch.FetchLiveResults;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ehsun Behravesh
 */
@Path("results")
public class LiveResultsService {

  @GET
  @Produces("text/html; charset=UTF-8")
  public String getResults() {
    FetchLiveResults fetch = new FetchLiveResults();
    String result = "";

    try {
      result = fetch.fetchHTML();
    } catch (Exception ex) {
      Logger.getLogger(LiveResultsService.class.getName()).log(Level.SEVERE, "Error in fetching results! {0}", ex);
    }

    return result;
  }
}
