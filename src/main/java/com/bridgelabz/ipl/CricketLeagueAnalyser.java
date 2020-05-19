package com.bridgelabz.ipl;

import com.Csv.CSVBuilderException;
import com.Csv.CSVBuilderFactory;
import com.Csv.ICSVBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {

    Map<String, Ipl2019DAO> ipl2019DAOMap =null;

    public CricketLeagueAnalyser() {
        this.ipl2019DAOMap =new HashMap<String, Ipl2019DAO>();
    }

    public  int loadIplRunsSheetData(String csvFilePath) throws CricketLeagueAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Ipl2019RunsSheetCSV> csvIterator=csvBuilder.getCSVfileIterator(reader, Ipl2019RunsSheetCSV.class);
            Iterable<Ipl2019RunsSheetCSV> csvIterable=()->csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(iplRunsCSV -> ipl2019DAOMap.
                            put(iplRunsCSV.player,new Ipl2019DAO(iplRunsCSV)));
            return ipl2019DAOMap.size();
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch(IOException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch(CSVBuilderException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
    }

    public int loadIplWktsSheetData(String csvFilePath) throws CricketLeagueAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Ipl2019WktsSheetCSV> csvIterator=csvBuilder.getCSVfileIterator(reader, Ipl2019WktsSheetCSV.class);
            Iterable<Ipl2019WktsSheetCSV> csvIterable=()->csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(iplWktsCSV -> ipl2019DAOMap.
                            put(iplWktsCSV.player,new Ipl2019DAO(iplWktsCSV)));
            return ipl2019DAOMap.size();
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch(IOException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch(CSVBuilderException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
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
        Comparator<Ipl2019DAO> iplRunSheetComparatorFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparatorSixes,iplRunSheetComparatorFours);
    }

    public String getBestStrikeRatewithSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        if(ipl2019DAOMap ==null || ipl2019DAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        Comparator<Ipl2019DAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<Ipl2019DAO> iplRunSheetComparatorFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        Comparator<Ipl2019DAO> iplRunSheetComparatorStrikeRate=iplRunSheetComparatorFours.thenComparing(ipl->ipl.battingStrikeRate);
        List sortedData= ipl2019DAOMap.values().stream().
                sorted(iplRunSheetComparatorSixes).
                sorted(iplRunSheetComparatorFours).
                sorted(iplRunSheetComparatorStrikeRate).
                collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    public String getBestAverageWithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparatorAverage =Comparator.comparing(ipl->ipl.battingAverage);
        Comparator<Ipl2019DAO> iplRunSheetComparatorStrikeRate=iplRunSheetComparatorAverage.thenComparing(ipl->ipl.battingStrikeRate);
        return this.sortIplData(iplRunSheetComparatorAverage,iplRunSheetComparatorStrikeRate);
    }

    public String getMaximumRunsWithBestAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplRunSheetComparatorRuns =Comparator.comparing(ipl->ipl.runs);
        Comparator<Ipl2019DAO> iplRunSheetComparatorAverage=iplRunSheetComparatorRuns.thenComparing(ipl->ipl.battingAverage);
        return this.sortIplData(iplRunSheetComparatorRuns,iplRunSheetComparatorAverage);
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
        if(ipl2019DAOMap ==null || ipl2019DAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        Comparator<Ipl2019DAO> iplWktsSheetComparator5Wickets =Comparator.comparing(ipl->ipl.fiveWickets);
        Comparator<Ipl2019DAO> iplWktsSheetComparator4Wickets=iplWktsSheetComparator5Wickets.thenComparing(ipl->ipl.fourWickets);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorStrikeRate=iplWktsSheetComparator4Wickets.thenComparing(ipl->ipl.bowlingStrikeRate);
        List sortedData= ipl2019DAOMap.values().stream().
                sorted(iplWktsSheetComparator5Wickets).
                sorted(iplWktsSheetComparator4Wickets).
                sorted(iplWktsSheetComparatorStrikeRate).
                collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    public String getBowlingAveragewithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparatorStrikeRate =Comparator.comparing(ipl->ipl.bowlingStrikeRate);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorAverage=iplWktsSheetComparatorStrikeRate.thenComparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparatorStrikeRate,iplWktsSheetComparatorAverage);
    }


    public String getBowlingAveragewithWicketsWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<Ipl2019DAO> iplWktsSheetComparatorWickets =Comparator.comparing(ipl->ipl.wickets);
        Comparator<Ipl2019DAO> iplWktsSheetComparatorAverage=iplWktsSheetComparatorWickets.thenComparing(ipl->ipl.bowlingAverage);
        return this.sortIplData(iplWktsSheetComparatorWickets,iplWktsSheetComparatorAverage);
    }

    private String sortIplData(Comparator<Ipl2019DAO> iplRunSheetComparator) throws CricketLeagueAnalyserException {
        if(ipl2019DAOMap ==null || ipl2019DAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= ipl2019DAOMap.values().stream().
                sorted(iplRunSheetComparator).collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    private String sortIplData(Comparator<Ipl2019DAO>... iplRunSheetComparator) throws CricketLeagueAnalyserException {
        if(ipl2019DAOMap ==null || ipl2019DAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= ipl2019DAOMap.values().stream().
                sorted(iplRunSheetComparator[0]).
                sorted(iplRunSheetComparator[1]).
                collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }
}