package com.lordabbett.ecp.vrscontent.model.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Detail implements Serializable {

  @JsonProperty("field")
  private String field;
  @JsonProperty("value")
  private String value;
  @JsonProperty("issue")
  private String issue;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getIssue() {
    return issue;
  }

  public void setIssue(String issue) {
    this.issue = issue;
  }
}
