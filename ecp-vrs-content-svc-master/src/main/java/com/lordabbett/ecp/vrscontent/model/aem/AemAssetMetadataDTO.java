package com.lordabbett.ecp.vrscontent.model.aem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lordabbett.ecp.vrscontent.model.AssetRenditionType;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "title", "name", "parentPath", "creationDate", "lastModifiedBy",
    "lastModified", "format", "tags", "content"})
public class AemAssetMetadataDTO {

  @JsonProperty("id")
  private String id;
  @JsonProperty("title")
  private String title;
  @JsonProperty("name")
  private String name;
  @JsonProperty("parentPath")
  private String parentPath;
  @JsonProperty("creationDate")
  private LocalDateTime creationDate;
  @JsonProperty("lastModifiedBy")
  private String lastModifiedBy;
  @JsonProperty("lastModified")
  private LocalDateTime lastModified;
  @JsonProperty("format")
  private String format;
  @JsonProperty("tags")
  private List<String> tags;
  @JsonProperty("renditions")
  private List<AssetRenditionType> renditions;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getParentPath() {
    return parentPath;
  }

  public void setParentPath(String parentPath) {
    this.parentPath = parentPath;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public List<AssetRenditionType> getRenditions() {
    return renditions;
  }

  public void setRenditions(
      List<AssetRenditionType> renditions) {
    this.renditions = renditions;
  }

  @Override
  public String toString() {
    return "AemAssetMetadataDTO{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", name='" + name + '\'' +
        ", parentPath='" + parentPath + '\'' +
        ", creationDate=" + creationDate +
        ", lastModifiedBy='" + lastModifiedBy + '\'' +
        ", lastModified=" + lastModified +
        ", format='" + format + '\'' +
        ", tags=" + tags +
        ", renditions=" + renditions +
        '}';
  }
}
