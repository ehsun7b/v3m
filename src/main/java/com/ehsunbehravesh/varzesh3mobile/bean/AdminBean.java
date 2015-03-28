package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.utils.security.SecurityUtils;
import com.ehsunbehravesh.varzesh3mobile.entity.Administrator;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

  @Override
  public Administrator getByUsername(final String username) {
    if (username.equalsIgnoreCase("ehsun7b")) {
      try {
        Administrator admin = new Administrator();
        admin.setUsername("ehsun7b");
        admin.setPassword(SecurityUtils.SHA256("13621215"));
        return admin;
      } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
        Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return null;
  }

  
}
