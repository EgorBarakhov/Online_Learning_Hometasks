package ru.kpfu.itis.barakhov;

import java.util.Locale;

public class ConsoleWriter {

    public static void success() {
        System.out.println("File have been downloaded successfully!");
    }

    public static void downloadIsStarted() {
        System.out.println("Download have already started");
    }

    public static void writePercent() {
        System.out.printf(Locale.ENGLISH,"%2.2f", DownloadClass.checkPercent());
        System.out.println(" %");
    }

    public static void nothingIsDownloading() {
        System.out.println("Nothing is downloading");
    }

    public static void availableCommands() {
        System.out.println("Available commands: 'download', 'info', 'exit'.");
    }
}
