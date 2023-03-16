package net.transilk.gui.panel.screen;

import net.transilk.util.ConstantsUtil;
import net.transilk.util.RenderUtil;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JPanel {
    public SettingsScreen() {
        setSize(ConstantsUtil.frameWidth, ConstantsUtil.frameHeight - ConstantsUtil.screenSelectionPanelHeight - ConstantsUtil.screenSelectionTaskbarHeight);
        setBackground(ConstantsUtil.backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(ConstantsUtil.defaultFont);
        String text = "Settings Coming Soon";

        int width = ConstantsUtil.frameWidth;
        int height = ConstantsUtil.frameHeight - ConstantsUtil.screenSelectionTotalHeight;

        RenderUtil.drawCenteredString(text, new Dimension(width, height), graphics2D);
    }
}
