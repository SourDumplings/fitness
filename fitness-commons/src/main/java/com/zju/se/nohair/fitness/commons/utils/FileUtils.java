package com.zju.se.nohair.fitness.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;

/**
 * 文件工具类
 *
 * @author Wang Haowen
 * @version 1.0.0
 * @projectName fitness
 * @date 2020/1/2 23:46
 */
public class FileUtils {

  public static String getExtension(String s){
    int begin = s.lastIndexOf(".");
    if (begin == -1){
      return "";
    }
    int last = s.length();
    String a = s.substring(begin, last);
    return a;
  }


  /*
  * 判断文件是否为图片类型
  *
  * */
  public static boolean isImage(File file){
    MimetypesFileTypeMap mtftp = new MimetypesFileTypeMap();
    mtftp.addMimeTypes("image png tif jpg jpeg bmp");
    String mimetype= mtftp.getContentType(file);
    String type = mimetype.split("/")[0];
    return type.equals("image");
  }

  /*
  * 返回true则确认保存了文件
  *
  * */
  public static boolean savePic(InputStream inputStream, String fileName) {
    boolean res = true;
    OutputStream os = null;
    try {
      String path = "/root/pic/";
      String osn = System.getProperty("os.name");
      if(osn.toLowerCase().startsWith("win")){
        path = "D:\\data\\pic";
      }

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
      res = false;
      e.printStackTrace();
    } catch (Exception e) {
      res = false;
      e.printStackTrace();
    } finally {
      // 完毕，关闭所有链接
      try {
        os.close();
        inputStream.close();
      } catch (IOException e) {
        res = false;
        e.printStackTrace();
      }
    }
    return res;
  }

}
