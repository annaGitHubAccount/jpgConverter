package de.anna.thread;

import de.anna.factory.JpgConverterFactory;
import de.anna.service.JpgConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CompressThread extends Thread {

    private static final int SIZE_500_KB = 500 * 1024;
    List<File> fileList;
    File inputImagePath;
    File outputPathAsFile;

    public CompressThread(List<File> sublistOfFile, File aInputImagePath, File aOutputPathAsFile) {
        fileList = sublistOfFile;
        inputImagePath = aInputImagePath;
        outputPathAsFile = aOutputPathAsFile;

    }

    @Override
    public void run() {

        JpgConverter jpgConverter = JpgConverterFactory.getInstance();


        for (File file : fileList) {

            if (!file.isDirectory() && isJpgFile(file)) {

                try {
                    BufferedImage inputImage = ImageIO.read(file);

                    double percent = 0.3;

                    if (file.length() < SIZE_500_KB) {
                        percent = 0.5;
                    }

                    int scaledWidth = (int) (inputImage.getWidth() * percent);
                    int scaledHeight = (int) (inputImage.getHeight() * percent);

                    jpgConverter.resize(inputImagePath.getPath() + "/" + file.getName(),
                            outputPathAsFile.getPath() + "/" + file.getName(), scaledWidth, scaledHeight);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isJpgFile(File file) {
        return file.getName().endsWith(".PNG") || file.getName().endsWith(".png");
    }
}
