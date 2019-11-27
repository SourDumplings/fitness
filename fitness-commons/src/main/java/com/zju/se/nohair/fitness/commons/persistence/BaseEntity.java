package com.zju.se.nohair.fitness.commons.persistence;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 实体类的基类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 14:11
 */
@Data
public class BaseEntity implements Serializable {

  private Long id;
  private Date created;
  private Date updated;
}
