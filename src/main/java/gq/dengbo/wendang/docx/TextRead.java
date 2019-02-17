package gq.dengbo.wendang.docx;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TextRead {

	public static void main(String[] args) throws Exception {
		readDocx();
	}

	private static void readDocx() throws Exception {
		InputStream is;
		is = new FileInputStream("text.docx");
		XWPFDocument xwpf = new XWPFDocument(is);
		
		List<IBodyElement> ibs = xwpf.getBodyElements();
		for (IBodyElement ib : ibs) {
			BodyElementType bet = ib.getElementType();
			if (bet == BodyElementType.TABLE) {
				//表格
				System.out.println("table:"+ib.getPart());
			}
			else {
				//段落
				XWPFParagraph para = (XWPFParagraph)ib;
				System.out.println("It is a new  paragraph..."+para.getFirstLineIndent());
				
				List<XWPFRun> res = para.getRuns();
				System.out.println("run");
				if (res.size() <=0) {
					System.out.println("empty line");
				}
				for (XWPFRun re : res) {
					if (null == re.text() || re.text().length() <=0) {
						if (re.getEmbeddedPictures().size() > 0) {
							System.out.println("image***"+re.getEmbeddedPictures());
						}
						else {
							System.out.println("objects:"+re.getCTR().getObjectList());
							if (re.getCTR().xmlText().indexOf("instrTest")> 0) {
								System.out.println("there is an equation filed");
							}
							else {
								
							}
						}
					}
					else {
						System.out.println("==="+re.getCharacterSpacing()+re.text());
					}
				}
			}
		}
		is.close();
	}

}
