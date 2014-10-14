package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsuhnbehravesh.fcpersepolis.net.RankingFetch;
import com.ehsuhnbehravesh.fcpersepolis.news.Ranking;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author ehsun7b
 */
@Stateless
public class RankingBean implements RankingBeanLocal {

  private static final long MAX_CACHE = 10 * 60 * 1000; // 10 minutes
  private static Ranking ranking;
  private static long lastLoad;
  
  @Override
  public Ranking ranking() {
    if (ranking != null && System.currentTimeMillis() - lastLoad < MAX_CACHE) {
      return ranking;
    } else {
      RankingFetch fetch = new RankingFetch();
      try {
        Ranking newRanking = fetch.load();
        ranking = newRanking;
        return ranking;
      } catch (Exception ex) {
        Logger.getLogger(RankingBean.class.getName()).log(Level.SEVERE, null, ex);
        if (ranking != null) {
          return ranking;
        }
      }
    }
    
    return null;
  }
  
  
}
