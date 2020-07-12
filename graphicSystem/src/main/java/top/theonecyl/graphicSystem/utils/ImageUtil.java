package top.theonecyl.graphicSystem.utils;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    private static final Random r = new Random();
    private static final String basePath = "E:\\IdeaProjects\\graphic\\graphicSystem\\src\\main\\resources";
//    private static final String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    /*
     * 将CommonsFile转化为File
     * */
    public static File transferCommonsMultipartFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 处理图片，并返回新生成图片的相对值路径
     */
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail.getImageName());
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr =targetAddr + realFileName + extension;
//        System.out.println("ImageUtil--->"+realFileName + extension);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
//        System.out.println("ImageUtil--->"+relativeAddr);
        // 调用Thumbnails生成带有水印的图片
//        System.out.println(basePath+ "/watermark.png");
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(1024, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩图片失败：" + e.toString());
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }

    /*
     * 创建目标路径所涉及到的目录，
     * 即 /home/work/xiangze/xxx.jpg
     * 那么这三个文件夹都得自动创建
     * */
    private static void makeDirPath(String targetAddr) {
        String realFileParenPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParenPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /*
     * 获取输入文件的扩展名
     * */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /*
    生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     */
    public static String getRandomFileName() {
//        获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("E:/test/article1.jpg"))
                .size(1000, 1000).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/watermark.png")), 0.25f).outputQuality(0.8f)
                .toFile("E:/test/newtest.jpg");
    }

    /*
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件
     * 如果storePath是目录路径则删除该目录下的所有文件
     * */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

}
