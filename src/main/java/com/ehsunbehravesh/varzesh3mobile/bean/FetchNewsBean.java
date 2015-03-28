package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import com.ehsunbehravesh.varzesh3mobile.entity.ImageContent;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchImageContent;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchNews;
import com.ehsunbehravesh.varzesh3mobile.fetch.FootballExternalFetch;
import com.ehsunbehravesh.varzesh3mobile.fetch.FootballInternalFetch;
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

  @Schedule(hour = "*", minute = "*/10")
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
    } catch (Exception ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(hour = "*", minute = "5/10")
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
    } catch (Exception ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  @Schedule(hour = "*", minute = "3/10")
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
    } catch (Exception ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }

  //@Schedule(hour = "*", minute = "*", second = "*/20")
  /*public void fetchTest() {
    Logger.getLogger(FetchNewsBean.class.getName()).log(Level.INFO, "Test news ...");
    try {
      List<News> lastNews = newsBean.lastNews(1000);
      Collections.reverse(lastNews);
      News news = lastNews.get(0);
      News newNews = new News();
      newNews.setTitle(news.getTitle());
      newNews.setUrl(news.getUrl());
      newNews.setMainText(news.getMainText());
      Image img = new Image();
      img.setOriginalURL(news.getMainImage().getOriginalURL());
      newNews.setMainImage(img);
      newNews.setFetchTime(news.getFetchTime());
      newNews.setPublishTime(news.getPublishTime());
      newNews.setAbstractText(news.getAbstractText());
      newsBean.insertNews(newNews);
    } catch (Exception ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, ex.getMessage());
    }
  }*/

  @Schedule(hour = "*", minute = "10/15")
  @Override
  public void fetchImagesContent() {
    try {
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
    } catch (Exception ex) {
      Logger.getLogger(FetchNewsBean.class.getName()).log(Level.SEVERE, "Fetching image content failed. {0}", ex.getMessage());
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
