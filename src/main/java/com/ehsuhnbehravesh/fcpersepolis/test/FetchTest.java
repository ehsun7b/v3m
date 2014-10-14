package com.ehsuhnbehravesh.fcpersepolis.test;

import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchNews;
import com.ehsunbehravesh.varzesh3mobile.fetch.FootballExternalFetch;
import com.ehsunbehravesh.varzesh3mobile.fetch.FootballInternalFetch;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ehsun7b
 */
public class FetchTest {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {

      try {
        List<News> list = new FootballInternalFetch().fetch();
        FetchNews f = new FetchNews();
        for (News news : list) {
          try {
            news = f.fetch(news.getUrl(), news.getCategory());
            System.out.println(news.getTitle() + "\n" + news.getAbstractText() + "\n");
          } catch (Exception ex) {
          }
        }

        list = new FootballExternalFetch().fetch();
        f = new FetchNews();
        for (News news : list) {
          try {
            news = f.fetch(news.getUrl(), news.getCategory());
            System.out.println(news.getTitle() + "\n" + news.getAbstractText() + "\n");
          } catch (Exception ex) {
          }
        }
      } catch (Exception ex) {
      }
    }
  }
}
