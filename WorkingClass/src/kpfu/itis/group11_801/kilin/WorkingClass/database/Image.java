package kpfu.itis.group11_801.kilin.workingClass.database;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.*;

public class Image {
    private String imagePath;

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static Image CreateImage(Part photoPart, ServletContext servletContext) {
        String pathName = servletContext.getRealPath("") + File.separator + "files" + File.separator;
        String smallPath = "files" + File.separator;
        File dir = new File(pathName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            BufferedInputStream fileContent = new BufferedInputStream(photoPart.getInputStream());
            if (fileContent.available() == 0) {
                return null;
            }
            String fileName = System.currentTimeMillis() + ".png";
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(pathName + fileName));
            while (fileContent.available() != 0) {
                outputStream.write(fileContent.read());
            }
            outputStream.close();
            fileContent.close();
            return new Image(smallPath + fileName);
        } catch (IOException e) {
            return null;
        }
    }

}