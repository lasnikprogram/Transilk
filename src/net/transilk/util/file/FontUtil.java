package net.transilk.util.file;

import net.transilk.util.ConstantsUtil;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FontUtil {
    private static Font font;

    public static void prepareFont() {
        try {
            if (ConstantsUtil.shouldOwnFontBeUsed) {
                InputStream fontStream = new FileInputStream(FileUtil.fontDirectory + "Comfortaa-Bold.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            } else {
                useFallBackFont();
            }
        } catch (IOException | FontFormatException e) {
            System.err.println("Consider putting \"Comfortaa-Bold.ttf\" in the \"fonts\" folder");
            useFallBackFont();
            e.printStackTrace();
        }
    }

    public static Font getFont() {
        if (font == null) prepareFont();
        return font;
    }

    private static void useFallBackFont() {
        System.err.println("Using fallback font Futura");
        font = new Font("Futura", Font.PLAIN, 1);
    }
}
