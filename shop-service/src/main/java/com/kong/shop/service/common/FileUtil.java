package com.kong.shop.service.common;

import java.io.*;

/**
 * Created by Administrator on 2016/3/3 0003.
 */
public class FileUtil {
    /**
     * 单个文件上传
     *
     * @param is
     * @param fileName
     * @param filePath
     */

    public static void upFile(InputStream is, String fileName, String filePath) {

        FileOutputStream fos = null;

        BufferedOutputStream bos = null;

        BufferedInputStream bis = null;

        File file = new File(filePath);

        if (!file.exists()) {
            file.mkdirs();
        }

        File f = new File(filePath + "/" + fileName);

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];
            int len;
            while ((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }
                if (null != fos) {
                    fos.close();
                    fos = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
