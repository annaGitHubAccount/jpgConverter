package de.anna.imageConverter.service;

import java.io.IOException;
import java.util.List;

public interface CombineIntoPDF {

    void combineImagesIntoPDF(String pdfPath, List<String> inputDirsAndFiles) throws IOException;
}
