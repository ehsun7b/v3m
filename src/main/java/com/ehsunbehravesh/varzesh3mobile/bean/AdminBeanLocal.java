/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Administrator;
import javax.ejb.Local;

/**
 *
 * @author Ehsun Behravesh <ehsun.behravesh@mimos.my>
 */
@Local
public interface AdminBeanLocal {

  Administrator getByUsername(final String username);
  
}
