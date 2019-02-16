import static org.junit.Assert.*;

import org.junit.Test;

import gq.dengbo.Triangle;

public class TriangleTest {
    @Test
    public void test(){
        assertEquals(true, new Triangle().judgeEdges(3, 4, 5));
    }
}
