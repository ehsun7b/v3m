package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ehsun7b
 */
@Local
public interface ImageBeanLocal {

  List<Image> noContentImages();

  void insertImage(Image image);

  void updateImage(Image image);
  
}
