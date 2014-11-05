package com.ehsunbehravesh.varzesh3mobile.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class News implements Serializable {

  public static final String CAT_FOOTBALL_INTERNAL = "foot_int";
  public static final String CAT_FOOTBALL_EXTERNAL = "foot_ext";
  public static final String CAT_UNKNOWN = "";
  public static final String CAT_OTHER_SPORTS = "sports";  
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String url;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Image mainImage;
  
  @OneToMany(mappedBy = "news", fetch = FetchType.LAZY)
  private List<Image> images;
  
  @Column(nullable = false)
  private String title;
  
  private String preTitle;
  
  @Column(length = 1024)
  private String abstractText;
  
  @Lob
  @Column(nullable = false)
  private String mainText;
  
  private String code;
  private String publishTime;
  
  @Column(nullable = false)
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date fetchTime;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private Boolean hot = false;

  public News() {
    this.category = CAT_UNKNOWN;
  }  
  
  @Override
  public String toString() {
    return MessageFormat.format("{4} Code: {0} - Publish time: {1}\n{2}\n{3}\n{4}\n\n", code, publishTime, title, abstractText, category, mainText);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof News) {
      News news1 = (News) obj;
      return Objects.equal(id, news1.id);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    Long hash = id != null ? id : -1;
    return Integer.parseInt(hash.toString());
  }
  
  
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Image getMainImage() {
    return mainImage;
  }

  public void setMainImage(Image mainImage) {
    this.mainImage = mainImage;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  
  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPreTitle() {
    return preTitle;
  }

  public void setPreTitle(String preTitle) {
    this.preTitle = preTitle;
  }

  public String getAbstractText() {
    return abstractText;
  }

  public void setAbstractText(String abstractText) {
    this.abstractText = abstractText;
  }

  public String getMainText() {
    return mainText;
  }

  public void setMainText(String mainText) {
    this.mainText = mainText;
  }

  public String getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(String publishTime) {
    this.publishTime = publishTime;
  }

  public Date getFetchTime() {
    return fetchTime;
  }

  public void setFetchTime(Date fetchTime) {
    this.fetchTime = fetchTime;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Boolean getHot() {
    return hot;
  }

  public void setHot(Boolean hot) {
    this.hot = hot;
  }  
}
