package de.anna.service;

import java.io.IOException;

public interface JpgConverter {

    void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) throws IOException;
}
