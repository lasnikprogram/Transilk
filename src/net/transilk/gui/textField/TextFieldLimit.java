package net.transilk.gui.textField;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldLimit extends PlainDocument {
    private final int limit;

    TextFieldLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        // https://stackoverflow.com/a/3519182/15403976
        if (getLength() + str.length() > limit)
            return;

        super.insertString(offs, str, a);
    }
}
