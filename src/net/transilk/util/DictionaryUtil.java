package net.transilk.util;

import net.transilk.dictionary.IndexHolder;
import net.transilk.util.file.FileUtil;
import net.transilk.util.file.ZipUtil;
import net.transilk.util.parser.JsonParserUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class DictionaryUtil {
    // XXX and YYY are placeholder representing both languages in ISO 639 2
    public final static String wordlistDirectory = FileUtil.toUniversalPath(FileUtil.dictionaryDirectory + "XXX-YYY/");
    public final static String dictionaryFile = wordlistDirectory + "XXX-YYY.dict.dz";
    public final static String indexFile = wordlistDirectory + "XXX-YYY.index";
    public final static int maxDisplayedDictionaryEntries = 30;
    private final static String base64List = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static IndexHolder getDictionary(String leftLanguage, String rightLanguage) {
        String filePath = languagesToFilePath(wordlistDirectory, leftLanguage, rightLanguage);

        if (filePath == null) return null;

        if (!FileUtil.doesFileExist(filePath)) {
            String dictionaryUrl = formUrl(leftLanguage, rightLanguage);
            if (dictionaryUrl == null) return null;
            ZipUtil.unpackTarXzUrlToDirectory(dictionaryUrl, FileUtil.dictionaryDirectory);
        }

        String indexFilePath = languagesToFilePath(indexFile, leftLanguage, rightLanguage);

        return parseIndexFile(indexFilePath);
    }

    private static IndexHolder parseIndexFile(String indexFile) {
        IndexHolder indexHolder = new IndexHolder();
        BufferedReader bufferedReader = FileUtil.getBufferedReaderFromFilePath(indexFile);

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split("\t");
                int absoluteBytePosition = base64Decode(splitLine[1]);
                int byteLength = base64Decode(splitLine[2]);
                indexHolder.put(splitLine[0], absoluteBytePosition, byteLength);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return indexHolder;
    }

    private static int base64Decode(String string) {
        // https://github.com/jgoerzen/dictdlib/blob/e0e9d35edfe760511186b4b964d8d85d157c374c/dictdlib.py#L44
        int bytes = 0;
        int shiftAmount = 0;

        for (int i = string.length() - 1; i >= 0; i--) {
            int charIndex = base64List.indexOf(string.charAt(i));
            bytes = bytes | (charIndex << shiftAmount);
            shiftAmount += 6;
        }
        return bytes;
    }

    private static String formUrl(String leftLanguage, String rightLanguage) {
        String left = Iso639MapperUtil.getIso639_2FromIso639_1(leftLanguage);
        String right = Iso639MapperUtil.getIso639_2FromIso639_1(rightLanguage);
        return JsonParserUtil.getUrlForLanguage(left, right);
    }

    public static String languagesToFilePath(String filePath, String leftLanguage, String rightLanguage) {
        leftLanguage = Iso639MapperUtil.getIso639_2FromIso639_1(leftLanguage);
        rightLanguage = Iso639MapperUtil.getIso639_2FromIso639_1(rightLanguage);
        if (leftLanguage == null || rightLanguage == null) return null;
        return placeHolderFilePathToRealFilePath(filePath, leftLanguage, rightLanguage);
    }

    public static String placeHolderFilePathToRealFilePath(String filePath, String leftLanguage, String rightLanguage) {
        return filePath.replaceAll("XXX", leftLanguage).replaceAll("YYY", rightLanguage);
    }
}
