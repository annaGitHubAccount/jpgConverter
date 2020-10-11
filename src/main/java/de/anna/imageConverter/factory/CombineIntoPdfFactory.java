package de.anna.imageConverter.factory;

import de.anna.imageConverter.service.CombineIntoPDF;
import de.anna.imageConverter.service.impl.CombineIntoPDFImpl;

public class CombineIntoPdfFactory {

    public static CombineIntoPDF getInstance(){
        return new CombineIntoPDFImpl();
    }
}
