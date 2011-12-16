package devtool;

import java.util.*;
import java.util.zip.*;

import org.apache.commons.lang.*;

public class DefaultAdapter implements Adapter {

    private ZipFile dataFile;
    
    public DefaultAdapter(ZipFile dataFile){
        Validate.notNull(dataFile, "data file cannot be null!");
        this.dataFile = dataFile;
    }

    @Override
    public List<Key> listKeys() {
        Map<String, ZipEntry> map = new TreeMap<String, ZipEntry>();
        for(Enumeration<? extends ZipEntry> e = dataFile.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            String name = entry.getName();
            String directory = name.substring(0, name.lastIndexOf("/"));
            if (directory.split("/").length > 3) {
                map.put(directory, entry);
            }
        }
        KeyTransformer transformer = new KeyTransformer(map.values());
        return transformer.transform();
    }

    @Override
    public List<Content> getContent(Key key) {
        Validate.notNull(key, "key cannot be null!");
        ZipEntry directory = new ZipEntry(key.getCategory() + "/" + key.getProduct() + "/" 
                + key.getVersion() + "/" + key.getSubject());
        Collection<ZipEntry> files = new ArrayList<ZipEntry>();
        for(Enumeration<? extends ZipEntry> e = dataFile.entries(); e.hasMoreElements(); ) {
            ZipEntry entry = e.nextElement();
            if (!entry.isDirectory()) {
                if (entry.getName().startsWith(directory.getName())) {
                    files.add(entry);
                }
            }
        }
        ContentTransformer transformer = new ContentTransformer(dataFile, 
                files);
        return transformer.transform();
    }
    
}
