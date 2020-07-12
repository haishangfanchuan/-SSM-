package top.theonecyl.graphicSystem.utils;

import java.io.InputStream;

/**
 * 设置封装图片的名称和上传路径
 */
public class ImageHolder {
    private String imageName;
    private InputStream inputStream;

    public ImageHolder(String imageName, InputStream inputStream) {
        this.imageName = imageName;
        this.inputStream = inputStream;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public String toString() {
        return "ImageHolder{" +
                "imageName='" + imageName + '\'' +
                ", inputStream=" + inputStream +
                '}';
    }
}
