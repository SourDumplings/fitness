package com.zju.se.nohair.fitness.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 14:11
 */
@SuppressWarnings("checkstyle:SummaryJavadoc")
public class BaseEntity implements Serializable {

  private Long id;
  private Date created;
  private Date updated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }
}
