package gq.dengbo;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

public class CharsetT {
    /**
     * 打印本机的字符集
     * 
     */
    public static void charsetPrint() {
        Charset c = Charset.defaultCharset();
        System.out.println(c.name());
    }
    /**
     * 输出所有的支持字符集
     */
    public static void allcharset(){
        SortedMap<String, Charset> sm = Charset.availableCharsets();
        Set<String> keyset = sm.keySet();
        int a = 0;
        System.out.println("Java支持的所有字符集");
        for (String c : keyset) {
            a++;
            System.out.println(c);
        }
        System.out.println("个数为："+a);
    }
}
