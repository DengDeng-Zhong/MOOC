package gq.dengbo;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class ChineseTest {

    public static void main(String[] args) {
        String original = "生命在于运动";
        String result = ZhConverterUtil.convertToTraditional(original);
        System.out.println(result);

    }

}
