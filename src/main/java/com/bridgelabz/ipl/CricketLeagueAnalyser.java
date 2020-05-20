package com.bridgelabz.ipl;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {

    Map<String, Ipl2019DAO> iplRunsSheetMap =null;
    Map<String, Ipl2019DAO> iplWktsSheetMap=null;
    Map<String, Ipl2019DAO> iplRunsWktsSheetMap=null;
    public CricketLeagueAnalyser() {
        this.iplRunsSheetMap =new HashMap<String, Ipl2019DAO>();
        this.iplWktsSheetMap=new HashMap<String, Ipl2019DAO>();
        this.iplRunsWktsSheetMap=new HashMap<String, Ipl2019DAO>();
    }

    public  int loadIplRunsSheetData(String csvFilePath) throws CricketLeagueAnalyserException {
       iplRunsSheetMap=new IplLoader().loadIplRunsSheetData(Ipl2019RunsSheetCSV.class,csvFilePath);
       return iplRunsSheetMap.size();
    }

    public int loadIplWktsSheetData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplWktsSheetMap=new IplLoader().loadIplWktsSheetData(Ipl2019WktsSheetCSV.class,csvFilePath);
        return iplWktsSheetMap.size();
    }

    private Map<String, Ipl2019DAO> loadBothRunsandWktsSheet(Map<String,Ipl2019DAO> iplRunsSheetMap, Map<String,Ipl2019DAO> iplWktsSheetMap) {
        iplRunsWktsSheetMap.putAll(iplRunsSheetMap);
        iplRunsWktsSheetMap.putAll(iplWktsSheetMap);
        return iplRunsWktsSheetMap;
    }

    public String getBattingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparator = Comparator.comparing(ipl->ipl.battingAverage);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getSixesHitWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.sixes);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getFoursHitWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getMaxSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<Ipl2019DAO> iplRunSheetComparatorSixesFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparatorSixesFours);
    }

    public String getBestStrikeRatewithSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<Ipl2019DAO> iplRunSheetComparatorSixesFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        Comparator<Ipl2019DAO> iplRunSheetComparatorStrikeRateSixesFours=iplRunSheetComparatorSixesFours.thenComparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparatorStrikeRateSixesFours);
    }

    public String getBestAverageWithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparatorAverage =Comparator.comparing(ipl->ipl.battingAverage);
        Comparator<Ipl2019DAO> iplRunSheetComparatorAverageStrikeRate=iplRunSheetComparatorAverage.thenComparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparatorAverageStrikeRate);
    }

    public String getMaximumRunsWithBestAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparatorRuns =Comparator.comparing(ipl->ipl.battingRuns);
        Comparator<Ipl2019DAO> iplRunSheetComparatorRunsAverage=iplRunSheetComparatorRuns.thenComparing(ipl->ipl.battingAverage);
        return this.sortIplData(iplRunSheetComparatorRunsAverage);
    }

    public String getBowlingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getBowlingStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.bowlingStrikeRate);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getBowlingEconomyWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.economy);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getFiveWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.fiveWickets);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getFourWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.fourWickets);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getStrikeRateWith5wand4wWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparator5Wickets =Comparator.comparing(ipl->ipl.fiveWickets);
        Comparator<Ipl2019DAO> iplWktsSheetComparator5_4Wickets=iplWktsSheetComparator5Wickets.thenComparing(ipl->ipl.fourWickets);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorStrikeRate_5_4Wickets=iplWktsSheetComparator5_4Wickets.thenComparing(ipl->ipl.bowlingStrikeRate);
        return this.sortIplData(iplWktsSheetComparatorStrikeRate_5_4Wickets);
    }

    public String getBowlingAveragewithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparatorStrikeRate =Comparator.comparing(ipl->ipl.bowlingStrikeRate);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorAverageStrikeRate=iplWktsSheetComparatorStrikeRate.thenComparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparatorAverageStrikeRate);
    }

    public String getBowlingAveragewithWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparatorAverage =Comparator.comparing(ipl->ipl.bowlingAverage);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorAverageWickets=iplWktsSheetComparatorAverage.thenComparing(ipl->ipl.wickets);
        return this.sortIplData(iplWktsSheetComparatorAverageWickets);
    }

    public String getBowlingandBattingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        loadBothRunsandWktsSheet(iplRunsSheetMap,iplWktsSheetMap);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorBowlingAverage =Comparator.comparing(ipl->ipl.bowlingAverage);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorBowlingBattingAverage=iplWktsSheetComparatorBowlingAverage.thenComparing(ipl->ipl.battingAverage);
        return this.sortIplData(iplWktsSheetComparatorBowlingBattingAverage);
    }

    private String sortIplData(Comparator<Ipl2019DAO> iplRunSheetComparator) throws CricketLeagueAnalyserException {
        if(iplRunsSheetMap ==null || iplRunsSheetMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= iplRunsSheetMap.values().stream().
                sorted(iplRunSheetComparator).collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }
}