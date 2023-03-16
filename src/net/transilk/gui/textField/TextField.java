package net.transilk.gui.textField;

import net.transilk.util.ConstantsUtil;
import net.transilk.util.RenderUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class TextField extends JTextField {
    private Color backgroundColor = ConstantsUtil.textFieldBackgroundColor;
    protected Shape shape;

    public TextField() {
        setDisabledTextColor(ConstantsUtil.textFieldTextUnselectedColor);
        setSelectedTextColor(ConstantsUtil.textFieldTextSelectedColor);
        setOpaque(false);
        setFont(ConstantsUtil.defaultFont);
        setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);
        graphics2D.setColor(backgroundColor);
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(graphics2D);
    }

    protected void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }
}
