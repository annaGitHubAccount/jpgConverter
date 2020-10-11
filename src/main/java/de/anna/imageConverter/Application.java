package de.anna.imageConverter;

import com.google.common.collect.Lists;
import de.anna.imageConverter.config.ParameterDto;
import de.anna.imageConverter.config.ProgrammParameterParser;
import de.anna.imageConverter.enums.ProgramParameterEnum;
import de.anna.imageConverter.factory.CombineIntoPdfFactory;
import de.anna.imageConverter.service.CombineIntoPDF;
import de.anna.imageConverter.thread.CompressThread;
import de.anna.imageConverter.thread.ProgressThread;
import de.anna.imageConverter.util.StringUtil;
import de.anna.imageConverter.validator.ParamValidator;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static File OUTPUT_ORDER = null;

    public void start(String[] args) throws InterruptedException, IOException {

        showDescriptionToHelpParameterAndEndTheApp(args);

        ProgrammParameterParser programmParameterParser = new ProgrammParameterParser();
        ParameterDto parameterDto = programmParameterParser.parse(args);

        ParamValidator.validate(parameterDto);

        File inputOrder = new File(parameterDto.getInputPath());
        OUTPUT_ORDER = new File(parameterDto.getOutputPath());

        List<File> fileInputList = createFileInputList(inputOrder);

        int numberOfThreads = parameterDto.getNumberOfThreads();
        List<List<File>> listOfFileLists = divideListOfInputFile(fileInputList, fileInputList.size() / numberOfThreads + 1);

        if (!OUTPUT_ORDER.exists()) {
            OUTPUT_ORDER.mkdir();
        }

        ProgressThread progressThread = new ProgressThread(fileInputList.size());
        progressThread.start();

        Thread[] threadsArray = new Thread[numberOfThreads + 1];

        for (int i = 0; i < numberOfThreads; i++) {
            CompressThread compressThread = createCompressThread("compressThread" + (i + 1), inputOrder, OUTPUT_ORDER, listOfFileLists.get(i), parameterDto);
            threadsArray[i] = compressThread;
        }
        threadsArray[numberOfThreads] = progressThread;

        joinThreads(threadsArray);

        createPdfOfOutputFiles(OUTPUT_ORDER, parameterDto);

    }

    private void showDescriptionToHelpParameterAndEndTheApp(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        if (!StringUtil.isEmpty(args[0])) {
            if (args[0].startsWith(ProgramParameterEnum.HELP.getLongParameterName()) || (args[0].startsWith(ProgramParameterEnum.HELP.getShortParameterName()))) {
                ProgramParameterEnum[] programParameterEnums = ProgramParameterEnum.values();
                for (ProgramParameterEnum param : programParameterEnums) {
                    stringBuilder.append(param.getLongParameterName()).append(" or ").append(param.getShortParameterName()).append(": ").append(param.getBeschreibung()).append("\n");
                }
                System.out.println(stringBuilder);
                System.exit(0);
            }
        }
    }

    private void joinThreads(Thread... threads) throws InterruptedException {

        for (Thread thread : threads) {
            thread.join();
        }
    }

    private CompressThread createCompressThread(String name, File inputOrder, File outputOrder, List<File> listOfFile, ParameterDto parameterDto) {

        CompressThread compressThread = new CompressThread(listOfFile, inputOrder, outputOrder, parameterDto);
        compressThread.setName(name);
        compressThread.start();
        return compressThread;
    }

    private List<File> createFileInputList(File inputImagePath) {

        File[] filesInInputFolder = inputImagePath.listFiles();
        if (filesInInputFolder == null) {
            throw new IllegalStateException("File Folder doesn't exist!!! ");
        }
        return Arrays.asList(filesInInputFolder);
    }

    private List<List<File>> divideListOfInputFile(List<File> fileInputList, int numberOfThreads) {

        List<List<File>> partitionFileInputLists = Lists.partition(fileInputList, numberOfThreads);
        return partitionFileInputLists;
    }

    private void createPdfOfOutputFiles(File outputImagePath, ParameterDto parameterDto) throws IOException {

        System.out.println();
        System.out.println("--------------------------------- Compress Image and create PDF --------------------------------------");

        CombineIntoPDF combineIntoPDF = CombineIntoPdfFactory.getInstance();
        File[] outputFiles = outputImagePath.listFiles();
        List<File> filesOutputList = Arrays.asList(outputFiles);
        List<String> stringList = filesOutputList.stream().map(file -> file.getPath()).collect(Collectors.toList());
        combineIntoPDF.combineImagesIntoPDF(parameterDto.getOutputPath() + "\\output.pdf", stringList);
    }

}
