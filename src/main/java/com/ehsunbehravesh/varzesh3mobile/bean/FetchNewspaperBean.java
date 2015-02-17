package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperCollection;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchNewspaper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author ehsun7b
 */
@Singleton
public class FetchNewspaperBean implements FetchNewspaperBeanLocal {

  @Inject
  private NewspaperBeanLocal newspaperBean;
  
  @Schedule(hour = "8,9,10,11,12,13,14,15", minute = "30", second = "0")
  //@Schedule(hour = "*", minute = "*", second = "30")
  @Override
  public void fetchNewspaper() {
    Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Fetching newspapers ...");
    FetchNewspaper fetch = new FetchNewspaper();
    NewspaperCollection collection = fetch.fetch();
    
    if (collection != null && collection.getNewspapers().size() > 0) {
      newspaperBean.insertCollection(collection);
    }
  }
}
