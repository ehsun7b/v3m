package com.ehsunbehravesh.varzesh3mobile.fetch;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ehsun7b
 */
public class FetchTVPrograms {

  private static final String URL = "http://www.varzesh3.com";
 
  public String fetch() throws IOException {
    Document doc = Jsoup.connect(URL).get();

    Elements widget = doc.select("div#identifierwidget-7");
    Element widgetContent = widget.select("div.widget-content").get(0);
    
    if (widgetContent != null) {
      return widgetContent.html();
    }
    
    return null;
  }
  
}
