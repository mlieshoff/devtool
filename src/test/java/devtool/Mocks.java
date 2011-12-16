package devtool;

import java.util.*;

import static org.junit.Assert.*;

public class Mocks {

    static List<Key> createKeyList() {
        List<Key> list = new ArrayList<Key>();
        list.add(createKey());
        list.add(new Key("build", "ant", "1.8", "zip"));
        return list;
    }
    

    static void assertKeysEquals(List<Key> expected, List<Key> actual) {
        assertEquals("size don't matches!", expected.size(), actual.size());
        for(Key key : expected) {
            assertTrue("key[" + key + "] not contained!", actual.contains(key));
        }
    }

    static Key createKey() {
        return new Key("build", "ant", "1.8", "classpath");
    }


    public static List<Content> createContentList() {
        List<Content> list = new ArrayList<Content>();
        list.add(new Content("file1.txt", "abc"));
        return list;
    }


}
