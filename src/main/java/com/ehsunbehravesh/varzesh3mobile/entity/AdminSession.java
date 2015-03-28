package com.ehsunbehravesh.varzesh3mobile.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ehsun Behravesh
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminSession implements Serializable {

  @Id
  private String id;
  
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date creationDate;
}
