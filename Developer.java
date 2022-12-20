package com.example.springproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Developer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int commit_num;
  private String repoName;

  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getCommit_num() {
    return commit_num;
  }

  public void setCommit_num(int commit_num) {
    this.commit_num = commit_num;
  }

  public String getRepoName() {
    return repoName;
  }

  public void setRepoName(String repoName) {
    this.repoName = repoName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
