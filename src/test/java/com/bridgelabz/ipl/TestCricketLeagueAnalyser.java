package com.bridgelabz.ipl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCricketLeagueAnalyser {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/IPL2019.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION = "./src/test/resources/IPL2019FactsheetMostRuns.jpg";

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_NumberOfRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            int numOfRecords = cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        }catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenImproperFileName_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenImproperFileExtension_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenImproperHeader_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenImproperDelimiter_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnBattingAverage_ShouldReturn_BestAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBattingAverageWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("MS Dhoni",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnBattingAverage_ShouldReturn_LeastAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBattingAverageWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Ishant Sharma",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnStrikeRate_ShouldReturn_BestStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getStrikeRateWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Ishant Sharma",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnStrikeRate_ShouldReturn_LeastStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getStrikeRateWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Bhuvneshwar Kumar",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnSixes_ShouldReturn_PlayerWithMaximumSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getSixesHitWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Andre Russell",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnSixes_ShouldReturn_PlayerWithLeastSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getSixesHitWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Kuldeep Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnFours_ShouldReturn_PlayerWithMaximumFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFoursHitWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Shikhar Dhawan",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnFours_ShouldReturn_PlayerWithLeastFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFoursHitWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_SixesandFours_ShouldReturn_PlayerWithMaximumBoundaries() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getMaxSixesandFoursWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Andre Russell",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_SixesandFours_ShouldReturn_PlayerWithLeastBoundaries() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getMaxSixesandFoursWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }
}
