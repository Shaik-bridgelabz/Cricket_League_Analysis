package com.bridgelabz.ipl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCricketLeagueAnalyser {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/IPL2019.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION = "./src/test/resources/IPL2019FactsheetMostRuns.jpg";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/IPL2019.csv";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_EXTENSION = "./src/test/resources/IPL2019FactsheetMostWkts.jpg";

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

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_StrikeRateWithSixesandFours_ShouldReturn_PlayerWithMaximumStrikeRateinBoundaries() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBestStrikeRatewithSixesandFoursWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Andre Russell",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_StrikeRateWithSixesandFours_ShouldReturn_PlayerWithLeastStrikeRateinBoundaries() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBestStrikeRatewithSixesandFoursWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Shakib Al Hasan",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_BestAverage_and_StrikeRate_ShouldReturn_PlayerWithMaximumAverageandStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBestAverageWithStrikeRateWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("MS Dhoni",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_BestAverage_and_StrikeRate_ShouldReturn_PlayerWithLeastAverageandStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBestAverageWithStrikeRateWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_MaximumRunsWithBestAverage_ShouldReturn_PlayerWithMaximumRunsandAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getMaximumRunsWithBestAverageWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("David Warner",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOn_MaximumRunsWithBestAverage_ShouldReturn_PlayerWithLeastRunsandAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplRunsSheetData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getMaximumRunsWithBestAverageWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_ShouldReturn_NumberOfRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            int numOfRecords = cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(99, numOfRecords);
        }catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenImproperFileName_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_FILE);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenImproperFileExtension_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH_FOR_WRONG_EXTENSION);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenImproperHeader_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenImproperDelimiter_ShouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOnBowlingAverage_ShouldReturn_BestAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBowlingAverageWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOnBowlingAverage_ShouldReturn_LeastAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBowlingAverageWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Suresh Raina",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOnStrikeRate_ShouldReturn_BestStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBowlingStrikeRateWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOnStrikeRate_ShouldReturn_LeastStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBowlingStrikeRateWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Suresh Raina",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOnEconomy_ShouldReturn_BestEconomyPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBowlingEconomyWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Ben Cutting",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOnEconomy_ShouldReturn_LeastEconomyPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getBowlingEconomyWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Shivam Dube",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOn5Wickets_ShouldReturn_Maximum5WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFiveWicketsWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Alzarri Joseph",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOn5Wickets_ShouldReturn_Least5WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFiveWicketsWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Umesh Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOn4Wickets_ShouldReturn_Maximum4WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFourWicketsWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Kagiso Rabada",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_WhenSortedOn4Wickets_ShouldReturn_Least4WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser();
            cricketLeagueAnalyser.loadIplWktsSheetData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFourWicketsWiseSortedData();
            IplRunSheetDAO[] sortedAverageData=new Gson().fromJson(sortedData,IplRunSheetDAO[].class);
            Assert.assertEquals("Umesh Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }
}
