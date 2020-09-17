package de.anna.factory;

import de.anna.service.CombineIntoPDF;
import de.anna.service.impl.CombineIntoPDFImpl;

public class CombineIntoPdfFactory {

    public static CombineIntoPDF getInstance(){
        return new CombineIntoPDFImpl();
    }
}
