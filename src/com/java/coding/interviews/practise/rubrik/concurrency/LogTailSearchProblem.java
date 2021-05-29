package com.java.coding.interviews.practise.rubrik.concurrency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author https://crunchify.com
 */

public class LogTailSearchProblem implements Runnable {

    private boolean debug = false;

    private int crunchifyRunEveryNSeconds = 2000;
    private long lastKnownPosition = 0;
    private boolean shouldIRun = true;
    private File crunchifyFile = null;
    private static int crunchifyCounter = 0;

    public LogTailSearchProblem(String myFile, int myInterval) {
        crunchifyFile = new File(myFile);
        this.crunchifyRunEveryNSeconds = myInterval;
    }

    private void printLine(String message) {
        System.out.println(message);
    }

    public void stopRunning() {
        shouldIRun = false;
    }

    public void run() {
        try {
            while (shouldIRun) {
                Thread.sleep(crunchifyRunEveryNSeconds);
                long fileLength = crunchifyFile.length();
                if (fileLength > lastKnownPosition) {
                    // Reading and writing file
                    RandomAccessFile readWriteFileAccess = new RandomAccessFile(crunchifyFile, "rw");
                    readWriteFileAccess.seek(lastKnownPosition);
                    String crunchifyLine = null;
                    while ((crunchifyLine = readWriteFileAccess.readLine()) != null) {
                        this.printLine(crunchifyLine);
                        crunchifyCounter++;
                    }
                    lastKnownPosition = readWriteFileAccess.getFilePointer();
                    readWriteFileAccess.close();
                } else {
                    if (debug)
                        this.printLine("Hmm.. Couldn't found new line after line # " + crunchifyCounter);
                }
            }
        } catch (Exception e) {
            this.printLine("Exception");
            stopRunning();
        }
        if (debug)
            this.printLine("Exit the program...");
    }

    public static void main(String argv[]) {

        ExecutorService crunchifyExecutor = Executors.newFixedThreadPool(4);

        // Replace username with your real value
        // For windows provide different path like: c:\\temp\\crunchify.log
        String filePath = "C:\\personal\\Amazon-Practice\\src\\com\\java\\amazon\\dynamic\\rubrik\\concurrency\\test.log";
        LogTailSearchProblem crunchify_tailF = new LogTailSearchProblem(filePath, 2000);

        // Start running log file tailer on crunchify.log file
        crunchifyExecutor.execute(crunchify_tailF);

        // Start pumping data to file crunchify.log file
        appendData(filePath, true, 5000);

    }

    /**
     * Use appendData method to add new line to file, so above tailer method can print the same in Eclipse Console
     *
     * @param filePath
     * @param shouldIRun
     * @param crunchifyRunEveryNSeconds
     */
    private static void appendData(String filePath, boolean shouldIRun, int crunchifyRunEveryNSeconds) {
        FileWriter fileWritter;

        try {
            while (shouldIRun) {
                Thread.sleep(crunchifyRunEveryNSeconds);
                fileWritter = new FileWriter(filePath, true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

                String data = "\nCrunchify.log file content: " + Math.random();
                bufferWritter.write(data);
                bufferWritter.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}