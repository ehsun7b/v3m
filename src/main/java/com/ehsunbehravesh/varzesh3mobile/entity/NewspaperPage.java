package com.ehsunbehravesh.varzesh3mobile.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ehsun7b
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewspaperPage implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @XmlTransient
  @Column(nullable = false)
  private String originalUrl;

  @Column(nullable = false)
  private String title;

  @XmlTransient
  @ManyToOne
  private NewspaperCollection collection;

  @Lob
  @Column(nullable = false)
  @XmlTransient
  private byte[] content;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public NewspaperCollection getCollection() {
    return collection;
  }

  public void setCollection(NewspaperCollection collection) {
    this.collection = collection;
  }

}
