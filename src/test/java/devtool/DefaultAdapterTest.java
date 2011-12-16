package devtool;

import java.util.*;
import java.util.zip.*;

import org.junit.*;

import static org.junit.Assert.*;

public class DefaultAdapterTest {

    private Adapter adapter;
    
    @Before
    public void setUp() throws Exception {
        adapter = new DefaultAdapter(new ZipFile("src/test/resources/data.zip"));
    }

    @Test
    public void shouldListKeys() {
        List<Key> actual = adapter.listKeys();
        List<Key> expected = Mocks.createKeyList();
        Mocks.assertKeysEquals(expected, actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void failGetContentBecauseNullKey() {
        adapter.getContent(null);
    }
    
    @Test
    public void shouldGetContent() {
        List<Content> actual = adapter.getContent(Mocks.createKey());
        List<Content> expected = Mocks.createContentList();
        assertEquals(expected, actual);
    }

}
