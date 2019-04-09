package com.lordabbett.ecp.vrscontent.model.entity;


import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ip_web_aem" )
public class Biography {
  @Id
  @Column(name = "person_id")
  private Integer personId;
  @Column(name = "display_name")
  private String displayName;
  @Column(name = "designation")
  private String designation;
  @Column(name = "title")
  private String title;
  @Column(name = "active_ind")
  private boolean activeInd;
  @Column(name = "created_dt")
  private LocalDateTime createdDate;
  @Column(name = "created_by")
  private String createdBy;
  @Column(name = "updated_dt")
  private LocalDateTime lastUpdated;
  @Column(name = "updated_by")
  private String lastUpdatedUser;
  @Column(name = "image_path")
  private String imagePath;

  public Integer getPersonId() {
    return personId;
  }

  public void setPersonId(Integer personId) {
    this.personId = personId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isActiveInd() {
    return activeInd;
  }

  public void setActiveInd(boolean activeInd) {
    this.activeInd = activeInd;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getLastUpdatedUser() {
    return lastUpdatedUser;
  }

  public void setLastUpdatedUser(String lastUpdatedUser) {
    this.lastUpdatedUser = lastUpdatedUser;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public String toString() {
    return "Biography{" +
        "personId=" + personId +
        ", displayName='" + displayName + '\'' +
        ", designation='" + designation + '\'' +
        ", title='" + title + '\'' +
        ", activeInd=" + activeInd +
        ", createdDate=" + createdDate +
        ", createdBy='" + createdBy + '\'' +
        ", lastUpdated=" + lastUpdated +
        ", lastUpdatedUser='" + lastUpdatedUser + '\'' +
        ", imagePath='" + imagePath + '\'' +
        '}';
  }
}
