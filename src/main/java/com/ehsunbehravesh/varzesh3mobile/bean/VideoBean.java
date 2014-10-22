package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Video;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
public class VideoBean implements VideoBeanLocal {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void insertVideo(Video video) {
    em.persist(video);
    em.flush();
  }

  @Override
  public Video findByURL(String URL) {
    String sql = "SELECT v FROM Video v WHERE v.URL = :url";
    TypedQuery<Video> query = em.createQuery(sql, Video.class);
    query.setParameter("url", URL);
    Video result = null;

    try {
      result = query.getSingleResult();
    } catch (Exception e) {
    }

    return result;
  }

  @Override
  public List<Video> videos(int limit) {
     String sql = "SELECT v FROM Video v ORDER BY v.id DESC";
    TypedQuery<Video> query = em.createQuery(sql, Video.class);
    query.setMaxResults(limit);
    List<Video> result = query.getResultList();

    return result;
  }

  @Override
  public Video findByID(final Long id) {
    Video video = em.find(Video.class, id);
    return video;
  }

  
  
}
