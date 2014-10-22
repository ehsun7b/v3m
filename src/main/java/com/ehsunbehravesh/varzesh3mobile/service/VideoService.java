package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.varzesh3mobile.bean.VideoBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.Video;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ehsun Behravesh
 */
@Path("video")
@Stateless
public class VideoService {
  
  @Inject 
  private VideoBeanLocal videoBean;
  
  @GET
  @Path("all/{limit}")
  @Produces("application/json; charset=UTF-8")
  public List<Video> videos(@PathParam("limit") int limit) {
    List<Video> videos = videoBean.videos(limit);
    return videos;
  }
  
  @GET
  @Path("{id}")
  @Produces("application/json; charset=UTF-8")
  public Video video(@PathParam("id") long id) {
    Video video = videoBean.findByID(id);
    return video;
  }
}
