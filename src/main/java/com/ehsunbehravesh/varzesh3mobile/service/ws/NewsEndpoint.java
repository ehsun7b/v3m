package com.ehsunbehravesh.varzesh3mobile.service.ws;

import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ehsun Behravesh
 */
@Singleton
@ServerEndpoint(
        value = "/news"
)
public class NewsEndpoint {

  @Inject
  private NewsBeanLocal newsBean;

  private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<>());
  private static final Map<String, Long> lastNewsIds = Collections.synchronizedMap(new HashMap<>());

  @OnOpen
  @Produces(MediaType.APPLICATION_JSON)
  public void onOpen(Session peer) {
    peers.add(peer);
  }

  @OnClose
  public void onClose(Session peer) {
    peers.remove(peer);
  }

  @OnMessage
  public String onMessage(Long id, Session peer) {
    lastNewsIds.put(peer.getId(), id);
    List<News> lastNews = newsBean.lastNews(id);

    List<Long> newsIds = new ArrayList<>();
    lastNews.stream().forEach((lastNew) -> {
      newsIds.add(lastNew.getId());
    });

    if (newsIds.size() > 0) {
      lastNewsIds.put(peer.getId(), newsIds.get(0));
    }

    Gson gson = new Gson();
    String json = gson.toJson(newsIds);

    return json;
  }

  @Schedule(hour = "*", minute = "*", second = "*/10")
  public void broadcast() {
    for (Session peer : peers) {
      Long id = lastNewsIds.get(peer.getId());
      if (id != null) {
        List<News> lastNews = newsBean.lastNews(id);

        List<Long> newsIds = new ArrayList<>();
        lastNews.stream().forEach((lastNew) -> {
          newsIds.add(lastNew.getId());
        });

        if (newsIds.size() > 0) {
          lastNewsIds.put(peer.getId(), newsIds.get(0));
        }

        Gson gson = new Gson();
        String json = gson.toJson(newsIds);

        peer.getAsyncRemote().sendText(json);
      }
    }
  }
}
