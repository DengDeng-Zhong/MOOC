package gq.dengbo.wendang.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * 该类有pdf的读写，删除以及合并操作
 * 新增转换成word功能
 * @author DengBo
 *
 */
public class PdfOpeartor {
	public static void main(String[] args) throws Exception {
//		createHelloPDF("test1.pdf");
		
//		readHelloPDF();
		
//		mergerPDF();
		
//		removePDF();
		
		XWPFDocument doc = new XWPFDocument();
//		PdfOptions 
		
	}

	private static void removePDF() throws InvalidPasswordException, IOException {
		File file = new File("merge.pdf");
		PDDocument document = PDDocument.load(file);
		
		int noOfPages = document.getNumberOfPages();
		System.out.println("total pages: "+ noOfPages);
		
		//删除第一页
		document.removePage(1);
		System.out.println("page remove");
		
		document.save("merger.pdf");
		document.close();
	}

	/**
	 * 合并pdf
	 * @throws FileNotFoundException
	 */
	private static void mergerPDF() throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream(new File("merge.pdf"));
		
		ByteArrayOutputStream mergePDFOutputStream = null;
		File file1 = new File("test1.pdf");
		File file2 = new File("test2.pdf");
		
		List<InputStream> sources = new ArrayList<InputStream>();
		try {
			sources.add(new FileInputStream(file1));
			sources.add(new FileInputStream(file2));
			mergePDFOutputStream = new ByteArrayOutputStream();
			
			//设定来源和目标
			PDFMergerUtility pdfMerger = new PDFMergerUtility();
			pdfMerger.addSources(sources);
			pdfMerger.setDestinationStream(mergePDFOutputStream);
			
			//设定合并选项
			PDDocumentInformation pdfdocinfo = new PDDocumentInformation();
			pdfMerger.setDestinationDocumentInformation(pdfdocinfo);
			
			//合并
			pdfMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
			
			fos.write(mergePDFOutputStream.toByteArray());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void readHelloPDF() {
		File pdfFile = new File("test.pdf");
		PDDocument document = null;
		try {
			document = PDDocument.load(pdfFile);
			AccessPermission ap = document.getCurrentAccessPermission();
			if (!ap.canExtractContent()) {
				throw new IOException("没有抽取文本权限");
			}
			//获取页码
			int pages = document.getNumberOfPages();
			//读文本内容
			PDFTextStripper stripper = new PDFTextStripper();
			//设置按顺序输出
			stripper.setSortByPosition(true);
			stripper.setStartPage(1);//设置起始页
			stripper.setEndPage(pages);
			String content = stripper.getText(document);
			System.out.println(content);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createHelloPDF(String path) {
		PDDocument document = null;
		PDPage page = null;
		try {
			document = new PDDocument();
			page = new PDPage();
			document.addPage(page);
			PDFont font = PDType1Font.HELVETICA_BOLD;
			PDPageContentStream content = new PDPageContentStream(document, page);
			content.beginText();
			content.setFont(font, 12);
			content.moveTextPositionByAmount(100, 700);
			content.showText("helloworld");
			
			
			content.endText();
			content.close();
			document.save(path);
			document.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
