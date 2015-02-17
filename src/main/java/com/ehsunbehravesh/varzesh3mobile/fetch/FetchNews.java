package com.ehsunbehravesh.varzesh3mobile.fetch;

import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import java.net.URL;
import java.util.Date;
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

  public News fetch(String url, String category) throws Exception {
    News result;

    try {
      Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
      result = new News();

      Element headTable = doc.select("table#NewsTable").get(0);
      Element tr1 = headTable.select("tr").get(0);
      Element td1 = tr1.select("td").get(0);
      Element fontDate = td1.select("font").get(0);
      String text = fontDate.text();
      String code = text.substring(text.indexOf(codeTitle) + codeTitle.length(), text.indexOf(timeTitle)).trim();
      String publishTime = text.substring(text.indexOf(timeTitle) + timeTitle.length(), text.indexOf(visitsTitle)).trim();

      result.setCode(code.trim());
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
        try {
          Element centerTable = doc.select("table#CenterTable").get(0);
          Element imgMain = centerTable.select("img").get(0);

          imgUrl = imgMain.absUrl("src");
          imgText = imgMain.attr("title");
        } catch (Exception ex2) {

        }
      }

      Element bodyTable = headTable.nextElementSibling();
      Element tr2 = bodyTable.select("tr").get(1);
      Element tr2td1 = tr2.select("td").get(0);
      //Element div = tr2td1.select("div").get(0);
      //Element font = div.select("font").get(0);
      Elements ps = tr2td1.select("p");
      Elements div = tr2td1.select("div");

      StringBuilder mainText = new StringBuilder();

      for (Element p : ps) {
        Elements fonts = p.select("font");
        if (fonts.size() > 0) {
          for (Element font : fonts) {
            mainText.append(font.html());
          }
        } else {
          mainText.append(p.html());
        }
      }

      for (Element d : div) {
        Elements fonts = d.select("font");
        if (fonts.size() > 0) {
          for (Element font : fonts) {
            mainText.append(font.html());
          }
        } else {
          if (!d.classNames().contains("itemTagsBlock")) {
            mainText.append(d.html());
          }
        }
      }

      result.setMainText(mainText.toString());

      result.setCategory(category);
      result.setFetchTime(new Date());
      result.setUrl(removeTitleFromURL(url));

      Image imageMain = new Image();
      if (imgUrl != null && imgText != null) {
        imageMain.setOriginalURL(imgUrl);
        imageMain.setText(imgText);
      }

      if (imageMain.getOriginalURL() != null) {
        result.setMainImage(imageMain);
      }

    } catch (Exception ex) {
      throw new Exception(ex.getMessage() + " - URL: " + url);
    }
    return result;
  }

  public static String removeTitleFromURL(String url) {
    int indexOfTitle = url.indexOf("&title");
    if (indexOfTitle > 0) {
      return url.substring(0, indexOfTitle);
    }

    return url;
  }
}