package gq.dengbo;

import org.apache.commons.math3.util.ArithmeticUtils;

public class GcdTest {

    public static void main(String[] args) {
        int a = ArithmeticUtils.gcd(361, 285);
        System.out.println("361与285最大公约数为："+a);
    }

}
