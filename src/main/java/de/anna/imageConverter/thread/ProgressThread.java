package de.anna.imageConverter.thread;

import de.anna.imageConverter.Application;
import de.anna.imageConverter.Main;

import java.io.File;


public class ProgressThread extends Thread {

    private int numberOfInputFiles;

    public ProgressThread(int aAnzahlVonInputFiles) {
        numberOfInputFiles = aAnzahlVonInputFiles;
    }

    @Override
    public void run() {

        while (true) {

            File outputPathAsFile = Application.OUTPUT_ORDER;
            File[] outputFiles = outputPathAsFile.listFiles();
            int numberOfOutputFiles = outputFiles.length;

            String progressBar = progressBar(numberOfInputFiles, numberOfOutputFiles);

            System.out.print("\r"  + progressBar + "Es wurde " + numberOfOutputFiles + "/" + numberOfInputFiles + " verarbeitet");

            if (numberOfOutputFiles == numberOfInputFiles) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }

    }

    private String progressBar(double numberOfInputFiles, double numberOfOutputFiles) {

        StringBuilder stringBuilder = new StringBuilder();
        double procent = numberOfOutputFiles / numberOfInputFiles * 100;

        stringBuilder.append("[");
        for(int i = 0; i <= 100; i++){
            if(i <= procent){
                stringBuilder.append("X");
            }else {
                stringBuilder.append(" ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

}
