package com.example.springproject.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Release {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String repoName;
  private Date publish_time;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRepoName() {
    return repoName;
  }

  public void setRepoName(String repoName) {
    this.repoName = repoName;
  }

  public Date getPublish_time() {
    return publish_time;
  }

  public void setPublish_time(Date publish_time) {
    this.publish_time = publish_time;
  }
}
