package com.ehsunbehravesh.varzesh3mobile.service;

import com.ehsunbehravesh.utils.image.ThumbnailUtils;
import com.ehsunbehravesh.varzesh3mobile.bean.ImageBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.bean.NewsBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.bean.NewspaperBeanLocal;
import com.ehsunbehravesh.varzesh3mobile.entity.Image;
import com.ehsunbehravesh.varzesh3mobile.entity.ImageContent;
import com.ehsunbehravesh.varzesh3mobile.entity.News;
import com.ehsunbehravesh.varzesh3mobile.entity.NewspaperPage;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author ehsun7b
 */
@Path("image")
@Stateless
public class ImageService {

  @Inject
  private NewsBeanLocal newsBean;

  @Inject
  private ImageBeanLocal imageBean;

  @Inject
  private NewspaperBeanLocal newspaperBean;

  //<editor-fold defaultstate="collapsed" desc="NEWS IMAGE">
  @GET
  @Path("news/main/{newsId}")
  @Produces("image/png")
  public Response newsMainImage(@PathParam("newsId") Long newsId) {

    News news = newsBean.findById(newsId);

    if (news != null) {
      Image mainImage = news.getMainImage();

      if (mainImage != null) {
        ImageContent content = mainImage.getContent();

        if (content == null) {
          content = loadContentFromURL(mainImage.getOriginalURL());
        }

        if (content != null) {
          final byte[] byteContent = content.getContent();

          if (byteContent != null && byteContent.length > 0) {
            return Response.ok().entity(new StreamingOutput() {

              @Override
              public void write(OutputStream output) throws IOException, WebApplicationException {
                output.write(byteContent);
                output.flush();
              }
            }).build();
          }
        }
      }
    }

    Response response = Response.status(Response.Status.NOT_FOUND).entity("Not found!").build();
    return response;
  }

  @GET
  @Path("news/main/{width}/{height}/{newsId}")
  @Produces("image/png")
  public Response newsMainImageThumbnail(
          @PathParam("width") Integer width,
          @PathParam("height") Integer height,
          @PathParam("newsId") Long newsId) {

    News news = newsBean.findById(newsId);

    if (news != null) {
      Image mainImage = news.getMainImage();

      if (mainImage != null) {
        ImageContent content = mainImage.getContent();

        if (content == null) {
          content = loadContentFromURL(mainImage.getOriginalURL());
        }

        if (content == null) {
          content = loadContentFromURL(mainImage.getOriginalURL());
        }

        if (content != null) {
          try {
            final byte[] byteContent = content.getContent();
            final BufferedImage image = ThumbnailUtils.toImage(byteContent);
            final BufferedImage thumbnail = ThumbnailUtils.thumbnail(image, new Dimension(width, height));
            final byte[] thumbContent = ThumbnailUtils.toByteArray(thumbnail, "png");

            if (byteContent != null && byteContent.length > 0) {
              return Response.ok().entity(new StreamingOutput() {

                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                  output.write(thumbContent);
                  output.flush();
                }
              }).build();
            }
          } catch (IOException ex) {
            Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, "Error in generating thumbnail!", ex);
          }
        }
      }
    }

    Response response = Response.status(Response.Status.NOT_FOUND).entity("Not found!").build();
    return response;
  }

  @GET
  @Path("news/main/f/{width}/{height}/{newsId}")
  @Produces("image/png")
  public Response newsMainImageForceThumbnail(
          @PathParam("width") Integer width,
          @PathParam("height") Integer height,
          @PathParam("newsId") Long newsId) {

    News news = newsBean.findById(newsId);

    if (news != null) {
      Image mainImage = news.getMainImage();

      if (mainImage != null) {
        ImageContent content = mainImage.getContent();

        if (content == null) {
          content = loadContentFromURL(mainImage.getOriginalURL());
        }

        if (content != null) {
          try {
            final byte[] byteContent = content.getContent();
            final BufferedImage image = ThumbnailUtils.toImage(byteContent);
            final BufferedImage thumbnail = ThumbnailUtils.thumbnailForce(image, new Dimension(width, height));
            final byte[] thumbContent = ThumbnailUtils.toByteArray(thumbnail, "png");

            if (byteContent != null && byteContent.length > 0) {
              return Response.ok().entity(new StreamingOutput() {

                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                  output.write(thumbContent);
                  output.flush();
                }
              }).build();
            }
          } catch (IOException ex) {
            Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, "Error in generating thumbnail!", ex);
          }
        }
      }
    }

    Response response = Response.status(Response.Status.NOT_FOUND).entity("Not found!").build();
    return response;
  }

  @GET
  @Path("news/main/c/{width}/{height}/{newsId}")
  @Produces("image/png")
  public Response newsMainImageCropThumbnail(
          @PathParam("width") Integer width,
          @PathParam("height") Integer height,
          @PathParam("newsId") Long newsId) {

    News news = newsBean.findById(newsId);

    if (news != null) {
      Image mainImage = news.getMainImage();

      if (mainImage != null) {
        ImageContent content = mainImage.getContent();

        if (content == null) {
          content = loadContentFromURL(mainImage.getOriginalURL());
        }

        if (content != null) {
          try {
            final byte[] byteContent = content.getContent();
            final BufferedImage image = ThumbnailUtils.toImage(byteContent);
            final BufferedImage thumbnail = ThumbnailUtils.thumbnailCrop(image, new Dimension(width, height));
            final byte[] thumbContent = ThumbnailUtils.toByteArray(thumbnail, "png");

            if (byteContent != null && byteContent.length > 0) {
              return Response.ok().entity(new StreamingOutput() {

                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                  output.write(thumbContent);
                  output.flush();
                }
              }).build();
            }
          } catch (IOException ex) {
            Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, "Error in generating thumbnail!", ex);
          }
        }
      }
    }

    Response response = Response.status(Response.Status.NOT_FOUND).entity("Not found!").build();
    return response;
  }
  // </editor-fold>

  //<editor-fold defaultstate="collapsed" desc="NEWSPAPER PAGE IMAGE">
  @GET
  @Path("newspaper/{pageId}")
  @Produces("image/png")
  public Response newspaperPageImage(@PathParam("pageId") Long pageId) {

    NewspaperPage page = newspaperBean.findPageById(pageId);

    if (page != null) {
      byte[] content = page.getContent();

      if (content == null) {
        BufferedImage fetchImage;
        try {
          fetchImage = ThumbnailUtils.fetchImage(page.getOriginalUrl());
          content = ThumbnailUtils.toByteArray(fetchImage, "PNG");
        } catch (IOException | URISyntaxException ex) {
          Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, "Error in fetching newspaper image content", ex);
        }
      }

      if (content != null) {
        if (content.length > 0) {
          final byte[] byteContent = content;
          
          return Response.ok().entity(new StreamingOutput() {

            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
              output.write(byteContent);
              output.flush();
            }
          }).build();
        }
      }
    }

    Response response = Response.status(Response.Status.NOT_FOUND).entity("Not found!").build();
    return response;
  }
  //</editor-fold>

  private ImageContent loadContentFromURL(String imageUrl) {
    try {
      BufferedImage fetchImage = ThumbnailUtils.fetchImage(imageUrl);

      if (fetchImage != null) {
        byte[] byteImage = ThumbnailUtils.toByteArray(fetchImage, "png");

        if (byteImage != null && byteImage.length > 0) {
          ImageContent content = new ImageContent();
          content.setContent(byteImage);
          return content;
        }
      }
    } catch (IOException | URISyntaxException ex) {
      Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE, "Error in fetching news image content", ex);
    }

    return null;
  }
}
