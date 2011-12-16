package devtool;

import java.util.*;

public interface Adapter {
    List<Key> listKeys();
    List<Content> getContent(Key key);
}
