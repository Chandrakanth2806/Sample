package com.lordabbett.ecp.vrscontent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lordabbett.ecp.commons.model.AemAssetType;
import com.lordabbett.ecp.commons.model.AemEventType;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetTransferRequest {

  @NotNull
  @JsonProperty("assetType")
  private AemAssetType assetType;

  @NotNull
  @JsonProperty("eventType")
  private AemEventType eventType;

  @NotNull
  @JsonProperty("assetPath")
  private String assetPath;

  @JsonProperty("assetRenditionType")
  private String rendition;

  @JsonProperty("jcrUuid")
  private String jcrUuid;

  public AemAssetType getAssetType() {
    return assetType;
  }

  public void setAssetType(AemAssetType assetType) {
    this.assetType = assetType;
  }

  public AemEventType getEventType() {
    return eventType;
  }

  public void setEventType(AemEventType eventType) {
    this.eventType = eventType;
  }

  public String getAssetPath() {
    return assetPath;
  }

  public void setAssetPath(String assetPath) {
    this.assetPath = assetPath;
  }

  public String getRendition() {
    return rendition;
  }

  public void setRendition(String rendition) {
    this.rendition = rendition;
  }

  public String getJcrUuid() {
    return jcrUuid;
  }

  public void setJcrUuid(String jcrUuid) {
    this.jcrUuid = jcrUuid;
  }

  @Override
  public String toString() {
    return "AssetTransferRequest{" +
        "assetType=" + assetType +
        ", eventType=" + eventType +
        ", assetPath='" + assetPath + '\'' +
        ", rendition='" + rendition + '\'' +
        ", jcrUuid='" + jcrUuid + '\'' +
        '}';
  }
}
