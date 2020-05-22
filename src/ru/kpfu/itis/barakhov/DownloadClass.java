package ru.kpfu.itis.barakhov;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadClass implements Runnable {

    private static URLConnection connection;
    private static int facticalSize;

    @Override
    public void run() {
        URL toDownload;
        try {
            toDownload = new URL("https://logic.pdmi.ras.ru/~dvk/graphs_dk.pdf");
            downloadFile(toDownload);
            ConsoleWriter.success();
        } catch (MalformedURLException e) {
            System.out.println("Issues with URL");
        } catch (IOException e) {
            System.out.println("Issues with connection");
        }
    }

    public static void downloadFile(URL toDownload) throws IOException {
        Path content = Paths.get("graph.pdf");
        if (!Files.exists(content)) {
            Files.createFile(content);
        }
        FileWriter writer = new FileWriter(String.valueOf(content));
        connection = toDownload.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            writer.write(line + "\n");
            facticalSize += (line.length());
        }
        writer.flush();
        writer.close();
    }

    public static double checkPercent() {
        double size = connection.getContentLength();
        double percent = facticalSize / size;
        return percent * 100.0d;
    }

}