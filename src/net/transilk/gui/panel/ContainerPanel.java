package net.transilk.gui.panel;

import net.transilk.gui.panel.screen.ScreenContainer;
import net.transilk.util.ConstantsUtil;

import javax.swing.*;

public class ContainerPanel extends JPanel {
    public ContainerPanel() {
        setLayout(null);
        setBackground(ConstantsUtil.backgroundColor);

        ScreenContainer screenContainer = new ScreenContainer();
        ScreenSelectionPanel screenSelectionPanel = new ScreenSelectionPanel(screenContainer);

        add(screenSelectionPanel);
        add(screenContainer);
    }
}
