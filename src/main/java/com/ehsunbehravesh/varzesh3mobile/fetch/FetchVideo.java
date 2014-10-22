package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.varzesh3mobile.entity.Video;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Ehsun Behravesh
 */
public class FetchVideo {

  public Video fetch(String url) throws MalformedURLException, IOException {
    Video result = null;
    
    Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
    
    Element div = doc.select("div.index-box").get(0);
    Element h2 = div.select("h2").get(0);
    String title = h2.text();
    
    String thumbnailURL = div.select("meta[itemprop=thumbnailURL]").get(0).attr("content");
    String contentURL = div.select("meta[itemprop=contentURL]").get(0).attr("content");
    String width = div.select("meta[itemprop=width]").get(0).attr("content");
    String height = div.select("meta[itemprop=height]").get(0).attr("content");
    String description = div.select("meta[itemprop=description]").get(0).attr("content");
    
    result = new Video();
    
    result.setTitle(title);
    result.setURL(url);
    result.setThumbnailURL(thumbnailURL);
    result.setContentURL(contentURL);
    result.setWidth(width);
    result.setHeight(height);
    result.setDescription(description);
    result.setFetchTime(new Date());
    
    return result;
  }
  
  public static void main(String[] args) {
    try {
      FetchVideo fetch = new FetchVideo();
      
      Video video = fetch.fetch("http://video.varzesh3.com/video-clips/margins/%D9%BE%DB%8C%D8%B4-%D8%A8%D8%A7%D8%B2%DB%8C-%D8%A7%D8%B3%D8%AA%D9%82%D9%84%D8%A7%D9%84-%D9%BE%D8%AF%DB%8C%D8%AF%D9%87/");
      
      System.out.println(video.getTitle());
      System.out.println(video.getContentURL());
      System.out.println(video.getThumbnailURL());
      System.out.println(video.getDescription());
      System.out.println(video.getWidth());
      System.out.println(video.getHeight());
      System.out.println(video.getURL());
    } catch (IOException ex) {
      Logger.getLogger(FetchVideo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
