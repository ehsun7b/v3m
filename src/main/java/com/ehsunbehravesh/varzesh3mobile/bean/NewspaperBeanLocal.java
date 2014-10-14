/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperCollection;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperPage;
import javax.ejb.Local;

/**
 *
 * @author ehsun7b
 */
@Local
public interface NewspaperBeanLocal {

  void insertCollection(NewspaperCollection collection);

  NewspaperCollection lastNewspaper();  

  NewspaperPage findPageById(final Long id);
}
