package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ehsun7b
 */
@Local
public interface NewsBeanLocal {

  void insertNews(News news);

  News findByURL(final String url);

  void updateNews(News news);

  List<News> hotNews(int limit);

  News findById(final Long id);

  List<News> footIntNews(int limit);
  
  List<News> footExtNews(int limit);
  
  List<News> sportsNews(int limit);
}
