package com.zju.se.nohair.fitness.web.admin.dao.mapper;

import com.zju.se.nohair.fitness.web.admin.dao.po.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户的 Mapper 接口.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:10
 */
@Mapper
@Component(value = "UserMapper")
public interface UserMapper {

  /**
   * 查询全部记录.
   *
   * @return 全部记录的列表
   */
  List<User> listAll();

  /**
   * 新增一条记录.
   *
   * @param entity 新增记录实体
   */
  void insert(User entity);

  /**
   * 删除一条记录.
   *
   * @param id 删除记录的 id
   */
  void delete(Long id);

  /**
   * 根据 ID 查询信息.
   *
   * @param id 查询对象的 id
   * @return 获取到的实体对象
   */
  User getById(Long id);

  /**
   * 更新实体信息.
   *
   * @param entity 需要更新的实体对象.
   */
  void update(User entity);

  /**
   * 查询总记录条数.
   *
   * @return 总记录条数.
   */
  int count();
}
