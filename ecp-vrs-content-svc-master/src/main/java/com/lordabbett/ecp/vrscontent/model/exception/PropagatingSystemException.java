package com.lordabbett.ecp.vrscontent.model.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(value = Include.NON_NULL)
public class PropagatingSystemException {

  @JsonProperty("debug_id")
  private String debugId;
  @JsonProperty("title")
  private String httpStatus;
  @JsonProperty("status")
  private Integer httpStatusCode;
  @JsonProperty("message")
  private String message;
  @JsonProperty("details")
  private List<Detail> details;

  public String getDebugId() {
    return debugId;
  }

  public void setDebugId(String debugId) {
    this.debugId = debugId;
  }

  public String getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  public Integer getHttpStatusCode() {
    return httpStatusCode;
  }

  public void setHttpStatusCode(Integer httpStatusCode) {
    this.httpStatusCode = httpStatusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<Detail> getDetails() {
    return details;
  }

  public void setDetails(List<Detail> details) {
    this.details = details;
  }
}
