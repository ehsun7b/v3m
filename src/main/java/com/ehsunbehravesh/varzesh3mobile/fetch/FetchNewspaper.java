package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.utils.image.ThumbnailUtils;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperCollection;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperPage;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
public class FetchNewspaper {

  private static final String URL = "http://www.varzesh3.com/";
  
  public NewspaperCollection fetch() {
    NewspaperCollection result = new NewspaperCollection();
    
    try {
      Document doc = Jsoup.parse(new URL(URL).openStream(), "UTF-8", URL);
      
      Element galleryDiv = doc.select("div.gallery-box").get(0);
      Elements links = galleryDiv.select("ul").get(0).select("a[href]");
      
      for (Element link : links) {
        String url = link.absUrl("href");
        Element p = (Element) link.nextSibling();
        String title = p.text();
        
        int tries = 0;
        byte[] content = null;
        
        while ((content = fetchImageContent(url)) == null && tries++ < 4) {
          Logger.getLogger(FetchNewspaper.class.getName()).log(Level.INFO, "Trying to fetch {0} for the {1} time.", new Object[] {url, tries});
        }
        
        if (content != null && title != null && title.trim().length() > 0) {
          NewspaperPage newspaper = new NewspaperPage();
          newspaper.setOriginalUrl(url);
          newspaper.setTitle(title);
          newspaper.setContent(content);
          newspaper.setCollection(result);
          
          result.getNewspapers().add(newspaper);
        } 
      }
      
    } catch (Exception ex) {
      Logger.getLogger(FetchNewspaper.class.getName()).log(Level.WARNING, "Error in fetching newspaper collection. {0}", ex.getMessage());
    }
    
    return result;
  }

  private byte[] fetchImageContent(String url) {
    try {
      Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
      
      Element img = doc.select("div#newspaper-large-container").get(0).select("img").get(0);
      url = img.absUrl("src");
      
      BufferedImage image = ThumbnailUtils.fetchImage(url);
      if (image != null) {
        return ThumbnailUtils.toByteArray(image, "png");
      }
    } catch (Exception ex) {
      Logger.getLogger(FetchNewspaper.class.getName()).log(Level.WARNING, "Error in fetching newspaper content. {0}", ex.getMessage());
    }
    
    return null;
  }
}
