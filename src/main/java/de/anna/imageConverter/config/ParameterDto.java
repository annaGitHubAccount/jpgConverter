package de.anna.imageConverter.config;

public class ParameterDto {

    private String inputPath = null;
    private String outputPath = null;
    private Integer numberOfThreads = null;
    private Double imageCompressionLarge = null;
    private Double imageCompressionSmall = null;

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public Integer getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(Integer numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public Double getImageCompressionLarge() {
        return imageCompressionLarge;
    }

    public void setImageCompressionLarge(Double imageCompressionLarge) {
        this.imageCompressionLarge = imageCompressionLarge;
    }

    public Double getImageCompressionSmall() {
        return imageCompressionSmall;
    }

    public void setImageCompressionSmall(Double imageCompressionSmall) {
        this.imageCompressionSmall = imageCompressionSmall;
    }
}
