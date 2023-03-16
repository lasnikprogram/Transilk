package net.transilk.util;

import net.transilk.Transilk;

import java.util.prefs.Preferences;

public class PreferencesUtil {
    private static final String leftLanguageValue = "de";
    private static final String rightLanguageValue = "en";
    private static final String searchFieldValue = "";

    private static final String leftLanguage = "LEFT_LANGUAGE";
    private static final String rightLanguage = "RIGHT_LANGUAGE";
    private static final String searchField = "SEARCH_FIELD";

    // https://www.amitph.com/introduction-to-java-preferences-api/
    private static Preferences preferences;

    public static void loadPreferences() {
        preferences = Preferences.userNodeForPackage(Transilk.class);
    }

    public static String getLeftLanguage() {
        return preferences.get(leftLanguage, leftLanguageValue);
    }

    public static void setLeftLanguage(String string) {
        preferences.put(leftLanguage, string);
    }

    public static String getRightLanguage() {
        return preferences.get(rightLanguage, rightLanguageValue);
    }

    public static void setRightLanguage(String string) {
        preferences.put(rightLanguage, string);
    }

    public static String getSearchField() {
        return preferences.get(searchField, searchFieldValue);
    }

    public static void setSearchField(String string) {
        preferences.put(searchField, string);
    }
}
