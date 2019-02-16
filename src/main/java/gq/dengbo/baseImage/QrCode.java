package gq.dengbo.baseImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.text.Format;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCode {
    private static int WIDTH = 300;
    private static int HEIGHT = 300;
    private static String FORMAT = "png";
    public static void main(String[] args) {
        
        generateQRCode(new File("2dcode.png"),"http://www.baidu.com");
        readQrCode(new File("2dcode.png"));
    }
    private static void readQrCode(File file) {
        MultiFormatReader reader = new MultiFormatReader();
        try {
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            Result result = reader.decode(binaryBitmap, hints);
            System.out.println("解析结果为："+result.toString());
            System.out.println("二维码格式为："+result.getBarcodeFormat());
            System.out.println("二维码文本内容为："+result.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private static void generateQRCode(File file, String content) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//设置容错等级
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
            Path path = file.toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, path);//写到指定路径下  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
