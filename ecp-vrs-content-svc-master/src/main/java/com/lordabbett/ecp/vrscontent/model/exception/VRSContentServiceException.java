package com.lordabbett.ecp.vrscontent.model.exception;

public class VRSContentServiceException extends RuntimeException {

  private final int httpStatusCode;

  public VRSContentServiceException(String message, int httpStatusCode) {
    super(message);
    this.httpStatusCode = httpStatusCode;
  }

  public int getHttpStatusCode() {
    return httpStatusCode;
  }
}
