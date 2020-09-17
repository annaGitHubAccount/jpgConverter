package de.anna.main;

import com.google.common.collect.Lists;
import de.anna.factory.CombineIntoPdfFactory;
import de.anna.service.CombineIntoPDF;
import de.anna.thread.CompressThread;
import de.anna.thread.ProgressThread;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainJpg {

    public static final String INPUT_PATH = "D:/Workspace/temp/input";
    public static final String OUTPUT_PATH = "D:/Workspace/temp/output";


    public static void main(String[] args) throws IOException, InterruptedException {

        File inputOrder = new File(INPUT_PATH);
        File outputOrder = new File(OUTPUT_PATH);

        List<File> fileInputList = createFileInputList(inputOrder);

        int numberOfThreads = 4;
        List<List<File>> listOfFileLists = divideListOfInputFile(fileInputList, fileInputList.size() / numberOfThreads + 1);

        if (!outputOrder.exists()) {
            outputOrder.mkdir();
        }

        ProgressThread progressThread = new ProgressThread(fileInputList.size());
        progressThread.start();

        Thread[] threadsArray = new Thread[numberOfThreads + 1];

        for(int i = 0; i < numberOfThreads; i++){
            CompressThread compressThread = createCompressThread("compressThread" + (i + 1), inputOrder, outputOrder, listOfFileLists.get(i));
            threadsArray[i] = compressThread;
        }
        threadsArray[numberOfThreads] = progressThread;

        joinThreads(threadsArray);

        createPdfOfOutputFiles(outputOrder);

    }

    private static void joinThreads(Thread... threads) throws InterruptedException {

        for(Thread thread : threads){
            thread.join();
        }
    }

    private static CompressThread createCompressThread(String name, File inputOrder, File outputOrder, List<File> listOfFile) {

        CompressThread compressThread = new CompressThread(listOfFile, inputOrder, outputOrder);
        compressThread.setName(name);
        compressThread.start();
        return compressThread;
    }

    private static List<File> createFileInputList(File inputImagePath) {

        File[] filesInInputFolder = inputImagePath.listFiles();
        if (filesInInputFolder == null) {
            throw new IllegalStateException("File Folder doesn't exist!!! ");
        }
        return Arrays.asList(filesInInputFolder);
    }

    private static List<List<File>> divideListOfInputFile(List<File> fileInputList, int numberOfThreads) {

        List<List<File>> partitionFileInputLists = Lists.partition(fileInputList, numberOfThreads);
        return partitionFileInputLists;
    }

    private static void createPdfOfOutputFiles(File outputImagePath) throws IOException {

        System.out.println();
        System.out.println("--------------------------------- Create PDF --------------------------------------");

        CombineIntoPDF combineIntoPDF = CombineIntoPdfFactory.getInstance();
        File[] outputFiles = outputImagePath.listFiles();
        List<File> filesOutputList = Arrays.asList(outputFiles);
        List<String> stringList = filesOutputList.stream().map(file -> file.getPath()).collect(Collectors.toList());
        combineIntoPDF.combineImagesIntoPDF("D:/Workspace/temp/output.pdf", stringList);
    }


}
