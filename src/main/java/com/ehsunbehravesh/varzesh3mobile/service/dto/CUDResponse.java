package com.ehsunbehravesh.varzesh3mobile.service.dto;

import java.io.Serializable;

/**
 *
 * @author Ehsun Behravesh <ehsun.behravesh@mimos.my>
 */
public class CUDResponse implements Serializable {
  
  private Boolean success;
  private String message;
  private String error;

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
  
  
}
