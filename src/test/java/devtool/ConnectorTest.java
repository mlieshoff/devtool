package devtool;

import java.io.*;
import java.util.*;
import java.util.zip.*;

import org.junit.*;

import static org.junit.Assert.*;

public class ConnectorTest {

    private Connector connector;
    
    @Before
    public void setUp() throws IOException {
        connector = new DefaultConnector(new DefaultAdapter(new ZipFile(
                "src/test/resources/data.zip")));
    }

    @Test(expected=IllegalArgumentException.class)
    public void failsCreateWithNullAdapter() {
        new DefaultConnector(null);
    }

    @Test
    public void shouldSearchWithEmptyQuery() {
        List<Key> expected = Mocks.createKeyList();
        List<Key> actual = connector.search("");
        Mocks.assertKeysEquals(expected, actual);
    }

    @Test
    public void shouldSearchWithNullQuery() {
        List<Key> expected = Mocks.createKeyList();
        List<Key> actual = connector.search(null);
        Mocks.assertKeysEquals(expected, actual);
    }

    @Test
    public void shouldSearchWithQuery() {
        String query = "classpath";
        List<Key> expected = createFilteredKeyList(query);
        List<Key> actual = connector.search(query);
        Mocks.assertKeysEquals(expected, actual);
    }

    @Test
    public void shouldGetContentForKey() {
        List<Content> actual = connector.get(Mocks.createKey());
        List<Content> expected = Mocks.createContentList();
        assertEquals(expected, actual);
    }
    
    private List<Key> createFilteredKeyList(String query) {
        List<Key> list = new ArrayList<Key>();
        List<Key> data = Mocks.createKeyList();
        for(Key key : data) {
            String id = key.toString().toLowerCase();
            if (id.contains(query)) {
                list.add(key);
            }
        }
        return list;
    }

}
