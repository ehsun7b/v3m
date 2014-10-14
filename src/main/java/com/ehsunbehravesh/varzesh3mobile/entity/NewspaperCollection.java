package com.ehsunbehravesh.varzesh3mobile.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ehsun7b
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewspaperCollection implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<NewspaperPage> newspapers;

  @Column(nullable = false)
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private final Date fetchTime;

  public NewspaperCollection() {
    newspapers = new ArrayList<>();
    fetchTime = new Date();
  }

  public List<NewspaperPage> getNewspapers() {
    return newspapers;
  }

  public void setNewspapers(List<NewspaperPage> newspapers) {
    this.newspapers = newspapers;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getFetchTime() {
    return fetchTime;
  }

}
