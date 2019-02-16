package gq.dengbo.baseImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Barcode {

    public static void main(String[] args) {
        generateCode(new File("1dcode.png"), "zhongdengbo", 500, 250);
//        readCode(new File("1dcode.png"));
    }

    private static void readCode(File file) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            if (image == null) {
                return;
            }
            //LuminanceSource source = new BufferedImageLuminanceSource(image);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
    }

    /**
     * 根据code生成相应的一维码
     * @param file  一维码目标文件
     * @param code  一维码内容
     * @param width 图片宽度
     * @param height   图片高度
     */
    public static void generateCode(File file, String code, int width, int height) {
        //定义矩形图
        BitMatrix matrix= null;
        try {
            //使用code_128格式进行编码生成100*25的条形码
            MultiFormatWriter writer = new MultiFormatWriter();
            
            matrix = writer.encode(code, BarcodeFormat.CODE_128, width, height);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将位图矩阵保存为图片
        try(FileOutputStream output = new FileOutputStream(file)) {
            ImageIO.write(MatrixToImageWriter.toBufferedImage(matrix), "png", output);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
