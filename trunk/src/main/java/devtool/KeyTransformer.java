package devtool;

import java.util.*;
import java.util.zip.*;

import org.apache.commons.lang.*;

public class KeyTransformer {

    private Collection<ZipEntry> data;

    public KeyTransformer(Collection<ZipEntry> data) {
        Validate.notNull(data, "data cannot be null!");
        this.data = data;
    }

    public List<Key> transform() {
        List<Key> list = new ArrayList<Key>();
        for(ZipEntry file : data) {
            String name = file.getName();
            String[] cats = name.split("/");
            if (cats.length > 3) {
                System.out.println("transform: " + name);
                Key key = new Key(cats[0], cats[1], cats[2], cats[3]);
                System.out.println("key: " + key);
                list.add(key);
            }
        }
        return list;
    }

}
