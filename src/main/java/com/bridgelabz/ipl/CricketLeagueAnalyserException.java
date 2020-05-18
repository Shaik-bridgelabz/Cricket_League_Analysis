package com.bridgelabz.ipl;

public class CricketLeagueAnalyserException extends Exception {
    public enum TypeOfException {
        NO_FILE_FOUND,
        INCORRECT_DELIMITER_EXCEPTION,
        INCORRECT_DELIMITER_HEADER_EXCEPTION,
        UNABLE_TO_PARSE,
        NO_DATA_FOUND;
    }

    public TypeOfException type;

    public CricketLeagueAnalyserException(TypeOfException type,String message) {
        super(message);
        this.type=type;
    }

    public CricketLeagueAnalyserException(String message,String name) {
        super(message);
        this.type=TypeOfException.valueOf(name);
    }
}
