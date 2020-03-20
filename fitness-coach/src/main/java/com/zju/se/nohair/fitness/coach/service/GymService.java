package com.zju.se.nohair.fitness.coach.service;

import com.zju.se.nohair.fitness.commons.dto.BaseResult;

/**
 * 健身房接口
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-20
 */
public interface GymService {

  BaseResult getGymDetail(Integer gymId);//查看健身房详细信息

  BaseResult listGym();//查看全部健身房列表
}
