package de.anna.imageConverter;

/*
java -jar xxxxxxxxx.jar --output=c:/dd/dd/ --input=c:/hh/hh
java -jar jpgConverter-1.0-SNAPSHOT-jar-with-dependencies.jar --input=d:/Workspace/temp/input --output=d:/Workspace/temp/output
java -jar jpgConverter-1.0-SNAPSHOT-jar-with-dependencies.jar -i=d:/Workspace/temp/input -o=d:/Workspace/temp/output
 */


import de.anna.imageConverter.exception.ParamValidationException;
import de.anna.imageConverter.exception.ParameterFormatException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Application application = new Application();
        try {
            application.start(args);
        } catch (ParameterFormatException | ParamValidationException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


}
