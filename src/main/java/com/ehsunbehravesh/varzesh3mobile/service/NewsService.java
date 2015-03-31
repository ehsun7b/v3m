package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.ehsunbehravesh.varzesh3mobile.service.dto.CUDResponse;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
  @Path("hot/{limit}")
  @Produces("application/json; charset=UTF-8")
  public List<News> hotNews(@PathParam("limit") int limit) {
    List<News> hotNews = newsBean.hotNews(limit);
    return hotNews;
  }

  @GET
  @Path("last/{limit}")
  @Produces("application/json; charset=UTF-8")
  public List<News> lastNews(@PathParam("limit") int limit) {
    List<News> hotNews = newsBean.lastNews(limit);
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

  @PUT
  @Path("hot/{id}")
  @Produces("application/json; charset=UTF-8")
  public CUDResponse makeHot(@PathParam("id") Long id) {
    CUDResponse result = new CUDResponse();

    News news = newsBean.findById(id);

    try {
      if (news != null) {
        news.setHot(Boolean.TRUE);
      } else {
        result.setMessage("News not found!");
      }
      result.setSuccess(Boolean.TRUE);
    } catch (Exception ex) {
      result.setSuccess(Boolean.FALSE);
      result.setError(ex.getMessage());
    }

    return result;
  }
}
