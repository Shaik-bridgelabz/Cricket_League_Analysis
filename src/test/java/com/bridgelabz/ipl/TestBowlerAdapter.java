package com.bridgelabz.ipl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TestBowlerAdapter {

    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/IPL2019.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_EXTENSION = "./src/test/resources/IPL2019FactsheetMostWkts.jpg";

    @Test
    public void givenIPLMostWktsCSVFile_whenLoaded_shouldReturnNumberOfRecords() {
        try {
            BowlerAdapter bowlerAdapter = new BowlerAdapter();
            Map<String, IplRecordDAO> numOfRecords =bowlerAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(86, numOfRecords.size());
        }catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenImproperFileName_shouldReturnException() {
        try {
            BowlerAdapter bowlerAdapter = new BowlerAdapter();
            bowlerAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_FILE);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperFileExtension_shouldReturnException() {
        try {
            BowlerAdapter bowlerAdapter = new BowlerAdapter();
            bowlerAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_EXTENSION);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperHeader_shouldReturnException() {
        try {
            BowlerAdapter bowlerAdapter = new BowlerAdapter();
            bowlerAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperDelimiter_shouldReturnException() {
        try {
            BowlerAdapter bowlerAdapter = new BowlerAdapter();
            bowlerAdapter.loadIPLData(IPLEntity.BOWLING, IPL_MOST_WKTS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.INCORRECT_DELIMITER_EXCEPTION,e.type);
        }
    }
}