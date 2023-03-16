package net.transilk.gui.textField;

import net.transilk.listener.LanguageFieldListener;
import net.transilk.util.ConstantsUtil;
import net.transilk.util.RenderUtil;
import net.transilk.util.file.FlagImageUtil;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanguageField extends TextField {

    private final ExecutorService executorService;
    private String text;
    private BufferedImage bufferedImage;
    private LanguageFieldListener languageFieldListener;

    public LanguageField(String standardText) {
        setDocument(new TextFieldLimit(2));
        setSize(ConstantsUtil.languageFieldDimension);
        super.shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50);

        executorService = Executors.newSingleThreadExecutor();

        this.text = standardText;
        setText(this.text);
        loadBufferedImage();

        setHorizontalAlignment(CENTER);

        addActionListener(e -> transferFocusUpCycle());

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setBackgroundColor(ConstantsUtil.textFieldBackgroundColor);
                setText(text);
            }

            @Override
            public void focusLost(FocusEvent e) {
                loadBufferedImage();
            }
        });
    }

    private void loadBufferedImage() {
        text = getText();

        if (text.length() != 2) {
            bufferedImage = null;
            repaint();
            return;
        }

        if (languageFieldListener != null)
            languageFieldListener.fieldUpdated(text);

        executorService.execute(() -> {
            bufferedImage = FlagImageUtil.getBufferedLanguageImage(text);
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (bufferedImage == null) setBackgroundColor(ConstantsUtil.textFieldErrorBackgroundColor);
        else setBackgroundColor(ConstantsUtil.textFieldBackgroundColor);
        super.paintComponent(g);

        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);

        if (!hasFocus() && bufferedImage != null)
            drawLanguageFlag(graphics2D);

        drawOutline(graphics2D);
    }

    private void drawLanguageFlag(Graphics2D graphics2D) {
        graphics2D.setClip(super.shape);
        bufferedImage.getWidth();
        bufferedImage.getHeight();
        graphics2D.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void drawOutline(Graphics2D graphics2D) {
        // fake outline for antialiasing (because setClip() results in a jagged line)
        graphics2D.setClip(null);
        graphics2D.setColor(ConstantsUtil.backgroundColor);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.draw(super.shape);
    }

    public void addLanguageFieldListener(LanguageFieldListener languageFieldListener) {
        this.languageFieldListener = languageFieldListener;
    }
}