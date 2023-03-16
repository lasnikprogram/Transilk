package net.transilk.util;

import java.awt.*;

public class RenderUtil {

    public static Graphics2D getAntiAliasedGraphics(Graphics graphics) {
        // https://stackoverflow.com/a/4286510/15403976
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        setRenderingHints(graphics2D);

        return graphics2D;
    }

    private static void setRenderingHints(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

    public static void drawCenteredString(String text, Dimension containerDimensions, Graphics2D graphics2D) {
        int textWidth = graphics2D.getFontMetrics().stringWidth(text);
        int textHeight = graphics2D.getFontMetrics().getHeight();
        int xLocation = containerDimensions.width / 2 - textWidth / 2;
        int yLocation = containerDimensions.height / 2 + textHeight / 2;

        graphics2D.drawString(text, xLocation, yLocation);
    }
}
