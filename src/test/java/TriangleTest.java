import static org.junit.Assert.*;

import org.junit.Test;


public class TriangleTest {
    @Test
    public void test(){
        assertEquals(true, new gq.dengbo.demo.Triangle().judgeEdges(3, 4, 5));
    }
}
