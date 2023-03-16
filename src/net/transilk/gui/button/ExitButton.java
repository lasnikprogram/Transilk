package net.transilk.gui.button;

import net.transilk.util.ConstantsUtil;

import java.awt.geom.Ellipse2D;

public class ExitButton extends Button {

    public ExitButton() {
        int diameter = ConstantsUtil.exitButtonDiameter;
        super.shape = new Ellipse2D.Double(0, 0, diameter, diameter);
    }
}
