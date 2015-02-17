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
public class FootballInternalFetch {

  private static final String URL = "http://www.varzesh3.com/files/leftnewsFootInt.asp";

  public List<News> fetch() throws IOException {
    List<News> result = new ArrayList<>();
    Document doc = Jsoup.connect(URL).get();

    Elements links = doc.select("a[href]");

    for (Element link : links) {
      String url = link.absUrl("href");
      url = url.replace("/files", "");
      News news = new News();
      news.setUrl(FetchNews.removeTitleFromURL(url));
      news.setCategory(News.CAT_FOOTBALL_INTERNAL);

      result.add(news);
    }

    return result;
  }

}
