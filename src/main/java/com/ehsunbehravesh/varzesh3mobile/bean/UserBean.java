package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertUser(final User user) {
        em.persist(user);
        em.flush();
    }
    
    
    
}
