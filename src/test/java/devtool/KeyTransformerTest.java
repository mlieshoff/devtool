package devtool;

import java.util.*;
import java.util.zip.*;

import org.junit.*;

import static org.junit.Assert.*;

public class KeyTransformerTest {

    @Test(expected=IllegalArgumentException.class)
    public void failsBecauseNullData() {
        new KeyTransformer(null);
    }

    @Test
    public void shouldTransform() {
        Collection<ZipEntry> data = new ArrayList<ZipEntry>();
        ZipEntry file1 = new ZipEntry("build/ant/1.8/classpath/file1.txt");
        data.add(file1);
        ZipEntry file2 = new ZipEntry("build/ant/1.8/zip/file2.txt");
        data.add(file2);
        KeyTransformer transformer = new KeyTransformer(data);
        Key key1 = new Key("build", "ant", "1.8", "classpath");
        Key key2 = new Key("build", "ant", "1.8", "zip");
        List<Key> actual = transformer.transform();
        assertTrue("key1 not contained!", actual.contains(key1));
        assertTrue("key2 not contained!", actual.contains(key2));
    }

}
