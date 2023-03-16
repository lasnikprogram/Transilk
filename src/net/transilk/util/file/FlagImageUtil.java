package net.transilk.util.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class FlagImageUtil {

    // XX is a placeholder representing the language in ISO 639 1 format
    public static final String urlForLanguageFlags = "https://www.unknown.nu/flags/images/XX-100";

    public static BufferedImage getBufferedLanguageImage(String ISO639_1String) {
        String filePathOfImage = FileUtil.imageDirectory + ISO639_1String + ".png";
        FileInputStream fileInputStream = FileUtil.getFileIfExistent(filePathOfImage);
        if (fileInputStream == null) {
            String urlString = urlForLanguageFlags.replace("XX", ISO639_1String);
            FileUtil.downloadFromUrlToFile(urlString, filePathOfImage);
            fileInputStream = FileUtil.getFileIfExistent(filePathOfImage);
        }

        try {
            // https://stackoverflow.com/a/6464492/15403976
            if (fileInputStream != null) return ImageIO.read(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
