package devtool;

import java.io.*;
import java.util.*;
import java.util.zip.*;

import org.apache.commons.lang.*;

public class ContentTransformer {

    private Collection<ZipEntry> data;
    private ZipFile zipFile;

    public ContentTransformer(ZipFile zipFile, Collection<ZipEntry> data) {
        Validate.notNull(zipFile, "zip file cannot be null!");
        Validate.notNull(data, "data cannot be null!");
        this.zipFile = zipFile;
        this.data = data;
    }

    public List<Content> transform() {
        List<Content> list = new ArrayList<Content>();
        for(ZipEntry file : data) {
            try {
                InputStream is = zipFile.getInputStream(file);
                Validate.notNull(is, "no input stream for file: " + file.getName());
                String filename = file.getName();
                String shortFilename = filename.substring(filename.lastIndexOf("/") + 1, 
                        filename.length());
                list.add(new Content(shortFilename, inputStreamToString(is)));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return list;
    }
    
    private String inputStreamToString(InputStream is) throws IOException {
        Validate.notNull(is, "input stream cannot be null!");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        bufferedReader.close();
        return stringBuilder.toString().substring(0, stringBuilder.length()-1);
    }

}
