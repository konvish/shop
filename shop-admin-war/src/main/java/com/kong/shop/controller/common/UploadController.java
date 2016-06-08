package com.kong.shop.controller.common;

import com.kong.shop.controller.BaseController;
import com.kong.shop.domain.common.UploadFilePath;
import com.kong.shop.service.common.UploadFilePathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kong on 2016/3/3.
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

    UploadFilePath uploadFilePath;

    @Override
    public String getTemplateRoot() {
        return "/images";
    }

    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void uploadHandlerForUploadify(@RequestParam("storeId") String storeId, @RequestParam(value = "file", required = false) MultipartFile pic, @RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = request.getSession().getServletContext().getRealPath("/images");
        //写文件前先读取图片原始宽高
        byte[] bytes = pic.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        int width = 0; //原始图片宽
        int height = 0;//原始图片高
        BufferedImage targetImg = null;
        try {
            BufferedImage bufimg = ImageIO.read(is);
            if (bufimg != null) {
                width = bufimg.getWidth();
                height = bufimg.getHeight();
                if (type.equals("1") || type.equals("3") || type == "1" || type == "3")
                    targetImg = UploadFilePathUtil.resize(bufimg, 230, 230); //缩略图
                else if (type.equals("2") || type.equals("4") || type == "2" || type == "4")
                    targetImg = UploadFilePathUtil.resize(bufimg, 750, height); //详情图
                else
                    targetImg = UploadFilePathUtil.resize(bufimg, 750, 398); //广告图
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            is.close();
            throw new Exception("uploadify上传图片读取图片高宽时发生异常!");
        }

        /** 拼成完整的文件保存路径加文件 **/
        String originalFilename = pic.getOriginalFilename(); // 文件全名
        //String suffix = StringUtil.substringAfter(originalFilename, "."); // 后缀
        int o = originalFilename.lastIndexOf(".");

        String suffix = originalFilename.substring(o + 1);
        // 文件名转码
        // fileName = Base64.StringToBase64(fileName);
        // fileName = StringUtil.join(fileName, suffix);
        if (storeId.equals("") || storeId.equals("undefined"))
            storeId="-1";
        UploadFilePath uploadFile = UploadFilePathUtil.initFileUpload(Integer.valueOf(storeId), filePath, suffix, width, height);
        //File file = new File(uploadFile.getRealPath());
        //pic.transferTo(file);
        ImageIO.write(targetImg, suffix, new FileOutputStream(uploadFile.getRealPath()));
        uploadFilePath = new UploadFilePath();
        uploadFilePath = uploadFile;
    }

    @ResponseBody
    @RequestMapping(value = "/getUploadMsg.do")
    public Map getUploadMsg() {
        Map map = new HashMap();
        map.put("uploadFile", uploadFilePath);
        return map;
    }

}
