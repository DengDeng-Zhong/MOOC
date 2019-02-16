package gq.dengbo.internal;

import java.util.Locale;
import java.util.ResourceBundle;

public class NewHelloWorld {

    public static void main(String[] args) {
        Locale mylocale = Locale.getDefault();
        System.out.println("mylocale = " + mylocale);
        
        //根据指定语言——国家环境加载资源文件
        ResourceBundle bundle = ResourceBundle.getBundle("message", mylocale);
        
        //从资源文件中取得的消息
        System.out.println(bundle.getString("hello"));
        
        mylocale = new Locale("en", "US"); //语言 国家强制转换成en_US
        bundle = ResourceBundle.getBundle("message", mylocale);
        System.out.println(bundle.getString("hello"));
        
        
    }

}
