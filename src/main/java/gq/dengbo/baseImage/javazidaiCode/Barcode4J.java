package gq.dengbo.baseImage.javazidaiCode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Barcode4J {

	public static void main(String[] args) {
		String msg = "Tianmen";
		String path = "1JavaCode.png";
		generateFile(msg, path);
	}

	private static void generateFile(String msg, String path) {
		File file = new File(path);
		try {
			Code39Bean bean = new Code39Bean();
//			EAN13Bean bean = new EAN13Bean();
			final int dpi = 150;
			
			final double width = UnitConv.in2mm(2.0f/dpi);
			bean.setWideFactor(3);
			bean.setModuleWidth(width);
			bean.doQuietZone(false);
			
			String format = "image/png";
			//输出到流
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(new FileOutputStream(file), format, dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			//生成条码
			bean.generateBarcode(canvas, msg);
			
			//结束绘制
			canvas.finish();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
