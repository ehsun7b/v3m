package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import com.ehsunbehravesh.varzesh3mobile.entity.ImageContent;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchImageContent;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchNews;
import com.ehsunbehravesh.varzesh3mobile.fetch.FootballExternalFetch;
import com.ehsunbehravesh.varzesh3mobile.fetch.FootballInternalFetch;
import com.ehsunbehravesh.varzesh3mobile.fetch.HotNewsFetch;
import com.ehsunbehravesh.varzesh3mobile.fetch.SportsFetch;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
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
public class FetchNewsBean implements FetchNewsBeanLocal {

  @Inject
  private NewsBeanLocal newsBean;

  @Inject
  private ImageBeanLocal imageBean;

  @Schedule(hour = "*", minute = "*/10", persistent = false)
  @Override
  public void fetchFootballExternal() {
    try {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Fetching football external news...");
      FootballExternalFetch fetch = new FootballExternalFetch();
      List<News> newsList = fetch.fetch();

      newsList = Lists.reverse(newsList);
      
      for (News news : newsList) {
        News found = newsBean.findByURL(news.getUrl());

        if (found == null) {
          FetchNews fetchNews = new FetchNews();
          News fetched = null;

          try {
            fetched = fetchNews.fetch(news.getUrl(), news.getCategory());
          } catch (Exception ex) {
            Logger.getLogger(FetchNewsBean.class.getName()).log(Level.WARNING, "Error in fetching news content! ".concat(ex.getMessage()));
          }

          if (fetched != null) {
            newsBean.insertNews(fetched);
          }

        }
      }
    } catch (IOException ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(hour = "*", minute = "5/10", persistent = false)
  @Override
  public void fetchFootballInternal() {
    Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Fetching football internal news...");
    try {
      FootballInternalFetch fetch = new FootballInternalFetch();
      List<News> newsList = fetch.fetch();

      newsList = Lists.reverse(newsList);
      
      for (News news : newsList) {
        News found = newsBean.findByURL(news.getUrl());

        if (found == null) {
          FetchNews fetchNews = new FetchNews();
          News fetched = null;

          try {
            fetched = fetchNews.fetch(news.getUrl(), news.getCategory());
          } catch (Exception ex) {
            Logger.getLogger(FetchNewsBean.class.getName()).log(Level.WARNING, "Error in fetching news content! ".concat(ex.getMessage()));
          }

          if (fetched != null) {
            newsBean.insertNews(fetched);
          }

        }
      }
    } catch (IOException ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(hour = "*", minute = "3/10", persistent = false)
  @Override
  public void fetchSports() {
    Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Fetching sports news...");
    try {
      SportsFetch fetch = new SportsFetch();
      List<News> newsList = fetch.fetch();

      newsList = Lists.reverse(newsList);
      
      for (News news : newsList) {
        News found = newsBean.findByURL(news.getUrl());

        if (found == null) {
          FetchNews fetchNews = new FetchNews();
          News fetched = null;

          try {
            fetched = fetchNews.fetch(news.getUrl(), news.getCategory());
          } catch (Exception ex) {
            Logger.getLogger(FetchNewsBean.class.getName()).log(Level.WARNING, "Error in fetching news content! ".concat(ex.getMessage()));
          }

          if (fetched != null) {
            newsBean.insertNews(fetched);
          }

        }
      }
    } catch (IOException ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  //@Schedule(hour = "*", minute = "8/10", persistent = false)
  @Override
  public void fetchHotNes() {
    Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Fetching hot news...");
    try {
      HotNewsFetch fetch = new HotNewsFetch();
      List<News> newsList = fetch.fetch();

      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "New fetched hot news: {0}", newsList.size());

      if (newsList.size() > 0) {
        List<News> oldHotNews = newsBean.hotNews();
        Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Old hot news: {0}", oldHotNews.size());        
        
        for (News oldHNews : oldHotNews) {
          if (!exist(oldHNews, newsList)) {
            oldHNews.setHot(Boolean.FALSE);
            newsBean.updateNews(oldHNews);
          }
        }
      }

      newsList = Lists.reverse(newsList);
      
      for (News news : newsList) {
        News found = newsBean.findByURL(news.getUrl());

        if (found == null) {
          FetchNews fetchNews = new FetchNews();
          News fetched = null;

          try {
            fetched = fetchNews.fetch(news.getUrl(), news.getCategory());
          } catch (Exception ex) {
            Logger.getLogger(FetchNewsBean.class.getName()).log(Level.WARNING, "Error in fetching news content! ".concat(ex.getMessage()));
          }

          if (fetched != null) {
            fetched.setHot(Boolean.TRUE);
            newsBean.insertNews(fetched);
          }

        } else {
          found.setHot(Boolean.TRUE);
          newsBean.updateNews(found);
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(hour = "*", minute = "10/15", persistent = false)
  @Override
  public void fetchImagesContent() {
    List<Image> images = imageBean.noContentImages();
    Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Images with no content: {0}", images.size());

    for (Image image : images) {
      FetchImageContent fetch = new FetchImageContent(image.getOriginalURL());
      ImageContent imageContent = new ImageContent();
      byte[] content;
      try {
        content = fetch.fetch();
        if (content != null && content.length > 0) {
          imageContent.setContent(content);
          image.setContent(imageContent);

          imageBean.updateImage(image);
        }
      } catch (IOException ex) {
        Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, "Fetching image content failed. {0}", ex.getMessage());
      }
    }
  }

  private boolean exist(News oldHNews, List<News> newsList) {
    for (News news : newsList) {
      if (oldHNews.getUrl().trim().equals(news.getUrl().trim())) {
        return true;
      }
    }
    
    return false;
  }

}
