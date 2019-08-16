package com.hx.controller;

import com.hx.util.HxException;
import com.hx.util.RequestTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Random;

/**
 * Created by huangch on 2019/7/23 09:58
 * description:
 *
 * @since JDK 1.6
 */
@Api(tags = {"文件上传管理"})
@RestController
@RequestMapping("upload")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @ApiOperation(value = "图片上传", httpMethod = "POST", notes = "图片上传", response = String.class)
//    @ApiImplicitParams(value = {@ApiImplicitParam(name = "file", required = true, value = "图片地址", dataType = "file", paramType = "query")})
    @PostMapping(value = "image", headers = "content-type=multipart/form-data")
    @ResponseBody
    public String uploadImage(@ApiParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) throws HxException {

        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (StringUtils.isEmpty(fileName)) {
            throw new HxException("上传的图片文件名为空");
        }

        String returnUrl = "upload/img";//存储路径

        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀

        fileName = System.currentTimeMillis() + "_" + new Random().nextInt(10000) + fileSuffix;//新的文件名

        //获取文件夹路径
        File parentDirFile = new File(returnUrl);

        //先判断文件是否存在,如果文件夹不存在则创建
        if (!parentDirFile.exists() && !parentDirFile.isDirectory()) {
            parentDirFile.mkdir();
        }

        String fullFileName = parentDirFile.getAbsolutePath() + "/" + fileName;

        //将图片存入文件夹
        File targetFile = new File(fullFileName);

        try {
            //将上传的文件写到服务器上指定的文件。
            file.transferTo(targetFile);

            return "http://139.129.6.52/img/" + fileName;//返回存储路径
        } catch (Exception e) {
            logger.error("upload image exception", e);
            throw new HxException("系统异常，图片上传失败");
        }
    }
}
