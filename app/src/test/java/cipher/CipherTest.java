/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cipher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class CipherTest {
    static List<String[]> testCases = new ArrayList<>();

    static {
        testCases.add(new String[] { "HELLO WORLD", "AHELLO WORLD", "A" });
        testCases.add(new String[] { "HELLO WORLD", "BGDKKN VNQKC", "B" });
        testCases.add(new String[] { "HELLO WORLD", "0ZW336 ,693V", "0" });
        testCases.add(new String[] { "HELLO WORLD", "9QNUUX 5X0UM", "9" });
        testCases.add(new String[] { "HELLO WORLD", "(PMTTW 4WZTL", "(" });
        testCases.add(new String[] { "HELLO WORLD", ")OLSSV 3VYSK", ")" });
        testCases.add(new String[] { "HELLO WORLD", "*NKRRU 2UXRJ", "*" });
        testCases.add(new String[] { "HELLO WORLD", "/IFMMP XPSME", "/" });

        testCases.add(new String[] { "AB89()*+,-./", "AAB89()*+,-./", "A" });
        testCases.add(new String[] { "AB89()*+,-./", "B/A789()*+,-.", "B" });
        testCases.add(new String[] { "AB89()*+,-./", "0STIJKLMNOPQR", "0" });
        testCases.add(new String[] { "AB89()*+,-./", "9JK/ABCDEFGHI", "9" });
        testCases.add(new String[] { "AB89()*+,-./", "(IJ./ABCDEFGH", "(" });
        testCases.add(new String[] { "AB89()*+,-./", ")HI-./ABCDEFG", ")" });
        testCases.add(new String[] { "AB89()*+,-./", "*GH,-./ABCDEF", "*" });
        testCases.add(new String[] { "AB89()*+,-./", "/BC9()*+,-./A", "/" });
    }

    @Test void testEncode() {
        for (String[] testCase : testCases) {
            Cipher cipher = new Cipher(testCase[2]);
            String result = cipher.encode(testCase[0]);
            assertEquals(testCase[1], result);
        }
    }

    @Test void testRandomEncode() {
        Cipher cipher = new Cipher();

        for (int i = 0; i < 100; i++) {
            String encoded = cipher.encode("HELLO WORLD");
            String decoded = cipher.decode(encoded);

            assertEquals("HELLO WORLD", decoded);
        }
    }

    @Test void testDecode() {
        for (String[] testCase : testCases) {
            Cipher cipher = new Cipher();
            String result = cipher.decode(testCase[1]);
            assertEquals(testCase[0], result);
        }
    }

    @Test void testInvalidEncode() {
        Cipher cipher = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> cipher.encode("a"));
        assertThrows(IllegalArgumentException.class, () -> cipher.encode("z"));
    }

    @Test void testInvalidDecode() {
        Cipher cipher = new Cipher();
        assertThrows(IllegalArgumentException.class, () -> cipher.decode("a"));
        assertThrows(IllegalArgumentException.class, () -> cipher.decode("z"));
        assertThrows(IllegalArgumentException.class, () -> cipher.decode(" A"));
    }
}
