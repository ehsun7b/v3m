package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ehsun7b
 */
@Stateless
public class NewsBean implements NewsBeanLocal {

  @PersistenceContext
  private EntityManager em;
  private static final Long MAX_COUNT_LAST_NEWS = 50l;

  @Override
  public void insertNews(News news) {
    em.persist(news);
    em.flush();
  }

  @Override
  public News findByURL(final String url) {
    String sql = "SELECT n FROM News n WHERE n.url = :url";
    TypedQuery<News> query = em.createQuery(sql, News.class);
    query.setParameter("url", url);
    News news = null;

    try {
      news = query.getSingleResult();
    } catch (Exception e) {
    }

    return news;
  }

  @Override
  public void updateNews(News news) {
    em.merge(news);
    em.flush();
  }

  @Override
  public List<News> hotNews(int limit) {
    String sql = "SELECT n FROM News n WHERE n.hot = :hot ORDER BY n.id DESC";
    TypedQuery<News> query = em.createQuery(sql, News.class);
    query.setParameter("hot", Boolean.TRUE);
    query.setMaxResults(limit);
    List<News> result = query.getResultList();

    return result;
  }

  @Override
  public List<News> lastNews(int limit) {
    String sql = "SELECT n FROM News n ORDER BY n.id DESC";
    TypedQuery<News> query = em.createQuery(sql, News.class);
    query.setMaxResults(limit);
    List<News> result = query.getResultList();

    return result;
  }

  @Override
  public News findById(final Long id) {
    News news = em.find(News.class, id);
    return news;
  }

  @Override
  public List<News> footIntNews(int limit) {
    String sql = "SELECT n FROM News n WHERE n.category = :category ORDER BY n.id DESC";
    TypedQuery<News> query = em.createQuery(sql, News.class);
    query.setParameter("category", News.CAT_FOOTBALL_INTERNAL);
    query.setMaxResults(limit);
    List<News> result = query.getResultList();

    return result;
  }

  @Override
  public List<News> footExtNews(int limit) {
    String sql = "SELECT n FROM News n WHERE n.category = :category ORDER BY n.id DESC";
    TypedQuery<News> query = em.createQuery(sql, News.class);
    query.setParameter("category", News.CAT_FOOTBALL_EXTERNAL);
    query.setMaxResults(limit);
    List<News> result = query.getResultList();

    return result;
  }

  @Override
  public List<News> sportsNews(int limit) {
    String sql = "SELECT n FROM News n WHERE n.category = :category ORDER BY n.id DESC";
    TypedQuery<News> query = em.createQuery(sql, News.class);
    query.setParameter("category", News.CAT_OTHER_SPORTS);
    query.setMaxResults(limit);
    List<News> result = query.getResultList();

    return result;
  }

  @Override
  public List<News> lastNews(Long id) {
    Long lastId = getLastNewsId();
    if (lastId > id + MAX_COUNT_LAST_NEWS) {
      return lastNews(MAX_COUNT_LAST_NEWS.intValue());
    } else {
      String sql = "SELECT n FROM News n WHERE n.id > :id ORDER BY n.id DESC";
      TypedQuery<News> query = em.createQuery(sql, News.class);
      query.setParameter("id", id);
      return query.getResultList();
    }
  }

  private Long getLastNewsId() {
    String sql = "SELECT max(n.id) FROM News n";
    TypedQuery<Long> query = em.createQuery(sql, Long.class);
    return query.getSingleResult();
  }
}
