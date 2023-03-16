package net.transilk.util.file;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    private static final String resourceDirectoryStandardSeparated = System.getProperty("user.dir") + "/resources";

    public static final String fontDirectory = toUniversalPath(resourceDirectoryStandardSeparated + "/fonts/");
    public static final String dictionaryDirectory = toUniversalPath(resourceDirectoryStandardSeparated + "/dictionaries/");
    public static final String imageDirectory = toUniversalPath(resourceDirectoryStandardSeparated + "/images/");


    // works on every OS
    public static String toUniversalPath(String filePath) {
        return filePath.replaceAll("/", "\\" + File.separator);
    }


    public static FileInputStream getFileIfExistent(String filePath) {
        if (doesFileExist(filePath)) {
            try {
                File file = new File(filePath);
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else return null;
    }

    public static boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void saveInputStreamToFile(String filePath, InputStream inputStream) throws IOException {
        // https://stackoverflow.com/a/9620718/15403976
        boolean createdFile = new File(filePath).createNewFile();
        if (!createdFile) return;

        int count;
        byte[] data = new byte[1024];
        FileOutputStream fos = new FileOutputStream(filePath, false);
        try (BufferedOutputStream dest = new BufferedOutputStream(fos)) {
            while ((count = inputStream.read(data)) != -1) {
                dest.write(data, 0, count);
            }
        }
    }

    public static void downloadFromUrlToFile(String url, String file) {
        // https://stackoverflow.com/a/921400/15403976
        try (FileOutputStream fos = new FileOutputStream(file)) {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());

            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException exception) {
            System.err.println("URL couldn't be found: " + url);
        }
    }

    public static void createDirectory(String directoryPath) {
        File file = new File(directoryPath);
        file.mkdirs();
    }

    public static void createDirectoryOfFile(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    public static String readFileFromURL(String url) {
        try {
            URL website = new URL(url);
            URLConnection urlConnection = website.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            return inputStreamToString(inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public static BufferedReader getBufferedReaderFromFilePath(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            return new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String inputStreamToString(InputStream inputStream) throws IOException {
        // https://stackoverflow.com/a/35446009/15403976
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            byteArrayOutputStream.write(buffer, 0, length);
        }

        return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    }

    public static void createAllDirectories() {
        createDirectory(fontDirectory);
        createDirectory(dictionaryDirectory);
        createDirectory(imageDirectory);
    }

    public static boolean doAllDirectoriesExist() {
        boolean fontDirectoryExists = doesFileExist(fontDirectory);
        boolean dictionaryDirectoryExists = doesFileExist(dictionaryDirectory);
        boolean imageDirectoryExists = doesFileExist(imageDirectory);

        return fontDirectoryExists && dictionaryDirectoryExists && imageDirectoryExists;
    }
}