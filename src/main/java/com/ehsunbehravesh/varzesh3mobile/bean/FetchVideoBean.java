package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.entity.Video;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchVideo;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ehsun Behravesh
 */
@Singleton
public class FetchVideoBean implements FetchVideoBeanLocal {

  private static final String url = "http://video.varzesh3.com/";

  @Inject
  private VideoBeanLocal videoBean;

  @Schedule(hour = "*", minute = "*/1", persistent = false)
  @Override
  public void fetchVideos() {
    Logger.getLogger(FetchVideoBean.class.getName()).log(Level.INFO, "Fetching videos ...");
    try {
      Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
      FetchVideo fetch = new FetchVideo();
      Elements lis = doc.select("li.firstpage-li");

      for (Element li : lis) {
        String url = li.select("a").get(0).absUrl("href");

        if (videoBean.findByURL(url) == null) {
          try {
            Video video = fetch.fetch(url);

            if (video != null) {

              videoBean.insertVideo(video);
            }

          } catch (Exception ex) {
            Logger.getLogger(FetchVideoBean.class.getName()).log(Level.WARNING, "Error in fetching video: {0} {1}",
                    new Object[]{url, ex.getMessage()});
          }
        }
      }

    } catch (MalformedURLException ex) {
      Logger.getLogger(FetchVideoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    } catch (IOException ex) {
      Logger.getLogger(FetchVideoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }

  }

}
