package com.ehsunbehravesh.varzesh3mobile.fetch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author ehsun7b
 */
public class FetchImageContent {

  private static final int BUFFER_SIZE = 2048;
  private final String url;

  public FetchImageContent(String url) {
    this.url = url;
  }

  public byte[] fetch() throws MalformedURLException, IOException {
    URL url1 = new URL(url);

    URLConnection connection = url1.openConnection();

    try (InputStream is = connection.getInputStream()) {
      ByteArrayOutputStream os = new ByteArrayOutputStream();

      byte[] buffer = new byte[BUFFER_SIZE];
      int len = 0;

      while ((len = is.read(buffer)) > 0) {
        os.write(buffer, 0, len);
      }

      os.flush();

      return os.toByteArray();
    }
  }
}
