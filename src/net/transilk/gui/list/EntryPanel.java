package net.transilk.gui.list;

import net.transilk.dictionary.EntryHolder;
import net.transilk.gui.MainGui;
import net.transilk.util.ConstantsUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntryPanel extends JPanel {
    public EntryPanel(EntryHolder entryHolder) {
        setLayout(new BorderLayout());

        EntryContentPanel shownEntryPanel = new EntryContentPanel(entryHolder.getLeft(), entryHolder.getRight());
        shownEntryPanel.setBackground(ConstantsUtil.textFieldBackgroundColor);
        shownEntryPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        add(shownEntryPanel, BorderLayout.NORTH);

        EntryContentPanel hiddenEntryPanel = new EntryContentPanel(entryHolder.getSee(), entryHolder.getSynonym());
        hiddenEntryPanel.setBackground(ConstantsUtil.backgroundColor);
        hiddenEntryPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        hiddenEntryPanel.setVisible(false);
        add(hiddenEntryPanel, BorderLayout.SOUTH);

        shownEntryPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                hiddenEntryPanel.setVisible(!hiddenEntryPanel.isVisible());

                // fixes bug when just a few EntryPanels are there: GUI is messy
                SwingUtilities.invokeLater(MainGui.getFrame()::repaint);
            }
        });
    }

    // https://stackoverflow.com/a/75674828/15403976
    @Override
    public Dimension getMaximumSize() {
        Dimension preferred = getPreferredSize();
        return new Dimension(ConstantsUtil.listPanelWidth, preferred.height);
    }
}
