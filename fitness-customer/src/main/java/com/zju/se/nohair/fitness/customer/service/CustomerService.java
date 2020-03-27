package com.zju.se.nohair.fitness.customer.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;
import com.zju.se.nohair.fitness.customer.dto.CommentCourseDto;
import com.zju.se.nohair.fitness.customer.dto.LoginDto;
import com.zju.se.nohair.fitness.customer.dto.PurchaseVipCardDto;
import com.zju.se.nohair.fitness.customer.dto.RechargeDto;
import com.zju.se.nohair.fitness.customer.dto.RegisterDto;
import com.zju.se.nohair.fitness.dao.po.NotifiesPoKey;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/28 18:12
 */
public interface CustomerService {

  BaseResult getCustomerBalance(Integer customerId);

  BaseResult getCustomerVipCardInfo(Integer customerId);

  BaseResult createCommentForPublicCourse(CommentCourseDto commentCourseDto);

  BaseResult createCommentForPrivateCourse(CommentCourseDto commentCourseDto);

  BaseResult getCustomerInfo(Integer customerId);

  BaseResult rechargeAmount(RechargeDto rechargeDto);

  BaseResult purchaseVipCard(PurchaseVipCardDto purchaseVipCardDto);

  BaseResult getNotifies(Integer customerId);

  BaseResult checkedNotifies(NotifiesPoKey notifies);

  BaseResult login(LoginDto loginDto);

  BaseResult register(RegisterDto registerDto);

  BaseResult getPicInfo(Integer picId);

  BaseResult getPicGroupInfo(Integer picGroupId);

  BaseResult uploadPic(MultipartFile file, Integer customerId);
}
