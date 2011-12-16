package devtool;

import java.io.*;
import java.util.*;
import java.util.zip.*;

import org.junit.*;

import static org.junit.Assert.*;

public class ContentTransformerTest {

    private File file = new File("src/test/resources/data.zip");
    
    @Test(expected=IllegalArgumentException.class)
    public void failsBecauseNullData() throws Exception {
        new ContentTransformer(new ZipFile(file), null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void failsBecauseNullZip() {
        new ContentTransformer(null, new ArrayList<ZipEntry>());
    }

    @Test
    public void shouldTransform() throws Exception {
        System.out.println("BEGIN shouldTransform");
        ZipFile zipFile = new ZipFile(file);
        Collection<ZipEntry> data = new ArrayList<ZipEntry>();
        ZipEntry file1 = new ZipEntry("build/ant/1.8/classpath/file1.txt");
        data.add(file1);
        ContentTransformer transformer = new ContentTransformer(zipFile, data);
        Content content = new Content("file1.txt", "abc");
        List<Content> actual = transformer.transform();
        System.out.println(actual);
        assertTrue("content1 not contained!", actual.contains(content));
        System.out.println("END shouldTransform");
    }

}
