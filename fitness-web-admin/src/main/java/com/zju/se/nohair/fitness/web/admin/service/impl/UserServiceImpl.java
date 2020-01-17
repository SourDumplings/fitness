package com.zju.se.nohair.fitness.web.admin.service.impl;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.commons.utils.FileUtils;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.mapper.UserMapper;
import com.zju.se.nohair.fitness.dao.po.CustomerPo;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import com.zju.se.nohair.fitness.dao.po.UserPo;
import com.zju.se.nohair.fitness.web.admin.dto.CreateUserDto;
import com.zju.se.nohair.fitness.web.admin.dto.PicTestDto;
import com.zju.se.nohair.fitness.web.admin.service.UserService;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户 Service 实现类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/11/21 15:28
 */
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

  private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private PictureMapper pictureMapper;

  private UserMapper userMapper;

  @Autowired
  public void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<UserPo> listAll() {
    return userMapper.listAll();
  }

  @Override
  public void insert(UserPo entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public UserPo getById(Long id) {
    return null;
  }

  @Override
  public void update(UserPo entity) {

  }

  @Override
  public int count() {
    return 0;
  }

  @Override
  public BaseResult getUserByPhone(String phone) {
    UserPo resultUser = userMapper.getUserByPhone(phone);
    BaseResult res = null;

    if (resultUser == null) {
      res = BaseResult.fail("获取用户信息失败");
      return res;
    } else {
      res = BaseResult.success("获取用户信息成功");
      res.setData(resultUser);
    }
    return res;
  }

  @Override
  public BaseResult createUser(CreateUserDto createUserDto) {
    BaseResult res = null;

    try {
      UserPo newUser = new UserPo();
      newUser.setUsername(createUserDto.getUsername());
      newUser.setEmail(createUserDto.getEmail());
      newUser.setPhone(createUserDto.getPhone());

      // 验证通过
      Date date = new Date();
      newUser.setUpdated(date);

      // 密码要加密
      newUser.setPassword(DigestUtils.md5DigestAsHex(createUserDto.getPassword().getBytes()));

      // 新增用户
      newUser.setCreated(date);
      userMapper.insert(newUser);
      res = BaseResult.success("创建用户成功");
    } catch (Exception e) {
      logger.error(e.getMessage());
      res = BaseResult.fail("创建用户失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult insertPic(MultipartFile file, PicTestDto picTestDto) {
    BaseResult res = null;

    try {
     //保存图片
      String extension = FileUtils.getExtension(file.getOriginalFilename());
      if (extension==null || extension.equals("") ){
        throw new Exception();
      }
      long times = System.currentTimeMillis();
      if(FileUtils.savePic(file.getInputStream(),times+extension)){
        PicturePo picturePo = new PicturePo();
        picturePo.setCreatedTime(new Date());
        picturePo.setFilePath("/root/pic/"+times+extension);
        picturePo.setPicLink("/pic/"+times+extension);
        pictureMapper.insertReturnId(picturePo);
        //此处的picturePo中id自动被设置为数据库生成的主键id了
        //下一步可以直接使用 例如 设置用户的头像图片id
       /* CustomerPo customerPo = new CustomerPo();
        customerPo.setId(customerId);
        customerPo.setPicId(picturePo.getId());
        customerMapper.updateByPrimaryKeySelective(customerPo);*/
      }else{
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        res = BaseResult.fail("图片存储失败");
        return res;
      }

      res = BaseResult.success("保存图片成功");
      res.setData(picTestDto);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("保存图片失败");
    }

    return res;
  }

  @Transactional(readOnly = false)
  @Override
  public BaseResult insertPics(MultipartFile[] files, PicTestDto picTestDto) {
    BaseResult res = null;

    try {
      //保存图片
      long times = System.currentTimeMillis();
      //获得可用的picGroupId
      int picGroupId = pictureMapper.getAvailablePicGroupId();
      for(MultipartFile f:files){
        String extension = FileUtils.getExtension(f.getOriginalFilename());
        if (extension==null || extension.equals("") ){
          throw new Exception();
        }
        //文件名可以自己确定命名规则 现在用的毫秒数
        String name = "test"+times+extension;
        times++;
        if(FileUtils.savePic(f.getInputStream(),name)){
          PicturePo picturePo = new PicturePo();
          picturePo.setCreatedTime(new Date());
          picturePo.setFilePath("/root/pic/"+name);
          picturePo.setPicLink("/pic/"+name);
          picturePo.setPicGroupId(picGroupId);
          pictureMapper.insertSelective(picturePo);
        }else{
          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
          res = BaseResult.fail("图片存储失败");
          return res;
        }
      }

      res = BaseResult.success("保存图片成功");
      res.setData(picTestDto);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      logger.error(e.getMessage());
      res = BaseResult.fail("保存图片失败");
    }
    return res;
  }
}
