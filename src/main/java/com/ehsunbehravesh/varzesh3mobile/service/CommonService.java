package com.ehsunbehravesh.varzesh3mobile.service;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ehsun Behravesh
 */
@Path("common")
public class CommonService {
  
  
  @GET
  @Produces("application/json; charset=UTF-8")
  @Path("mobile")
  public String isMobile(@HeaderParam("user-agent") String userAgent) {
    Boolean result;
    
    result = userAgent.toLowerCase().contains("mobile") ||
            userAgent.toLowerCase().contains("android");
    
    return "{\"result\": " + result + "}";
  }
}
