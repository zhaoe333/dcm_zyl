package com.cm.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片处理类
 * @author hty
 *
 */
@Slf4j
public class ImageUtil {
    /**
     * 给图片指定位置打马赛克
     * @param filePath 图片位置
     * @param targetPath 打码后的图片保存位置，若为空则保存路径默认为原图片路径
     * @param x 图片要打码区域左上角的横坐标
     * @param y 图片要打码区域左上角的纵坐标
     * @param width 图片要打码区域的宽度
     * @param height 图片要打码区域的高度
     * @param mosaicSize 马赛克尺寸，即每个矩形的长宽
     * @return
     * @throws IOException
     */
    @SuppressWarnings("static-access")
    public static boolean mosaic(String filePath, String targetPath,
                                 int x, int y, int width, int height, int mosaicSize) throws IOException {
        //1. 初始化图像处理各变量
        if (!filePath.endsWith(".png") && !filePath.endsWith(".jpg") &&
                !filePath.endsWith(".gif")) {
            log.error("ImageUtil>>>文件名非法，不是正确的图片文件名");
            return false;
        }
        int index = filePath.lastIndexOf(".");
        String suffix = filePath.substring(index + 1);
        if (targetPath != null && !targetPath.isEmpty() && !targetPath.endsWith(suffix)) {
            log.error("ImageUtil>>>目标文件后缀应与源文件后缀一致");
            return false;
        }
        File file = new File(filePath);
        if (!file.isFile()) {
            log.error("ImageUtil>>>" + filePath + "不是一个文件！");
            return false;
        }
        BufferedImage bi = ImageIO.read(file); // 读取该图片
        BufferedImage spinImage = new BufferedImage(bi.getWidth(),
                bi.getHeight(), bi.TYPE_INT_RGB);
        if (bi.getWidth() < mosaicSize || bi.getHeight() < mosaicSize || mosaicSize <= 0) { // 马赛克格尺寸太大或太小
            log.error("马赛克尺寸设置不正确");
            return false;
        }

        //2. 设置各方向绘制的马赛克块个数
        int xcount = 0; // 方向绘制个数
        int ycount = 0; // y方向绘制个数
        if (width % mosaicSize == 0) {
            xcount = width / mosaicSize;
        } else {
            xcount = width / mosaicSize + 1;
        }
        if (height % mosaicSize == 0) {
            ycount = height / mosaicSize;
        } else {
            ycount = height / mosaicSize + 1;
        }

        //3. 绘制马赛克(绘制矩形并填充颜色)
        Graphics gs = spinImage.getGraphics();
        gs.drawImage(bi, 0, 0, null);
        int xTmp = x;
        int yTmp = y;
        for (int i = 0; i < xcount; i++) {
            for (int j = 0; j < ycount; j++) {
                //马赛克矩形格大小
                int mwidth = mosaicSize;
                int mheight = mosaicSize;
                if(i == xcount - 1){   //横向最后一个比较特殊，可能不够一个size
                    mwidth = width - xTmp;
                }
                if(j == ycount - 1){  //同理
                    mheight = height - yTmp;
                }
                //矩形颜色取中心像素点RGB值
                int centerX = xTmp;
                int centerY = yTmp;
                if (mwidth % 2 == 0) {
                    centerX += mwidth / 2;
                } else {
                    centerX += (mwidth - 1) / 2;
                }
                if (mheight % 2 == 0) {
                    centerY += mheight / 2;
                } else {
                    centerY += (mheight - 1) / 2;
                }
                Color color = new Color(bi.getRGB(centerX, centerY));
                gs.setColor(color);
                gs.fillRect(xTmp, yTmp, mwidth, mheight);
                yTmp = yTmp + mosaicSize;// 计算下一个矩形的y坐标
            }
            yTmp = y;// 还原y坐标
            xTmp = xTmp + mosaicSize;// 计算x坐标
        }
        gs.dispose();
        if (targetPath == null || targetPath.isEmpty())
            targetPath = filePath;
        File sf = new File(targetPath);
        ImageIO.write(spinImage, suffix, sf); // 保存图片
        return true;
    }

    /**
     * 对图片进行旋转
     *
     * @param src   被旋转图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static BufferedImage Rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // 计算旋转后图片的尺寸
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);
        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        // 进行转换
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }

    /**
     * 计算旋转后的图片
     *
     * @param src   被旋转的图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        // 如果旋转的角度大于90度做相应的转换
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }

    @SuppressWarnings("static-access")
    public static boolean black(String filePath, String targetPath,
                                 int x, int y, int width, int height) throws IOException {
        //1. 初始化图像处理各变量
        if (!filePath.endsWith(".png") && !filePath.endsWith(".jpg") &&
                !filePath.endsWith(".gif")) {
            log.error("ImageUtil>>>文件名非法，不是正确的图片文件名");
            return false;
        }
        int index = filePath.lastIndexOf(".");
        String suffix = filePath.substring(index + 1);
        if (targetPath != null && !targetPath.isEmpty() && !targetPath.endsWith(suffix)) {
            log.error("ImageUtil>>>目标文件后缀应与源文件后缀一致");
            return false;
        }
        File file = new File(filePath);
        if (!file.isFile()) {
            log.error("ImageUtil>>>" + filePath + "不是一个文件！");
            return false;
        }
        BufferedImage bi = ImageIO.read(file); // 读取该图片
        BufferedImage subimage = bi.getSubimage(x, y, width, height);
        BufferedImage spinImage = new BufferedImage(bi.getWidth(),
                bi.getHeight(), bi.getType());
        Graphics gs = spinImage.getGraphics();
        gs.drawImage(bi, 0, 0, null);
        gs.setColor(Color.BLACK);
        gs.fillRect(x,y,width,height);
        gs.dispose();
        if (targetPath == null || targetPath.isEmpty())
            targetPath = filePath;
        File sf = new File(targetPath);
        ImageIO.write(subimage, suffix, sf); // 保存图片
        return true;
    }


    public static void main(String[] args) throws IOException {
//        ImageUtil.mosaic("C:\\Users\\yunlu.zhao\\Downloads\\fapiao2.jpg","C:\\Users\\yunlu.zhao\\Downloads\\fapiao4.jpg",
//                457,527,233,24,5);
//        BufferedImage src = ImageIO.read(new File("C:\\Users\\yunlu.zhao\\Downloads\\fapiao.jpg"));
//        BufferedImage des1 = ImageUtil.Rotate(src, 360-89);
//        ImageIO.write(des1, "jpg", new File("C:\\Users\\yunlu.zhao\\Downloads\\fapiao2.jpg"));
        ImageUtil.black("C:\\Users\\yunlu.zhao\\Downloads\\efapiao.jpg","C:\\Users\\yunlu.zhao\\Downloads\\efapiao3.jpg",
                589,465,272,30);
    }
}
