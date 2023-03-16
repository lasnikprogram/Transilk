package net.transilk.gui.list;

import net.transilk.util.ConstantsUtil;

import javax.swing.*;
import java.awt.*;

public class EntryContentPanel extends JPanel {
    public EntryContentPanel(String left, String right) {
        setLayout(new GridLayout());

        addLabel(left);
        addLabel(right);
    }

    private void addLabel(String textOnLabel) {
        JLabel jLabel = new JLabel();
        jLabel.setFont(ConstantsUtil.defaultFont);
        jLabel.setForeground(Color.WHITE);
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setText("<html><p align=\"center\">" + textOnLabel + "</p></html>");

        add(jLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // horizontal line between panels
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
