package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
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
public class ImageBean implements ImageBeanLocal {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Image> noContentImages() {
    String sql = "SELECT im FROM Image im WHERE im.content is null";
    TypedQuery<Image> query = em.createQuery(sql, Image.class);
    List<Image> result = query.getResultList();

    return result;
  }

  @Override
  public void insertImage(Image image) {
    em.persist(image);
    em.flush();
  }

  @Override
  public void updateImage(Image image) {
    em.merge(image);
    em.flush();
  }

}
