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
public class FetchLiveResults {

  private static final String URL = "http://live.varzesh3.com/livescores.do";

  public String fetchHTML() throws Exception {
    Document doc = Jsoup.parse(new URL(URL).openStream(), "UTF-8", URL);
    Element table = doc.select("div#main").get(0).select("table").get(0);

    String html = clean(table);

    return html;
  }

  private String clean(Element table) {
    Elements links = table.select("a");

    for (Element link : links) {
      link.removeAttr("href");
      link.removeAttr("onclick");
    }

    Elements imgs = table.select("img");

    for (Element img : imgs) {
      String src = img.absUrl("src");

      if (src.contains("info1")) {
        img.remove();
      } else {
        img.attr("src", src);
      }
    }

    Elements fonts = table.select("font");
    
    for (Element font : fonts) {
      font.attr("face", "Nassim");
    }
    
    return table.outerHtml();
  }

}
