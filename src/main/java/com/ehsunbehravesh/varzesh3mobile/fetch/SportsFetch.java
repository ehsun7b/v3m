package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
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
public class SportsFetch {

  private static final String URL = "http://www.varzesh3.com";

  public List<News> fetch() throws IOException {
    List<News> result = new ArrayList<>();
    Document doc = Jsoup.connect(URL).get();

    Element div = doc.select("div#identifierwidget-302").get(0);

    Elements links = div.select("a[href]");

    for (Element link : links) {
      String url;
      url = link.absUrl("href");
      url = url.replace("/files", "");
      News news = new News();
      news.setUrl(url);
      news.setCategory(News.CAT_OTHER_SPORTS);

      result.add(news);
    }

    return result;
  }
}
