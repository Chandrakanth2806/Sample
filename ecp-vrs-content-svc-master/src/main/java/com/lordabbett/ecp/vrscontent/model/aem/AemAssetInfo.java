package com.lordabbett.ecp.vrscontent.model.aem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AemAssetInfo {
  @JsonProperty(value = "name")
  private String name;
  @JsonProperty(value = "title")
  private String title;
  @JsonProperty(value = "lastModified")
  private String lastModified;
  @JsonProperty(value = "path")
  private String path;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLastModified() {
    return lastModified;
  }

  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
