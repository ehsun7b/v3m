package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperCollection;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperPage;
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
public class NewspaperBean implements NewspaperBeanLocal {

  @PersistenceContext
  private EntityManager em;
  
  @Override
  public void insertCollection(NewspaperCollection collection) {
    em.persist(collection);
  }

  @Override
  public NewspaperCollection lastNewspaper() {
    String sql = "SELECT nc FROM NewspaperCollection nc ORDER BY nc.id DESC";
    TypedQuery<NewspaperCollection> query = em.createQuery(sql, NewspaperCollection.class);
    List<NewspaperCollection> result = query.setMaxResults(1).getResultList();
    
    if (result.size() == 1) {
      return result.get(0);
    } else {
      return null;
    }
  }

  @Override
  public NewspaperPage findPageById(final Long id) {
    return em.find(NewspaperPage.class, id);
  }  
}
