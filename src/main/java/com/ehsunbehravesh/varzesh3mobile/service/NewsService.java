package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author ehsun7b
 */
@Path("news")
@Stateless
public class NewsService {

  @Inject
  private NewsBeanLocal newsBean;

  @GET
  @Path("hot")
  @Produces("application/json; charset=UTF-8")
  public List<News> hotNews() {
    List<News> hotNews = newsBean.hotNews();
    return hotNews;
  }

  @GET
  @Path("{id}")
  @Produces("application/json; charset=UTF-8")
  public News news(@PathParam("id") Long id) {
    News news = newsBean.findById(id);
    news.setMainText(news.getMainText().replaceAll("\n", "<br/>"));
    return news;
  }

  @GET
  @Path("foot/int/{limit}")
  @Produces("application/json; charset=UTF-8")
  public List<News> intFootNews(@PathParam("limit") int limit) {
    List<News> newsList = newsBean.footIntNews(limit);
    return newsList;
  }

  @GET
  @Path("foot/ext/{limit}")
  @Produces("application/json; charset=UTF-8")
  public List<News> extFootNews(@PathParam("limit") int limit) {
    List<News> newsList = newsBean.footExtNews(limit);
    return newsList;
  }

  @GET
  @Path("sports/{limit}")
  @Produces("application/json; charset=UTF-8")
  public List<News> sportsNews(@PathParam("limit") int limit) {
    List<News> newsList = newsBean.sportsNews(limit);
    return newsList;
  }
}
