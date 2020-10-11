package de.anna.imageConverter.factory;

import de.anna.imageConverter.service.JpgConverter;
import de.anna.imageConverter.service.impl.JpgConverterImpl;

public class JpgConverterFactory {

    public static JpgConverter getInstance() {
        return new JpgConverterImpl();
    }
}
