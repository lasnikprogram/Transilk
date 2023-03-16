package net.transilk.util;

import net.transilk.util.file.FontUtil;

import java.awt.*;

public class ConstantsUtil {
    public static final int frameWidth = 1000;
    public static final int frameHeight = 700;
    public static final int screenSelectionPanelHeight = 60;
    public static final int screenSelectionTaskbarHeight = 20;
    public static final int screenSelectionTotalHeight = screenSelectionPanelHeight + screenSelectionTaskbarHeight;
    public static final Dimension searchFieldDimension = new Dimension(600, 50);
    public static final Dimension languageFieldDimension = new Dimension(70, 50);
    public static final Dimension screenSelectionButtonDimension = new Dimension(140, 40);
    public static final int listPanelWidth = 750;
    public static final int exitButtonDiameter = 20;
    public static final int exitButtonToCornerDestination = 5;
    public static final int spacing = 20;

    public static final Color backgroundColor = new Color(69, 69, 69);
    public static final Color screenSelectionBackgroundColor = new Color(51, 51, 51);
    public static final Color buttonTextColor = new Color(255, 255, 255);
    public static final Color buttonIdleColor = new Color(42, 42, 42);
    public static final Color buttonHovoredColor = new Color(30, 30, 30);
    public static final Color buttonPressedColor = new Color(15, 15, 15);
    public static final Color outlineColor = new Color(255, 255, 255);
    public static final Color textFieldBackgroundColor = new Color(100, 100, 100);
    public static final Color textFieldErrorBackgroundColor = new Color(200, 100, 100);
    public static final Color textFieldTextSelectedColor = new Color(50, 50, 50);
    public static final Color textFieldTextUnselectedColor = new Color(0, 0, 0);

    public static final Font defaultFont = FontUtil.getFont().deriveFont(15f);
    public static final Font authorFont = FontUtil.getFont().deriveFont(15f);
    public static final boolean shouldOwnFontBeUsed = true;
}
