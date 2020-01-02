package com.zju.se.nohair.fitness.customer.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.customer.service.CoachInCustomerService;
import com.zju.se.nohair.fitness.customer.service.CustomerService;
import com.zju.se.nohair.fitness.customer.service.GymInCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户端需要的信息 包括健身房、教练等信息
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/23 16:05
 */

@RestController
@RequestMapping("customer/info")
@Api
public class InfoForCustomerController {

  private static Logger logger = LoggerFactory.getLogger(CourseController.class);

  @Autowired
  private CoachInCustomerService coachService;
  @Autowired
  private GymInCustomerService gymService;
  @Autowired
  private CustomerService customerService;

  @ApiOperation(value = "接收图片和参数的demo", httpMethod = "POST")
  @RequestMapping(value = "pic", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> getPic(@RequestParam("file") MultipartFile file, CommentCourseDto commentCourseDto) {

    try {
      savePic(file.getInputStream(),"1.jpg");
    } catch (IOException e) {
      e.printStackTrace();
    }

    BaseResult baseResult = BaseResult.success("success");
    baseResult.setData(commentCourseDto);
    return new ResponseEntity<>(baseResult.getData(),HttpStatus.OK);
  }

  private void savePic(InputStream inputStream, String fileName) {
    OutputStream os = null;
    try {
      String path = "/root/pic/customer/";
      // 2、保存到临时文件
      // 1K的数据缓冲
      byte[] bs = new byte[1024];
      // 读取到的数据长度
      int len;
      // 输出的文件流保存到本地文件
      File tempFile = new File(path);
      if (!tempFile.exists()) {
        tempFile.mkdirs();
      }
      os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
      // 开始读取
      while ((len = inputStream.read(bs)) != -1) {
        os.write(bs, 0, len);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 完毕，关闭所有链接
      try {
        os.close();
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @ApiOperation(value = "查询教练详细信息", httpMethod = "GET")
  @RequestMapping(value = "details/coach/{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getCoachDetail(@PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = coachService.getCoachDetail(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }


  @ApiOperation(value = "查询健身房详细信息", httpMethod = "GET")
  @RequestMapping(value = "details/gym/{gymId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getGymDetail(@PathVariable("gymId") Integer gymId) {
    BaseResult baseResult = gymService.getGymDetail(gymId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询附近健身房信息", httpMethod = "GET")
  @RequestMapping(value = "list/gym", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getGymList() {
    BaseResult baseResult = gymService.getNearGymList();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询客户钱包余额", httpMethod = "GET")
  @RequestMapping(value = "customer/balance/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getCustomerBalance(@PathVariable("customerId") Integer customerId) {
    BaseResult baseResult = customerService.getCustomerBalance(customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查询客户会员卡信息", httpMethod = "GET")
  @RequestMapping(value = "customer/vip_card/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getCustomerVipCardInfo(@PathVariable("customerId") Integer customerId) {
    BaseResult baseResult = customerService.getCustomerVipCardInfo(customerId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

}
