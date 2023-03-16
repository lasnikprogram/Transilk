package net.transilk.util.file;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ZipUtil {
    public static void unpackTarXzUrlToDirectory(String tarXzUrl, String extractedFolder) {
        // https://stackoverflow.com/a/50497853/15403976
        try {
            URL url = new URL(tarXzUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            unpackInputStreamTarXzToDirectory(inputStream, extractedFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unpackInputStreamTarXzToDirectory(InputStream in, String extractedFolder) throws IOException {
        XZCompressorInputStream xzCompressorInputStream = new XZCompressorInputStream(in);
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(xzCompressorInputStream);
        TarArchiveEntry entry;

        while ((entry = (TarArchiveEntry) tarArchiveInputStream.getNextEntry()) != null) {
            String entryPath = extractedFolder + entry.getName();

            if (!FileUtil.doesFileExist(entryPath))
                FileUtil.createDirectoryOfFile(entryPath);

            FileUtil.saveInputStreamToFile(entryPath, tarArchiveInputStream);
        }
    }
}
