package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingIran;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ehsun Behravesh
 */
@Path("ranking")
public class RankingService {

  @GET
  @Path("iran")
  @Produces("text/html; charset=UTF-8")
  public String iranRanking() throws Exception {
    FetchRankingIran fetch = new FetchRankingIran();

    String result = "";

    try {
      result = fetch.fetchHTML();
    } catch (Exception ex) {
      Logger.getLogger(LiveResultsService.class.getName()).log(Level.SEVERE, "Error in fetching results! {0}", ex);
    }

    return result;
  }
}
