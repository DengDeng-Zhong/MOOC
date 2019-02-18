package gq.dengbo.wendang.docx;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.text.Document;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ImgRead {

	public static void main(String[] args) throws Exception, Exception {
		imageRead();
	}

	private static void imageRead() throws Exception, IOException {
		File docFile = new File("simple.docx");
		
		XWPFDocument doc = new XWPFDocument(OPCPackage.openOrCreate(docFile));
		int i = 0;
		for (XWPFParagraph p : doc.getParagraphs()) {
			for (XWPFRun run : p.getRuns()) {
				System.out.println("a new run");
				for (XWPFPicture pic : run.getEmbeddedPictures()) {
					System.out.println(pic.getCTPicture().xmlText());
					//image
					System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCx());
					System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCy());
					//显示大小
					System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCx()/360000.0);
					System.out.println(pic.getCTPicture().getSpPr().getXfrm().getExt().getCy()/360000.0);
					
					int type = pic.getPictureData().getPictureType();
					byte [] img = pic.getPictureData().getData();
					
//					BufferedImage bufferedImage = ImageRead.byteArrayToImage(img);
//					System.out.println(bufferedImage.getWidth());
//					System.out.println(bufferedImage.getHeight());
					
					String extension = "";

					
				}
				
				
			}
		}
	}

}
