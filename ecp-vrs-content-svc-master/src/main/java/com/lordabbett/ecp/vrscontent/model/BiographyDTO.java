package com.lordabbett.ecp.vrscontent.model;

public class BiographyDTO {

  private Integer personId;
  private String displayName;
  private String designation;
  private String title;
  private String firstName;
  private String middleName;
  private String lastName;
  private String headshotPath;
  private String headshotlongPath;

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getHeadshotPath() {
    return headshotPath;
  }

  public void setHeadshotPath(String headshotPath) {
    this.headshotPath = headshotPath;
  }

  public String getHeadshotlongPath() {
    return headshotlongPath;
  }

  public void setHeadshotlongPath(String headshotlongPath) {
    this.headshotlongPath = headshotlongPath;
  }
}
