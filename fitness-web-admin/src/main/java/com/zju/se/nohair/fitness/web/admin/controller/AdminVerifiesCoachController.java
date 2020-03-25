package com.zju.se.nohair.fitness.web.admin.controller;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.web.admin.dto.AdminCreateVerifiesDto;
import com.zju.se.nohair.fitness.web.admin.service.AdminVerifiesCoachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台审批教练模块Controller
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-22
 */
@RestController
@RequestMapping("admin/verifies/coach")
@Api
public class AdminVerifiesCoachController {

  private static Logger logger = LoggerFactory.getLogger(AdminVerifiesCoachController.class);

  private AdminVerifiesCoachService adminVerifiesCoachService;

  @Autowired
  public void setAdminVerifiesCoachService(AdminVerifiesCoachService adminVerifiesCoachService) {
    this.adminVerifiesCoachService = adminVerifiesCoachService;
  }

  @ApiOperation(value = "教练审批列表；审核状态（0代表未审批，1代表已审批成功，2代表审批未通过）", httpMethod = "GET")
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> listCoachAll() {
    BaseResult baseResult = adminVerifiesCoachService.listCoachAll();
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "查看审批教练详细信息(审批type=0)", httpMethod = "GET")
  @RequestMapping(value = "{coachId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> getCoachDetailByCoachId(@PathVariable("coachId") Integer coachId) {
    BaseResult baseResult = adminVerifiesCoachService.getCoachDetailByCoachId(coachId);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getData(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }

  @ApiOperation(value = "审批教练（type=0）", httpMethod = "POST")
  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> verifiesByApplicantIdAndType(@RequestBody @Valid AdminCreateVerifiesDto adminCreateVerifiesDto) {
    BaseResult baseResult = adminVerifiesCoachService.verifiesByApplicantIdAndType(adminCreateVerifiesDto);
    if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
      return new ResponseEntity<>(baseResult.getMessage(), HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(baseResult.getMessage(),
          HttpStatus.valueOf(baseResult.getStatus()));
    }
  }
}
