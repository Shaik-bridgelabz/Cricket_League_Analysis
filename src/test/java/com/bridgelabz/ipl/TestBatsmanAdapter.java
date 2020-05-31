package com.bridgelabz.ipl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TestBatsmanAdapter {

    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/IPL2019.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION = "./src/test/resources/IPL2019FactsheetMostRuns.jpg";

    @Test
    public void givenIPLMostRunsCSVFile_whenLoaded_shouldReturnNumberOfRecords() {
        try {
            BatsmenAdapter batsmenAdapter = new BatsmenAdapter();
            Map<String, IplRecordDAO> numOfRecords =batsmenAdapter.loadIPLData(IPLEntity.BATTING,IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords.size());
        }catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperFileName_shouldReturnException() {
        try {
            BatsmenAdapter batsmenAdapter = new BatsmenAdapter();
            batsmenAdapter.loadIPLData(IPLEntity.BATTING,IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperFileExtension_shouldReturnException() {
        try {
            BatsmenAdapter batsmenAdapter = new BatsmenAdapter();
            batsmenAdapter.loadIPLData(IPLEntity.BATTING,IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperHeader_shouldReturnException() {
        try {
            BatsmenAdapter batsmenAdapter = new BatsmenAdapter();
            batsmenAdapter.loadIPLData(IPLEntity.BATTING,IPL_MOST_RUNS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperDelimiter_shouldReturnException() {
        try {
            BatsmenAdapter batsmenAdapter = new BatsmenAdapter();
            batsmenAdapter.loadIPLData(IPLEntity.BATTING,IPL_MOST_RUNS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.INCORRECT_DELIMITER_EXCEPTION,e.type);
        }
    }
}
