package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateBusinessUserDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminBusinessUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台模块的用户子模块下的商家用户 Controller.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/16 9:47
 */
@RestController
@RequestMapping("admin/user/business")
@Api
public class AdminBusinessUserController
{

  private static Logger logger = LoggerFactory.getLogger(AdminBusinessUserController.class);

  private AdminBusinessUserService adminBusinessUserService;

  @Autowired
  public void setAdminBusinessUserService(AdminBusinessUserService adminBusinessUserService)
  {
    this.adminBusinessUserService = adminBusinessUserService;
  }

  @ApiOperation(value = "商家用户列表；审核状态（0代表未审批，1代表已审批成功，2代表审批未通过）", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listAll()
  {
    BaseResult baseResult = adminBusinessUserService.listAll();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
    {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    }
    else
    {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "商家用户详情；性别统计第一个数字为男顾客数，第二个为女顾客数", httpMethod = "GET")
  @RequestMapping(value = "{businessId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getDetailById(
      @PathVariable(value = "businessId") Integer businessId)
  {
    BaseResult baseResult = adminBusinessUserService.getDetailById(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
    {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    }
    else
    {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "注册/更新商家用户；id 为 -1 即为注册，否则为更新", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> saveItem(
      AdminCreateBusinessUserDto adminCreateBusinessUserDto,
      @RequestParam("profilePic") MultipartFile profilePic,
      @RequestParam("certificationPic") MultipartFile certificationPic)
  {
    BaseResult baseResult = adminBusinessUserService.saveItem(adminCreateBusinessUserDto,
        profilePic, certificationPic);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
    {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    }
    else
    {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "删除商家用户", httpMethod = "DELETE")
  @RequestMapping(value = "{businessId}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deleteItem(
      @PathVariable(value = "businessId") Integer businessId)
  {
    BaseResult baseResult = adminBusinessUserService.deleteItem(businessId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
    {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.NO_CONTENT);
    }
    else
    {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "批量删除商家用户；参数输入格式示例：[id1,id2,id3]", httpMethod = "DELETE")
  @RequestMapping(value = "", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Object> deleteMulti(
      @RequestBody List<Integer> businessIds)
  {
    BaseResult baseResult = adminBusinessUserService.deleteMulti(businessIds);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
    {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.NO_CONTENT);
    }
    else
    {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
