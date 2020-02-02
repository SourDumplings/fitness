package com.zju.se.nohair.fitness.web.admin.abstracts;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 后台管理模块抽象接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/19 10:02
 */
public interface AbstractService {

  BaseResult listAll();

  BaseResult getDetailById(Integer id);

  BaseResult deleteItem(Integer id);
}
