import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(4);

    // Your tests go here.
    @Test
    public void testEqualChars(){
        assertTrue(offByN.equalChars('a','e'));
    }
}
