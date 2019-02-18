package gq.dengbo.demo;

public class Triangle {
    public boolean judgeEdges(int a, int b, int c) {
        boolean result = true;

        // 变长非负性
        if (a <= 0 || b <= 0 || c <= 0) {
            return false;
        }

        // 两边之和大于第三边
        if (a + b <= c) {
            return false;
        }
        if (a + c <= b) {
            return false;
        }
        if (c + b <= a) {
            return false;
        }
        return true;
    }

}
