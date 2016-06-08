package com.kong.shop.domain.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by kong on 2016/3/3.
 */
public class UploadFilePath {
    private String realPath;
    private String relativePath;
    private int imgHeight;
    private int imgWidth;
    private String fileName;

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("realPath", getRealPath())
                .append("retRelativePath", getRelativePath())
                .append("imgWidth", getImgWidth())
                .append("imgHeight", getImgHeight())
                .append("fileName", getFileName())
                .toString();
    }
}
