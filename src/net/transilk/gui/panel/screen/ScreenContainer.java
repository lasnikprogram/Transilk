package net.transilk.gui.panel.screen;

import net.transilk.util.ConstantsUtil;

import javax.swing.*;

public class ScreenContainer extends JPanel {
    private final JPanel[] screensToSelectFrom = new JPanel[3];

    public ScreenContainer() {
        setLayout(null);
        setBounds(0, ConstantsUtil.spacing + ConstantsUtil.screenSelectionPanelHeight,
                ConstantsUtil.frameWidth, ConstantsUtil.frameHeight - ConstantsUtil.screenSelectionPanelHeight);

        screensToSelectFrom[0] = new DictionaryScreen();
        add(screensToSelectFrom[0]);

        screensToSelectFrom[1] = new TrainerScreen();
        add(screensToSelectFrom[1]);

        screensToSelectFrom[2] = new SettingsScreen();
        add(screensToSelectFrom[2]);
    }

    public void updateScreenTo(int screenIndex) {
        hideAllScreens();
        screensToSelectFrom[screenIndex].setVisible(true);
    }

    private void hideAllScreens() {
        for (JPanel panel : screensToSelectFrom) {
            panel.setVisible(false);
        }
    }
}
