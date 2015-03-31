package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.bean.UserBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ehsun Behravesh
 */
@Path("user")
@Stateless
public class UserService {
    
    @Inject
    private UserBeanLocal userBean;
    
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public void insert(User user) {
        
        userBean.insertUser(user);
    }
}
