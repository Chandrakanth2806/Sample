package com.lordabbett.ecp.vrscontent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetTransferResponse {

  @JsonProperty("request")
  private AssetTransferRequest request;

  @JsonProperty("mappingIds")
  private List<Integer> mappingIds;

  @JsonProperty("message")
  private String message;

  public AssetTransferRequest getRequest() {
    return request;
  }

  public void setRequest(AssetTransferRequest request) {
    this.request = request;
  }

  public List<Integer> getMappingIds() {
    return mappingIds;
  }

  public void setMappingIds(List<Integer> mappingIds) {
    this.mappingIds = mappingIds;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
