package net.transilk.dictionary;

import net.transilk.util.DictionaryUtil;
import org.dict.zip.DictZipInputStream;

import java.io.IOException;
import java.util.ArrayList;

public class DictionarySearcher {
    private final ArrayList<String> results = new ArrayList<>();
    private final IndexHolder indexHolder;
    private DictZipInputStream dictZipInputStream;

    public DictionarySearcher(String leftLanguage, String rightLanguage) {
        indexHolder = DictionaryUtil.getDictionary(leftLanguage, rightLanguage);

        try {
            String langFilePath = DictionaryUtil.languagesToFilePath(DictionaryUtil.dictionaryFile, leftLanguage, rightLanguage);
            if (langFilePath == null || indexHolder == null) return;
            dictZipInputStream = new DictZipInputStream(langFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(String searchString) {
        if (dictZipInputStream == null || indexHolder == null) return;

        results.clear();

        int foundCounter = 0;
        for (String key : indexHolder.getKeySet()) {
            if (key.startsWith(searchString)) {
                if (foundCounter >= DictionaryUtil.maxDisplayedDictionaryEntries) return;

                IndexEntry indexEntry = indexHolder.get(key);
                addStringFromPosition(indexEntry.getAbsoluteBytePosition(), indexEntry.getByteLength());
                foundCounter++;
            }
        }
    }

    private void addStringFromPosition(int absoluteBytePosition, int byteLength) {
        try {
            dictZipInputStream.seek(absoluteBytePosition);
            byte[] bytes = new byte[byteLength];
            dictZipInputStream.readFully(bytes);
            results.add(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
