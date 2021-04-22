package is.hi.hbv501g.matbjorg.matbjorg.Helpers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class BASE64DecodedMultipartFile implements MultipartFile {

    protected static final Logger log = LogManager.getLogger(BASE64DecodedMultipartFile.class);

    private byte[] imgContent;
    private String fileName;
    private String ext;

    public BASE64DecodedMultipartFile(String base64) {
        imgContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        fileName = UUID.randomUUID().toString() + ".jpg";
        ext = "image/jpg";
    }

    public String getExt() {
        return ext;
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        if(getExt() == null) {
            return null;
        }
        return getExt();
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException {
        try (FileOutputStream f = new FileOutputStream(dest)) {
            f.write(imgContent);
        }
    }
}
