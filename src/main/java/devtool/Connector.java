package devtool;

import java.util.*;

public interface Connector {

    List<Key> search(String query);
    List<Content> get(Key key);
    
}
