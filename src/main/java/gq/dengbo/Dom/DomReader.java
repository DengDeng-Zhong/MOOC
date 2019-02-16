package gq.dengbo.Dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DomReader {
    public static void main(String[] args) {
        recursiveTraverse(); //自上而下进行访问
        System.out.println("========华丽的分界线========");
        traverseBySearch();//根据名称进行搜索
    }
    
    public static void recursiveTraverse(){
        try {
            //采用Dom解析xml文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("users.xml");
            
            //获取所有的一级子节点
            
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void traverseBySearch(){
        
    }
}
