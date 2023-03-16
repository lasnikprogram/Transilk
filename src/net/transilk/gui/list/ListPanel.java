package net.transilk.gui.list;

import net.transilk.dictionary.EntryHolder;
import net.transilk.util.ConstantsUtil;

import javax.swing.*;
import java.util.ArrayList;

public class ListPanel extends JPanel {
    private final ArrayList<EntryHolder> entries;

    public ListPanel() {
        entries = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ConstantsUtil.backgroundColor);
    }

    public void updatePanel() {
        removeAll();

        // sort alphabetically based on left side
        entries.sort((entry1, entry2) -> entry1.getLeft().compareToIgnoreCase(entry2.getLeft()));

        for (EntryHolder entryHolder : entries) {
            EntryPanel entryPanel = new EntryPanel(entryHolder);
            // fixes: floating almost randomly on x-Axis
            entryPanel.setAlignmentX(LEFT_ALIGNMENT);
            add(entryPanel);
        }
    }

    public void addEntry(EntryHolder entryHolder) {
        entries.add(entryHolder);
    }

    public void clearEntries() {
        entries.clear();
    }
}