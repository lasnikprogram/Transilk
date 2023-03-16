package net.transilk.gui;

import net.transilk.gui.panel.ContainerPanel;
import net.transilk.util.ConstantsUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MainGui extends JFrame {
    public MainGui() {
        setTitle("Transilk");
        setUndecorated(true);
        setSize(ConstantsUtil.frameWidth, ConstantsUtil.frameHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setShape(new RoundRectangle2D.Double(0, 0, ConstantsUtil.frameWidth, ConstantsUtil.frameHeight, 20, 20));

        setLocationRelativeTo(null);

        add(new ContainerPanel());

        setVisible(true);
    }

    // Program only appears in one Frame, therefore first and only entry in getFrames()
    public static Frame getFrame() {
        return getFrames()[0];
    }
}
