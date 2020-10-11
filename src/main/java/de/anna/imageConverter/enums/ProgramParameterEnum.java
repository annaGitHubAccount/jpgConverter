package de.anna.imageConverter.enums;

public enum ProgramParameterEnum {

    HELP("--help", "-h", null, "It shows help parameter"),
    INPUT("--input", "-i", null, "It shows input parameter"),
    OUTPUT("--output", "-o", null, "It shows output parameter"),
    THREADS("--threads", "-t", 2, "It shows threads parameter"),
    IMAGE_COMPRESSION_LARGE("--imageCompressionLarge", "-l", 0.3, "It shows imageCompressionLarge parameter"),
    IMAGE_COMPRESSION_SMALL("--imageCompressionSmall", "-s", 0.5, "It shows imageCompressionSmall parameter");

    private final String longParameterName;
    private final String shortParameterName;
    private final String beschreibung;
    private final Object defaultValue;

    ProgramParameterEnum(String longParameterName, String shortParameterName, Object defaultValue, String beschreibung) {
        this.longParameterName = longParameterName;
        this.shortParameterName = shortParameterName;
        this.beschreibung = beschreibung;
        this.defaultValue = defaultValue;
    }


    public String getLongParameterName() {
        return longParameterName;
    }

    public String getShortParameterName() {
        return shortParameterName;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}
