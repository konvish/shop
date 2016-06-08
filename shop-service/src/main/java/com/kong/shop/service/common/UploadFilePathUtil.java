package com.kong.shop.service.common;

import com.kong.shop.domain.common.UploadFilePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by kong on 2016/3/3.
 */
public class UploadFilePathUtil {
    /**
     * 获取图片上传路径
     *
     * @return
     */
    public static UploadFilePath initFileUpload(Integer storeId, String path, String suffix, int width, int height) {
        StringBuffer randomKey = new StringBuffer();
        Random random = new Random();
        char[] ranFile = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < 6; i++) {
            String code = String.valueOf(ranFile[random.nextInt(62)]);
            randomKey.append(code);
        }
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        String timeStr = new SimpleDateFormat("HHmmssSSS").format(date);
        int hashcode = Math.abs(storeId.hashCode() % 256);

        String relativePath = "/images" + "/" + hashcode + "/" + storeId + "/" + dateStr + "/";
        String realPath = path + "/" + hashcode + "/" + storeId + "/" + dateStr + "/";

        File logoSaveFile = new File(realPath);
        if (!logoSaveFile.exists()) {
            logoSaveFile.mkdirs();
        }
        // 图片文件名: 时间戳 + 随机串 + 高宽
        String fileName = timeStr + randomKey + '_' + height + '_' + width+"."+ suffix;
        UploadFilePath uploadFile = new UploadFilePath();
        uploadFile.setRelativePath(relativePath + fileName);
        uploadFile.setRealPath(realPath + fileName);
        uploadFile.setFileName(fileName);
        return uploadFile;
    }

    /**
     * 改变图片的尺寸
     *
     * @param source  源文件
     * @param targetW 目标长
     * @param targetH 目标宽
     */
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) throws IOException {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
//        if (sx > sy)
//        {
//            sx = sy;
//            targetW = (int) (sx * source.getWidth());
//        }
//        else
//        {
//            sy = sx;
//            targetH = (int) (sy * source.getHeight());
//        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
                    targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            //固定宽高，宽高一定要比原图片大
            target = new BufferedImage(targetW, targetH, type);
            //target = new BufferedImage(800, 600, type);
        }

        Graphics2D g = target.createGraphics();

        //写入背景
        //g.drawImage(ImageIO.read(new File("ok/blank.png")), 0, 0, null);

        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }
}
