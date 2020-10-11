package de.anna.imageConverter.config;

import de.anna.imageConverter.enums.ProgramParameterEnum;
import de.anna.imageConverter.exception.ParameterFormatException;


public class ProgrammParameterParser {

    private static final Integer DEFAULT_NUMBER_OF_THREADS = (Integer) ProgramParameterEnum.THREADS.getDefaultValue();
    private static final Double IMAGE_COMPRESSION_SMALL = (Double) ProgramParameterEnum.IMAGE_COMPRESSION_SMALL.getDefaultValue();
    private static final Double IMAGE_COMPRESSION_LARGE = (Double) ProgramParameterEnum.IMAGE_COMPRESSION_LARGE.getDefaultValue();

    public ParameterDto parse(String[] args) throws ParameterFormatException {

        ParameterDto parameterDto = new ParameterDto();

        try {
            for (String arg : args) {
                if (arg.startsWith(ProgramParameterEnum.INPUT.getLongParameterName()) || arg.startsWith(ProgramParameterEnum.INPUT.getShortParameterName())) {
                    parameterDto.setInputPath(getPathFromParam(arg));
                } else if (arg.startsWith(ProgramParameterEnum.OUTPUT.getLongParameterName()) || arg.startsWith(ProgramParameterEnum.OUTPUT.getShortParameterName())) {
                    parameterDto.setOutputPath(getPathFromParam(arg));
                } else if (arg.startsWith(ProgramParameterEnum.THREADS.getLongParameterName()) || arg.startsWith(ProgramParameterEnum.THREADS.getShortParameterName())) {
                    parameterDto.setNumberOfThreads(Integer.valueOf(getPathFromParam(arg)));
                } else if (arg.startsWith(ProgramParameterEnum.IMAGE_COMPRESSION_LARGE.getLongParameterName()) || arg.startsWith(ProgramParameterEnum.IMAGE_COMPRESSION_LARGE.getShortParameterName())) {
                    parameterDto.setImageCompressionLarge(Double.valueOf(getPathFromParam(arg)));
                } else if (arg.startsWith(ProgramParameterEnum.IMAGE_COMPRESSION_SMALL.getLongParameterName()) || arg.startsWith(ProgramParameterEnum.IMAGE_COMPRESSION_SMALL.getShortParameterName())) {
                    parameterDto.setImageCompressionSmall(Double.valueOf(getPathFromParam(arg)));
                }
            }

        } catch (Exception exception) {
            throw new ParameterFormatException("Parameter format is false. ", exception);
        }

        return setDefaultValueOfArgWhenNull(parameterDto);
    }

    private ParameterDto setDefaultValueOfArgWhenNull(ParameterDto parameterDto) {

        if (parameterDto.getNumberOfThreads() == null) {
            parameterDto.setNumberOfThreads(DEFAULT_NUMBER_OF_THREADS);
        }
        if (parameterDto.getImageCompressionSmall() == null) {
            parameterDto.setImageCompressionSmall(IMAGE_COMPRESSION_SMALL);
        }
        if (parameterDto.getImageCompressionLarge() == null) {
            parameterDto.setImageCompressionLarge(IMAGE_COMPRESSION_LARGE);
        }
        return parameterDto;
    }

    private String getPathFromParam(String arg) {
        return arg.split("=")[1];
    }
}
