package net.transilk.gui.panel;

import net.transilk.gui.MainGui;
import net.transilk.gui.button.ExitButton;
import net.transilk.gui.button.ScreenSelectionButton;
import net.transilk.gui.panel.screen.ScreenContainer;
import net.transilk.util.ConstantsUtil;
import net.transilk.util.RenderUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class ScreenSelectionPanel extends JPanel {
    private final ArrayList<ScreenSelectionButton> buttons;
    private final ScreenContainer screenContainer;
    private int mouseX;
    private int mouseY;

    public ScreenSelectionPanel(ScreenContainer screenContainer) {
        this.screenContainer = screenContainer;
        buttons = new ArrayList<>();

        setBackground(ConstantsUtil.screenSelectionBackgroundColor);
        setLayout(null);
        int height = ConstantsUtil.screenSelectionTaskbarHeight + ConstantsUtil.screenSelectionPanelHeight;
        setBounds(0, 0, ConstantsUtil.frameWidth, height);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
            }
        });

        // dragging functionality
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                MainGui.getFrame().setLocation(mouseEvent.getXOnScreen() - mouseX, mouseEvent.getYOnScreen() - mouseY);
            }
        });

        addExitButton();

        addButton("Dictionary");
        addButton("Trainer");
        addButton("Settings");

    }

    private void addExitButton() {
        ExitButton exitButton = new ExitButton();

        int horizontalPosition = ConstantsUtil.frameWidth - ConstantsUtil.exitButtonDiameter - ConstantsUtil.exitButtonToCornerDestination;
        int verticalPosition = ConstantsUtil.exitButtonToCornerDestination;

        exitButton.setLocation(horizontalPosition, verticalPosition);
        exitButton.setSize(ConstantsUtil.exitButtonDiameter, ConstantsUtil.exitButtonDiameter);

        exitButton.addActionListener(e -> System.exit(0));

        add(exitButton);
    }

    private void addButton(String buttonText) {
        ScreenSelectionButton button = new ScreenSelectionButton();
        button.setText(buttonText);

        Dimension dimension = ConstantsUtil.screenSelectionButtonDimension;
        int width = dimension.width;
        int height = dimension.height;
        int horizontalPosition = ConstantsUtil.frameWidth / 4 * (buttons.size() + 1) - (width / 2);
        int verticalPosition = ConstantsUtil.screenSelectionTaskbarHeight + ConstantsUtil.screenSelectionPanelHeight / 2 - height / 2;

        button.setLocation(horizontalPosition, verticalPosition);
        button.setSize(width, height);

        button.addActionListener(e -> buttonPressed(button));

        // first button should be selected (DictionaryScreen)
        if (buttons.isEmpty()) {
            button.setSelected(true);
        }

        add(button);
        buttons.add(button);
    }

    private void buttonPressed(ScreenSelectionButton screenSelectionButton) {
        buttons.forEach(button -> button.setSelected(false));
        screenSelectionButton.setSelected(true);

        int selectedScreenIndex = buttons.indexOf(screenSelectionButton);
        screenContainer.updateScreenTo(selectedScreenIndex);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(ConstantsUtil.authorFont);

        Dimension dimension = new Dimension(ConstantsUtil.frameWidth, ConstantsUtil.screenSelectionTaskbarHeight);
        RenderUtil.drawCenteredString("by Lasnik", dimension, graphics2D);
    }
}
