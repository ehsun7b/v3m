package com.ehsunbehravesh.varzesh3mobile.bean;

import javax.ejb.Local;

/**
 *
 * @author ehsun7b
 */
@Local
public interface FetchNewsBeanLocal {

  void fetchFootballExternal();
  
  void fetchFootballInternal();  
  
  void fetchSports();

  void fetchImagesContent();

  //public void fetchHotNes();
  
}
