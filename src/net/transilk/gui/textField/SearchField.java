package net.transilk.gui.textField;

import net.transilk.listener.SearchFieldListener;
import net.transilk.util.ConstantsUtil;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.geom.RoundRectangle2D;

public class SearchField extends TextField {
    private SearchFieldListener searchFieldListener;

    public SearchField() {
        setDocument(new TextFieldLimit(70));
        setSize(ConstantsUtil.searchFieldDimension);
        super.shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 100, 100);

        // https://stackoverflow.com/a/3953219/15403976
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchFieldListener.fieldUpdated();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchFieldListener.fieldUpdated();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void addSearchFieldListener(SearchFieldListener searchFieldListener) {
        this.searchFieldListener = searchFieldListener;
    }
}
