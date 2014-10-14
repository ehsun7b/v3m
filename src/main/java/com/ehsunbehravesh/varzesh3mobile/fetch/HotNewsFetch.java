package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
public class HotNewsFetch {

  private static final String url = "http://www.varzesh3.com";

  public List<News> fetch() throws IOException {
    List<News> result = new ArrayList<>();
    
    Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
    
    Elements topNewsDivs = doc.select("div.topnews-img");
    
    for (Element topNewsDiv : topNewsDivs) {
      Element topLink = topNewsDiv.select("a[href]").get(0);
      String absUrl = topLink.absUrl("href");
      
      News news = new News();
      news.setUrl(absUrl);
      
      result.add(news);
    }
    
    return result;
  }
}
