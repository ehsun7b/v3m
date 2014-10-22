/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Video;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ehsun Behravesh
 */
@Local
public interface VideoBeanLocal {

  void insertVideo(Video video);

  Video findByURL(String URL);

  List<Video> videos(int limit);

  Video findByID(final Long id);
  
}
