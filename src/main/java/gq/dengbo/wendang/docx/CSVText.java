package gq.dengbo.wendang.docx;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVText {
	public static void main(String[] args) throws Exception {
		readCSVWithIndex();
		System.out.println("======华丽丽的分割线1======");
		readCSVWithName();
		System.out.println("======华丽丽的分割线2======");
//		writeCSV();
		System.out.println("write done");
	}

	private static void writeCSV() {
		 try {
			CSVPrinter printer = new CSVPrinter(new FileWriter("score.csv"), CSVFormat.EXCEL);
			printer.printRecord("Name", "Subject", "Score");
			printer.printRecord("zhangsan", "shucue", "18");
			printer.println();
			printer.printRecord("zhangsan", "shucue", "18");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void readCSVWithName() throws Exception{
		Reader in = new FileReader("score.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Name","Subject","Score").parse(in);
		for (CSVRecord csvRecord : records) {
			System.out.println(csvRecord.get("Name"));
		}
		
		
	}

	private static void readCSVWithIndex() throws Exception{
		Reader in = new FileReader("score.csv");
		Iterable<CSVRecord> recods = CSVFormat.EXCEL.parse(in);
		for (CSVRecord record : recods) {
			System.out.println(record.get(0)); //0代表第一列
		}
	}
}
