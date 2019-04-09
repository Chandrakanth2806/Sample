package com.lordabbett.ecp.vrscontent.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AssetRenditionType {
  ORIGINAL("original", "original"), CQ5DAM_THUMBNAIL_48_48("png",
      "cq5dam.thumbnail.48.48.png"), CQ5DAM_THUMBNAIL_140_100("png",
      "cq5dam.thumbnail.140.100.png"), CQ5DAM_WEB_1280_1280("jpeg",
      "cq5dam.web.1280.1280.jpeg"), CQ5DAM_WEB_100_100("jpeg",
      "cq5dam.web.100.100.jpeg"), CQ5DAM_WEB_150_150("jpeg",
      "cq5dam.web.150.150.jpeg"), CQ5DAM_WEB_216_216("jpeg",
      "cq5dam.web.216.216.jpeg"), CQ5DAM_THUMBNAIL_319_319("png",
      "cq5dam.thumbnail.319.319.png"), CQ5DAM_WEB_90_90("jpeg", "cq5dam.web.90.90.jpeg");
  private String fileType;
  private String renditionFileName;

  AssetRenditionType(String fileType, String renditionFileName) {
    this.fileType = fileType;
    this.renditionFileName = renditionFileName;
  }

  @JsonCreator
  public static AssetRenditionType forValue(String value) {
    for (AssetRenditionType assetRenditionType : AssetRenditionType.values()) {
      if (assetRenditionType.renditionFileName.equalsIgnoreCase(value)) {
        return assetRenditionType;
      }
    }
    return null;
  }

  public static boolean isValidValue(String value) {
    for (AssetRenditionType assetRenditionType : AssetRenditionType.values()) {
      if (assetRenditionType.renditionFileName.equalsIgnoreCase(value)) {
        return true;
      }
    }
    return false;
  }

  @JsonValue
  public String toValue() {
    return this.renditionFileName;
  }

  public String getFileType() {
    return fileType;
  }

  public String getRenditionFileName() {
    return renditionFileName;
  }
}
