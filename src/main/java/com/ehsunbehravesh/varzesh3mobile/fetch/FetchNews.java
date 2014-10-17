package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author ehsun7b
 */
public class FetchNews {

  private static final String codeTitle = "کد خبر:";
  private static final String timeTitle = "زمان ارسال:";
  private static final String visitsTitle = "تعداد بازدید:";


  public News fetch(String url, String category) throws IOException {
    News result;
    
    //Document doc = Jsoup.connect(url).get();
    Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
    result = new News();
    
    Element headTable = doc.select("table#NewsTable").get(0);
    Element tr1 = headTable.select("tr").get(0);
    Element td1 = tr1.select("td").get(0);
    Element fontDate = td1.select("font").get(0);
    String text = fontDate.text();
    String code = text.substring(text.indexOf(codeTitle) + codeTitle.length(), text.indexOf(timeTitle)).trim();
    String publishTime = text.substring(text.indexOf(timeTitle) + timeTitle.length(), text.indexOf(visitsTitle)).trim();

    result.setCode(code);
    result.setPublishTime(publishTime);

    try {
      Element p1 = td1.select("p").get(0);
      String preTitle = p1.text().trim();
      result.setPreTitle(preTitle);
    } catch (Exception ex) {
      // some news have no pre title
    }
    
    Element h1 = td1.select("h1").get(0);
    String title = h1.text().trim();
    result.setTitle(title);

    Element h2 = td1.select("h2").get(0);
    String abstractText = h2.text().trim();
    result.setAbstractText(abstractText);
    
    Element td2 = tr1.select("td").get(1);
    Element imgMain = td2.select("img").get(0);
    String imgUrl = imgMain.absUrl("src");
    String imgText = imgMain.attr("title");
    
    Image imageMain = new Image();
    imageMain.setOriginalURL(imgUrl);
    imageMain.setText(imgText);
    result.setMainImage(imageMain);
    
    Element bodyTable = headTable.nextElementSibling();
    Element tr2 = bodyTable.select("tr").get(1);
    Element tr2td1 = tr2.select("td").get(0);
    Element div = tr2td1.select("div").get(0);
    Element font = div.select("font").get(0);    
    
    String mainText = font.html();
    
    result.setMainText(mainText);

    result.setCategory(category);
    result.setFetchTime(new Date());
    result.setUrl(url);  
    
    System.out.println(result);
    
    return result;
  }
}
