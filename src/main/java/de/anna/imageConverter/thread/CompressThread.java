package de.anna.imageConverter.thread;

import de.anna.imageConverter.config.ParameterDto;
import de.anna.imageConverter.factory.JpgConverterFactory;
import de.anna.imageConverter.service.JpgConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CompressThread extends Thread {

    private static final int SIZE_500_KB = 500 * 1024;
    private List<File> fileList;
    private File inputImagePath;
    private File outputPathAsFile;
    private ParameterDto parameterDto;

    public CompressThread(List<File> sublistOfFile, File aInputImagePath, File aOutputPathAsFile, ParameterDto aParameterDto) {
        fileList = sublistOfFile;
        inputImagePath = aInputImagePath;
        outputPathAsFile = aOutputPathAsFile;
        parameterDto = aParameterDto;
    }

    @Override
    public void run() {

        JpgConverter jpgConverter = JpgConverterFactory.getInstance();


        for (File file : fileList) {

            if (!file.isDirectory() && isImageFile(file)) {

                try {
                    BufferedImage inputImage = ImageIO.read(file);

                    double percent;
                    percent = parameterDto.getImageCompressionLarge();

                    if (file.length() < SIZE_500_KB) {
                        percent = parameterDto.getImageCompressionSmall();
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

    private static boolean isImageFile(File file) {
        return ((file.getName().endsWith(".PNG") || file.getName().endsWith(".png"))
                || (file.getName().endsWith(".JPG") || file.getName().endsWith(".jpg")));
    }
}
