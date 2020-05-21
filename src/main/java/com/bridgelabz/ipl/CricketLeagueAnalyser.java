package com.bridgelabz.ipl;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {

    Map<String, IplRecordDAO> iplDataMap;
    public IplAdapter iplAdapter;
    public IPLEntity iplEntity;

    public CricketLeagueAnalyser(IPLEntity iplEntity) {
        this.iplEntity=iplEntity;
    }

    public CricketLeagueAnalyser() {
        this.iplDataMap=new HashMap<>();
    }

    public void setIplAdapter(IplAdapter iplAdapter) {
        this.iplAdapter = iplAdapter;
    }

    public  int loadIplData(String... csvFilePath) throws CricketLeagueAnalyserException {
       iplDataMap=new IplAdapterFactory().cricketleagueFactory(iplEntity,csvFilePath);
       return iplDataMap.size();
    }

    public String getBattingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparator = Comparator.comparing(ipl->ipl.battingAverage);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getSixesHitWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.sixes);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getFoursHitWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getMaxSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<IplRecordDAO> iplRunSheetComparatorSixesFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparatorSixesFours);
    }

    public String getBestStrikeRatewithSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<IplRecordDAO> iplRunSheetComparatorSixesFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        Comparator<IplRecordDAO> iplRunSheetComparatorStrikeRateSixesFours=iplRunSheetComparatorSixesFours.thenComparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparatorStrikeRateSixesFours);
    }

    public String getBestAverageWithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparatorAverage =Comparator.comparing(ipl->ipl.battingAverage);
        Comparator<IplRecordDAO> iplRunSheetComparatorAverageStrikeRate=iplRunSheetComparatorAverage.thenComparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparatorAverageStrikeRate);
    }

    public String getMaximumRunsWithBestAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplRunSheetComparatorRuns =Comparator.comparing(ipl->ipl.battingRuns);
        Comparator<IplRecordDAO> iplRunSheetComparatorRunsAverage=iplRunSheetComparatorRuns.thenComparing(ipl->ipl.battingAverage);
        return this.sortIplData(iplRunSheetComparatorRunsAverage);
    }

    public String getBowlingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getBowlingStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.bowlingStrikeRate);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getBowlingEconomyWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.economy);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getFiveWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.fiveWickets);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getFourWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.fourWickets);
        return this.sortIplData(iplWktsSheetComparator);
    }

    public String getStrikeRateWith5wand4wWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparatorStrikeRate =Comparator.comparing(ipl->ipl.bowlingStrikeRate);
        Comparator<IplRecordDAO> iplWktsSheetComparatorStrikeRate5Wickets=iplWktsSheetComparatorStrikeRate.thenComparing(ipl->ipl.fiveWickets);
        Comparator<IplRecordDAO> iplWktsSheetComparatorStrikeRate_5_4Wickets=iplWktsSheetComparatorStrikeRate5Wickets.thenComparing(ipl->ipl.fourWickets);
        return this.sortIplData(iplWktsSheetComparatorStrikeRate_5_4Wickets);
    }

    public String getBowlingAveragewithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparatorStrikeRate =Comparator.comparing(ipl->ipl.bowlingStrikeRate);
        Comparator<IplRecordDAO> iplWktsSheetComparatorAverageStrikeRate=iplWktsSheetComparatorStrikeRate.thenComparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparatorAverageStrikeRate);
    }

    public String getBowlingAveragewithWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparatorWickets =Comparator.comparing(ipl->ipl.wickets);
        Comparator<IplRecordDAO> iplWktsSheetComparatorAverageWickets=iplWktsSheetComparatorWickets.thenComparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparatorAverageWickets);
    }

    public String getBowlingandBattingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplWktsSheetComparatorBattingAverage =Comparator.comparing(ipl->ipl.battingAverage);
        Comparator<IplRecordDAO> iplWktsSheetComparatorBowlingBattingAverage=iplWktsSheetComparatorBattingAverage.thenComparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparatorBowlingBattingAverage);
    }

    public String getRunsandWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplComparatorRuns =Comparator.comparing(ipl->ipl.battingRuns);
        Comparator<IplRecordDAO> iplComparatorRunsWickets=iplComparatorRuns.thenComparing(ipl->ipl.wickets);
        return this.sortIplData(iplComparatorRunsWickets);
    }

    private String sortIplData(Comparator<IplRecordDAO> iplRunSheetComparator) throws CricketLeagueAnalyserException {
        if(iplDataMap ==null || iplDataMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= iplDataMap.values().stream().
                sorted(iplRunSheetComparator).collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }
}