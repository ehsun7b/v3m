package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
public class FetchNews {

  private static final String codeTitle = "کد خبر:";
  private static final String timeTitle = "زمان:";
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
    System.out.println(text);
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

    String imgUrl = null;
    String imgText = null;

    try {
      Element td2 = tr1.select("td").get(1);
      Element imgMain = td2.select("img").get(0);
      imgUrl = imgMain.absUrl("src");
      imgText = imgMain.attr("title");
    } catch (Exception ex) {
      // some news have no main image in the main table (Photo news)
    }

    Element bodyTable = headTable.nextElementSibling();
    Element tr2 = bodyTable.select("tr").get(1);
    Element tr2td1 = tr2.select("td").get(0);
    Element div = tr2td1.select("div").get(0);
    Element font = div.select("font").get(0);

    String mainText = font.html();
    Element nextDiv = div.nextElementSibling();

    if (nextDiv != null) {
      mainText += nextDiv.html();
    }

    result.setMainText(mainText);

    result.setCategory(category);
    result.setFetchTime(new Date());
    result.setUrl(url);

    Image imageMain = new Image();
    if (imgUrl != null && imgText != null) {
      imageMain.setOriginalURL(imgUrl);
      imageMain.setText(imgText);
    } else { // it's an photo news
      if (mainText != null) {
        Document mainTextElement = Jsoup.parse(mainText);
        Elements imgs = mainTextElement.select("img");
        if (imgs.size() > 0) {
          Element imgMain = imgs.get(0);
          imgUrl = imgMain.absUrl("src");
          imgText = imgMain.attr("title");

          imageMain.setOriginalURL(imgUrl);
          imageMain.setText(imgText);
        }
      }
    }

    if (imageMain.getOriginalURL() != null) {
      result.setMainImage(imageMain);
    }

    return result;
  }
  
  public static void main(String[] args) {
    try {
      String url = "http://www.varzesh3.com/news.do?itemid=1191887&title=%D9%86%D9%8A%D9%84%D8%B3%D9%88%D9%86:-%D9%81%D9%88%D8%AA%D8%A8%D8%A7%D9%84%D9%85-%D8%B1%D8%A7-%D8%AF%D8%B1-%D8%A7%D9%8A%D8%B1%D8%A7%D9%86-%D8%A7%D8%AF%D8%A7%D9%85%D9%87-%D8%AE%D9%88%D8%A7%D9%87%D9%85-%D8%AF%D8%A7%D8%AF";
      FetchNews f = new FetchNews();
      News news = f.fetch(url, "");
      System.out.println("title: " + news.getTitle());
      System.out.println(news.getMainImage().getOriginalURL());
    } catch (IOException ex) {
      Logger.getLogger(FetchNews.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
