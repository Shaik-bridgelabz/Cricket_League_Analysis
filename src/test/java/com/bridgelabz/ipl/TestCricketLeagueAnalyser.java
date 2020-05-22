package com.bridgelabz.ipl;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class TestCricketLeagueAnalyser {
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE = "./src/test/resources/IPL2019.csv";
    private static final String IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION = "./src/test/resources/IPL2019FactsheetMostRuns.jpg";
    private static final String IPL_MOST_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsCSVFile_whenLoaded_shouldReturnNumberOfRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(IPLEntity.BATTING);
            int numOfRecords = cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        }catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperFileName_shouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_FILE);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperFileExtension_shouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH_FOR_WRONG_EXTENSION);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperHeader_shouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.INCORRECT_DELIMITER_HEADER_EXCEPTION,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenImproperDelimiter_shouldReturnException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
        }catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.TypeOfException.INCORRECT_DELIMITER_EXCEPTION,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnBattingAverage_shouldReturnBestAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BATTING_AVERAGE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("MS Dhoni",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnBattingAverage_shouldReturnLeastAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BATTING_AVERAGE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Ishant Sharma",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnStrikeRate_shouldReturnBestStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Ishant Sharma",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnStrikeRate_shouldReturnLeastStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Bhuvneshwar Kumar",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnSixes_shouldReturnPlayerWithMaximumSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.SIX);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Andre Russell",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnSixes_shouldReturnPlayerWithLeastSixes() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.SIX);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Kuldeep Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnFours_shouldReturnPlayerWithMaximumFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FOURS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Shikhar Dhawan",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnFours_shouldReturnPlayerWithLeastFours() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FOURS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnSixesandFours_shouldReturnPlayerWithMaximumBoundaries() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.SIX_AND_FOURS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Andre Russell",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnSixesandFours_shouldReturnPlayerWithLeastBoundaries() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.SIX_AND_FOURS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnStrikeRateWithSixesandFours_shouldReturnPlayerWithMaximumStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.SIX_AND_FOURS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Andre Russell",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnStrikeRateWithSixesandFours_shouldReturnPlayerWithLeastStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.SIX_AND_FOURS_WITH_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Shakib Al Hasan",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnBestAverageandStrikeRate_shouldReturnPlayerWithMaximumAverageandStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BAT_STATS_AVG_WITH_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("MS Dhoni",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnBestAverageandStrikeRate_shouldReturnPlayerWithLeastAverageandStrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BAT_STATS_AVG_WITH_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnMaximumRunsWithBestAverage_shouldReturnPlayerWithMaximumRunsandAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.RUN_WITH_AVG);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Shreyas Iyer",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostRunsCSVFile_whenSortedOnMaximumRunsWithBestAverage_shouldReturnPlayerWithLeastRunsandAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.RUN_WITH_AVG);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Marcus Stoinis",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenLoaded_shouldReturnNumberOfRecords() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(IPLEntity.BOWLING);
            int numOfRecords = cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(86, numOfRecords);
        }catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnBowlingAverage_shouldReturnBestAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWLING_AVERAGE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnBowlingAverage_shouldReturnLeastAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWLING_AVERAGE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Anukul Roy",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnStrikeRate_shouldReturnLeastStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWLING_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnStrikeRate_shouldReturnBestStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWLING_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Alzarri Joseph",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnEconomy_shouldReturnBestEconomyPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.ECONOMY);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Ben Cutting",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnEconomy_shouldReturnLeastEconomyPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.ECONOMY);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Anukul Roy",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOn5Wickets_shouldReturnMaximum5WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FIVE_WKTS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Alzarri Joseph",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOn5Wickets_shouldReturnLeast5WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FIVE_WKTS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Umesh Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOn4Wickets_shouldReturnMaximum4WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FOUR_WKTS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Kagiso Rabada",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOn4Wickets_shouldReturnLeast4WicketsPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FOUR_WKTS);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Umesh Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnStrikeRateWith5Wktsand4Wkts_shouldReturnBestStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FIVEWKT_FOURWKT_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Alzarri Joseph",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnStrikeRateWith5Wktsand4Wkts_shouldReturnLeastStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.FIVEWKT_FOURWKT_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Umesh Yadav",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnAveragewithStrikeRate_shouldReturnBestAverageandStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWL_STATS_AVG_WITH_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Krishnappa Gowtham",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnAveragewithStrikeRate_shouldReturnLeastAverageandStrikeRatePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWL_STATS_AVG_WITH_STRIKERATE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Anukul Roy",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnWicketswithAverage_shouldReturnMaximumWicketswithBestAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWL_STATS_WKT_WITH_AVG);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Imran Tahir",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLMostWktsCSVFile_whenSortedOnWicketswithAverage_shouldReturnLeastWicketsandAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BOWLING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BOWL_STATS_WKT_WITH_AVG);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Anukul Roy",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsandWktsFile_whenSortedOnBowlingandBattingAverages_shouldReturnBestAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH,IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BATTING_BOWLING_AVERAGE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("MS Dhoni",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsandWktsFile_whenSortedOnBowlingandBattingAverages_shouldReturnLeastAveragePlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH,IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.BATTING_BOWLING_AVERAGE);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Harpreet Brar",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsandWktsFile_whenSortedOnRunsandWickets_shouldReturnBestAllRounderPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH,IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.IPL_BEST_ALLROUNDER);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("David Warner",sortedAverageData[sortedAverageData.length-1].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsandWktsFile_whenSortedOnRunsandWickets_shouldReturnLeastAllRounderPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser=new CricketLeagueAnalyser(IPLEntity.BATTING);
            cricketLeagueAnalyser.loadIplData(IPL_MOST_RUNS_CSV_FILE_PATH,IPL_MOST_WKTS_CSV_FILE_PATH);
            String sortedData=cricketLeagueAnalyser.getFieldWiseSortedData(SortByField.Parameter.IPL_BEST_ALLROUNDER);
            IplRecordDAO[] sortedAverageData=new Gson().fromJson(sortedData, IplRecordDAO[].class);
            Assert.assertEquals("Tim Southee",sortedAverageData[0].player);
        }catch (CricketLeagueAnalyserException e) {
        }
    }
}