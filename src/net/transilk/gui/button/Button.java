package net.transilk.gui.button;

import net.transilk.util.ConstantsUtil;
import net.transilk.util.RenderUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Button extends JButton {
    public Shape shape;
    private boolean hovered, pressed;

    public Button() {
        setContentAreaFilled(false);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorderPainted(false);
        setFont(ConstantsUtil.defaultFont);
        setForeground(ConstantsUtil.buttonTextColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);

        if (pressed) graphics2D.setColor(ConstantsUtil.buttonPressedColor);
        else if (hovered) graphics2D.setColor(ConstantsUtil.buttonHovoredColor);
        else graphics2D.setColor(ConstantsUtil.buttonIdleColor);

        graphics2D.fill(shape);
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }
}
