package ru.kpfu.itis.barakhov;

import java.util.Scanner;

public class ConsoleReader implements Runnable {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String line;
        Thread source = null;
        boolean alive = false;
        while (true) {
            line = sc.nextLine();
            switch (line) {
                case ("download") : {
                    try {
                        if (alive) {
                            ConsoleWriter.downloadIsStarted();
                        } else {
                            source = new Thread(new DownloadClass());
                            source.start();
                            alive = source.isAlive();
                        }
                    } catch (NullPointerException npe) {
                        System.out.println("Thread is dead");
                    }
                    break;
                }
                case ("info") : {
                    try {
                        if (alive) {
                            ConsoleWriter.writePercent();
                            alive = source.isAlive();
                        } else {
                            ConsoleWriter.nothingIsDownloading();
                        }
                    } catch (NullPointerException npe) {
                        System.out.println("Thread is dead");
                    }
                    break;
                }
                case ("exit") : {
                    System.exit(0);
                }
                default : {
                    ConsoleWriter.availableCommands();
                    break;
                }
            }
        }
    }
}
