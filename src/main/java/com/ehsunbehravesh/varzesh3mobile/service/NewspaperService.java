package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.bean.NewspaperBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperCollection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ehsun7b
 */
@Path("newspaper")
@Stateless
public class NewspaperService {

  @Inject
  private NewspaperBeanLocal newspaperBean;

  @GET
  @Produces("application/json; charset=UTF-8")
  @Path("last")
  public NewspaperCollection last() {
    NewspaperCollection result = newspaperBean.lastNewspaper();
    
    return result;
  }
}
