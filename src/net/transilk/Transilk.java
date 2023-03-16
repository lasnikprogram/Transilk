package net.transilk;

import net.transilk.gui.MainGui;
import net.transilk.util.PreferencesUtil;
import net.transilk.util.file.FileUtil;

import javax.swing.*;

public class Transilk {
    public static void main(String[] args) {
        PreferencesUtil.loadPreferences();

        if (!FileUtil.doAllDirectoriesExist()) {
            FileUtil.createAllDirectories();
        }

        SwingUtilities.invokeLater(MainGui::new);
    }
}