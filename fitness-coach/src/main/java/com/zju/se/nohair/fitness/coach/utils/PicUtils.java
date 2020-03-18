package com.zju.se.nohair.fitness.coach.utils;

import com.zju.se.nohair.fitness.commons.constant.FileUrlConstant;
import com.zju.se.nohair.fitness.commons.utils.FileUtils;
import com.zju.se.nohair.fitness.dao.mapper.PictureMapper;
import com.zju.se.nohair.fitness.dao.po.PicturePo;
import java.util.Date;
import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片存储工具类
 * @author Miss.Hu
 * @version 1.0.0
 * @projectName fitness
 * @date 2020-03-18
 */
public class PicUtils {
  public static int saveSinglePic(PictureMapper pictureMapper, MultipartFile file)
      throws Exception {
    //保存图片
    String extension = FileUtils.getExtension(Objects.requireNonNull(file.getOriginalFilename()));
    if ("".equals(extension)) {
      throw new Exception("图片后缀名为空");
    }
    long times = System.currentTimeMillis();
    if (FileUtils.savePic(file.getInputStream(), times + extension)) {
      PicturePo picturePo = new PicturePo();
      picturePo.setCreatedTime(new Date());
      picturePo.setFilePath(FileUrlConstant.PIC_PATH_DIR + times + extension);
      picturePo.setPicLink(FileUrlConstant.PIC_LINK_DIR + times + extension);
      pictureMapper.insertReturnId(picturePo);
      return picturePo.getId();
    } else {
      throw new Exception("图片存储失败");
    }
  }
}
