package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.User;
import javax.ejb.Local;

/**
 *
 * @author Ehsun Behravesh
 */
@Local
public interface UserBeanLocal {

    void insertUser(final User user);
    
}
