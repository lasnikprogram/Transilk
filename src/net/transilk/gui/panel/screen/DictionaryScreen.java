package net.transilk.gui.panel.screen;

import net.transilk.dictionary.DictionarySearcher;
import net.transilk.dictionary.EntryHolder;
import net.transilk.gui.list.ListPanel;
import net.transilk.gui.textField.LanguageField;
import net.transilk.gui.textField.SearchField;
import net.transilk.util.ConstantsUtil;
import net.transilk.util.PreferencesUtil;
import net.transilk.util.RenderUtil;
import net.transilk.util.parser.ParserUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class DictionaryScreen extends JPanel {
    private JScrollPane scrollPane;
    private SearchField searchField;
    private int scrollPaneY;
    private ListPanel listPanel;
    private DictionarySearcher dictionarySearcher;

    private String leftLanguage = PreferencesUtil.getLeftLanguage();
    private String rightLanguage = PreferencesUtil.getRightLanguage();

    private String lastLeftLanguage;
    private String lastRightLanguage;
    private String lastSearchFieldText;

    public DictionaryScreen() {
        setSize(ConstantsUtil.frameWidth, ConstantsUtil.frameHeight - ConstantsUtil.screenSelectionPanelHeight - ConstantsUtil.screenSelectionTaskbarHeight);
        setBackground(ConstantsUtil.backgroundColor);
        setLayout(null);

        addSearchField();

        int languageFieldWidth = ConstantsUtil.languageFieldDimension.width;
        int languageFieldRightXPos = ConstantsUtil.frameWidth - languageFieldWidth - ConstantsUtil.spacing;
        LanguageField languageFieldRight = addLanguageField(rightLanguage, languageFieldRightXPos);
        languageFieldRight.addLanguageFieldListener(text -> {
            rightLanguage = text;
            PreferencesUtil.setRightLanguage(rightLanguage);
            startDictionarySearch();
        });

        LanguageField languageFieldLeft = addLanguageField(leftLanguage, ConstantsUtil.spacing);
        languageFieldLeft.addLanguageFieldListener(text -> {
            leftLanguage = text;
            PreferencesUtil.setLeftLanguage(leftLanguage);
            startDictionarySearch();
        });

        addScrollableListPanel();

        searchField.setText(PreferencesUtil.getSearchField());
    }

    private void addSearchField() {
        searchField = new SearchField();
        searchField.setLocation((ConstantsUtil.frameWidth - searchField.getWidth()) / 2, ConstantsUtil.spacing);
        searchField.addSearchFieldListener(this::startDictionarySearch);

        add(searchField);
    }

    private LanguageField addLanguageField(String language, int xPosition) {
        LanguageField languageField = new LanguageField(language);
        languageField.setLocation(xPosition, ConstantsUtil.spacing);

        add(languageField);

        return languageField;
    }

    private void addScrollableListPanel() {
        listPanel = new ListPanel();
        scrollPane = new JScrollPane(listPanel);
        scrollPaneY = 2 * ConstantsUtil.spacing + searchField.getHeight();
        scrollPane.setSize(ConstantsUtil.listPanelWidth, getHeight() - scrollPaneY - ConstantsUtil.spacing);
        scrollPane.setLocation((ConstantsUtil.frameWidth - ConstantsUtil.listPanelWidth) / 2, scrollPaneY);
        // workaround for windows behaviour: ScrollBar with Policy set to never is not scrollable
        // solution: width of 0
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, getHeight() - scrollPaneY - ConstantsUtil.spacing));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        // paint method doesn't get scrolled
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        // scrollbar + outline is painted when scrolled
        scrollPane.getViewport().addChangeListener(e -> repaint());
        add(scrollPane);
    }

    private void startDictionarySearch() {
        String searchFieldText = searchField.getText();

        PreferencesUtil.setSearchField(searchFieldText);

        if (searchFieldText.isBlank() || leftLanguage.isBlank() || rightLanguage.isBlank() || listPanel == null)
            return;


        if (!leftLanguage.equals(lastLeftLanguage) || !rightLanguage.equals(lastRightLanguage)) {
            dictionarySearcher = new DictionarySearcher(leftLanguage, rightLanguage);
        } else if (searchFieldText.equals(lastSearchFieldText)) return;

        listPanel.clearEntries();

        dictionarySearcher.search(searchFieldText.toLowerCase());
        for (String entry : dictionarySearcher.getResults()) {
            EntryHolder parsedEntry = ParserUtil.parseStringToEntry(entry);
            listPanel.addEntry(parsedEntry);
        }

        scrollPane.getVerticalScrollBar().setValue(0);
        scrollPane.getVerticalScrollBar().revalidate();
        listPanel.updatePanel();
        repaint();

        lastRightLanguage = rightLanguage;
        lastLeftLanguage = leftLanguage;
        lastSearchFieldText = searchFieldText;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);

        drawScrollbar(graphics2D);
        drawRoundOutline(graphics2D);
    }

    private void drawScrollbar(Graphics2D graphics2D) {
        int scrollPosition = scrollPane.getVerticalScrollBar().getValue();
        int scrollMaximum = scrollPane.getVerticalScrollBar().getMaximum();
        int borderOffset = 5;
        int scrollBarHeight = Math.min(scrollPane.getHeight(), scrollMaximum) * (scrollPane.getHeight() - (borderOffset * 2)) / scrollMaximum;
        int scrollBarMaximum = scrollPane.getHeight() - (borderOffset * 2);
        int scrollBarY = scrollPaneY + (scrollBarMaximum * scrollPosition) / scrollMaximum + borderOffset;

        int xOffsetFromBorder = (ConstantsUtil.frameWidth - scrollPane.getWidth()) / 2 + 15;

        graphics2D.setColor(ConstantsUtil.buttonIdleColor);
        graphics2D.fillRoundRect(ConstantsUtil.frameWidth - xOffsetFromBorder, scrollBarY, 10, scrollBarHeight, 5, 5);
    }

    private void drawRoundOutline(Graphics2D graphics2D) {
        int scrollPaneY = 2 * ConstantsUtil.spacing + ConstantsUtil.searchFieldDimension.height;
        int listWidth = ConstantsUtil.listPanelWidth;

        Rectangle outerRectangle = new Rectangle((ConstantsUtil.frameWidth - listWidth) / 2, scrollPaneY, listWidth, getHeight() - scrollPaneY - ConstantsUtil.spacing);
        Area area = new Area(outerRectangle);

        RoundRectangle2D innerRectangle = new RoundRectangle2D.Double((ConstantsUtil.frameWidth - listWidth) / 2d, scrollPaneY, listWidth, getHeight() - scrollPaneY - ConstantsUtil.spacing, 15, 15);
        Area innerRectangleArea = new Area(innerRectangle);
        area.subtract(innerRectangleArea);

        graphics2D.setColor(ConstantsUtil.backgroundColor);
        graphics2D.fill(area);

        graphics2D.setColor(ConstantsUtil.buttonIdleColor);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.draw(innerRectangleArea);
    }
}