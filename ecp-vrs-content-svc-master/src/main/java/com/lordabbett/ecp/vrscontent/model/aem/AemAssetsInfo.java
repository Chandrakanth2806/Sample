package com.lordabbett.ecp.vrscontent.model.aem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AemAssetsInfo {

  @JsonProperty(value = "hits")
  private List<AemAssetInfo> hits;

  @JsonProperty(value = "hits")
  public List<AemAssetInfo> getHits() {
    return hits;
  }

  @JsonProperty(value = "hits")
  public void setHits(List<AemAssetInfo> hits) {
    this.hits = hits;
  }
}
