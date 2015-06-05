package com.ehsunbehravesh.varzesh3mobile.fetch;

import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ehsun Behravesh
 */
public class FetchRankingIran {

  private static final String URL = "http://www.varzesh3.com/leaguecomshow.do?machesid=standing90-900129-900507-full";

  public String fetchHTML() throws Exception {
    Document doc = Jsoup.parse(new URL(URL).openStream(), "UTF-8", URL);
    Element table = doc.select("div#anc-op").get(0).select("table").get(0);

    String html = clean(table);

    return html;
  }

  private String clean(Element table) {
    Elements fonts = table.select("font");

    for (Element font : fonts) {
      font.attr("face", "Nassim");
    }

    return table.outerHtml();
  }  
}
