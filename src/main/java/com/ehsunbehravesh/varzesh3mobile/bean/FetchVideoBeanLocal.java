/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.varzesh3mobile.bean;

import javax.ejb.Local;

/**
 *
 * @author Ehsun Behravesh
 */
@Local
public interface FetchVideoBeanLocal {

  void fetchVideos();
  
}
