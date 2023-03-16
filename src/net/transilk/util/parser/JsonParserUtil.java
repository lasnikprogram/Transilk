package net.transilk.util.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.transilk.json.JsonDictionary;
import net.transilk.json.JsonDictionaryRelease;
import net.transilk.util.file.FileUtil;

import java.util.Collection;

public class JsonParserUtil {
    private static final String urlFreeDictApi = "https://freedict.org/freedict-database.json";
    private static Gson gson;

    public static String getUrlForLanguage(String left, String right) {
        if (gson == null) gson = new Gson();
        String jsonString = FileUtil.readFileFromURL(urlFreeDictApi);
        if (jsonString == null) return null;


        // https://github.com/google/gson/blob/master/UserGuide.md#collections-examples
        TypeToken<Collection<JsonDictionary>> collectionType = new TypeToken<>(){};
        Collection<JsonDictionary> jsonDictionaries = gson.fromJson(jsonString, collectionType);

        for (JsonDictionary dictionary : jsonDictionaries) {
            if (!dictionary.isValid())
                return null;
            String[] splitDictionaryName = dictionary.getName().split("-");

            if (left.equals(splitDictionaryName[0]) && right.equals(splitDictionaryName[1])) {
                return dictionaryToUrl(dictionary);
            }
        }

        return null;
    }

    private static String dictionaryToUrl(JsonDictionary jsonDictionary) {
        for (JsonDictionaryRelease dictionaryRelease : jsonDictionary.getReleases()) {
            // three release types: src, dictd, slob (dictd uses indexing, also mean used in this program)
            if (dictionaryRelease.getPlatform().equals("dictd")) {
                return dictionaryRelease.getURL();
            }
        }

        return null;
    }
}
