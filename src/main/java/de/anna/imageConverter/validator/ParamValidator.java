package de.anna.imageConverter.validator;

import de.anna.imageConverter.config.ParameterDto;
import de.anna.imageConverter.exception.ParamValidationException;
import de.anna.imageConverter.util.StringUtil;

public class ParamValidator {

    private static final Integer MIN_NUMBER_OF_THREADS = 1;
    private static final Integer MAX_NUMBER_OF_THREADS = 8;

    public static void validate(ParameterDto parameterDto) {

        if ((StringUtil.isEmpty(parameterDto.getInputPath())) || (StringUtil.isEmpty(parameterDto.getOutputPath()))) {
            throw new ParamValidationException("Please enter input or output path");
        }

        if (parameterDto.getNumberOfThreads() < MIN_NUMBER_OF_THREADS || parameterDto.getNumberOfThreads() > MAX_NUMBER_OF_THREADS) {
            String stringFormatException = String.format("The number of threads must be between %d und %d", MIN_NUMBER_OF_THREADS, MAX_NUMBER_OF_THREADS);
            throw new ParamValidationException(stringFormatException);
        }
    }
}
