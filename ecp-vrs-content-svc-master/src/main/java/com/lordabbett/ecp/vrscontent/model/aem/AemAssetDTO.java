package com.lordabbett.ecp.vrscontent.model.aem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AemAssetDTO {

  @NotNull
  @JsonProperty("assetMetadata")
  private AemAssetMetadataDTO assetMetadata;
  @JsonProperty("content")
  private byte[] content;

  public AemAssetMetadataDTO getAssetMetadata() {
    return assetMetadata;
  }

  public void setAssetMetadata(AemAssetMetadataDTO assetMetadata) {
    this.assetMetadata = assetMetadata;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }
}
