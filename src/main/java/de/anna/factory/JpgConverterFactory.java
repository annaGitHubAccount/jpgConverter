package de.anna.factory;

import de.anna.service.JpgConverter;
import de.anna.service.impl.JpgConverterImpl;

public class JpgConverterFactory {

    public static JpgConverter getInstance() {
        return new JpgConverterImpl();
    }
}
