package devtool;

import java.util.*;

import org.apache.commons.lang.*;

public class DefaultConnector implements Connector {

    private Adapter adapter;
    
    public DefaultConnector(Adapter adapter) {
        Validate.notNull(adapter, "adapter cannot be null!");
        this.adapter = adapter;
    }

    @Override
    public List<Key> search(String query) {
        return filter(adapter.listKeys(), query);
    }

    @Override
    public List<Content> get(Key key) {
        return adapter.getContent(key);
    }

    private List<Key> filter(List<Key> data, String query) {
        List<Key> list = new ArrayList<Key>();
        query = query != null ? query.toLowerCase() : null;
        for(Key key : data) {
            String id = key.toString().toLowerCase();
            if (query == null || id.contains(query)) {
                list.add(key);
            }
        }
        return list;
    }

}
