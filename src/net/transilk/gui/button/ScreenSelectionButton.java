package net.transilk.gui.button;

import net.transilk.util.ConstantsUtil;
import net.transilk.util.RenderUtil;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ScreenSelectionButton extends Button {
    private boolean selected;

    public ScreenSelectionButton() {
        Dimension dimension = ConstantsUtil.screenSelectionButtonDimension;
        int width = dimension.width;
        int height = dimension.height;

        super.shape = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, width / 2d, height / 2d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = RenderUtil.getAntiAliasedGraphics(g);

        // white outline for selected button
        if (selected) {
            graphics2D.setColor(ConstantsUtil.outlineColor);
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.draw(shape);
        }
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
