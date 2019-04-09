package com.lordabbett.ecp.vrscontent.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lordabbett.ecp.commons.model.AemAssetType;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AemAssetRequest {

  @NotNull
  @JsonProperty("assetType")
  private AemAssetType assetType;

  @NotNull
  @JsonProperty("assetPath")
  private String assetPath;

  @NotNull
  @JsonProperty("assetRenditionType")
  private String rendition;

  public AemAssetType getAssetType() {
    return assetType;
  }

  @JsonCreator
  public void setAssetType(AemAssetType assetType) {
    this.assetType = assetType;
  }

  public String getAssetPath() {
    return assetPath;
  }

  @JsonCreator
  public void setAssetPath(String assetPath) {
    this.assetPath = assetPath;
  }

  public String getRendition() {
    return rendition;
  }

  @JsonCreator
  public void setRendition(String rendition) {
    this.rendition = rendition;
  }


  @Override
  public String toString() {
    return "AemAssetRequest{" +
        "assetType=" + assetType +
        ", assetPath='" + assetPath + '\'' +
        ", rendition=" + rendition +
        '}';
  }
}
