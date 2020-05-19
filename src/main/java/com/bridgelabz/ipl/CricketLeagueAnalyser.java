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

    Map<String, IplRunSheetDAO> iplRunSheetDAOMap=null;
    Map<String, IplWktsSheetDAO> iplWktsSheetDAOMap=null;

    public CricketLeagueAnalyser(){
        iplRunSheetDAOMap=new HashMap<String, IplRunSheetDAO>();
        iplWktsSheetDAOMap=new HashMap<String, IplWktsSheetDAO>();
    }

    public  int loadIplRunsSheetData(String csvFilePath) throws CricketLeagueAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Ipl2019RunsSheetCSV> csvIterator=csvBuilder.getCSVfileIterator(reader, Ipl2019RunsSheetCSV.class);
            Iterable<Ipl2019RunsSheetCSV> csvIterable=()->csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(iplRunsCSV -> iplRunSheetDAOMap.put(iplRunsCSV.player,new IplRunSheetDAO(iplRunsCSV)));
            return iplRunSheetDAOMap.size();
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
                    .forEach(iplWktsCSV -> iplWktsSheetDAOMap.put(iplWktsCSV.player,new IplWktsSheetDAO(iplWktsCSV)));
            return iplWktsSheetDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch(IOException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch(CSVBuilderException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
    }

    public String getBattingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparator = Comparator.comparing(ipl->ipl.average);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.strikeRate);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getSixesHitWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.sixes);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getFoursHitWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparator =Comparator.comparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparator);
    }

    public String getMaxSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<IplRunSheetDAO> iplRunSheetComparatorFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        return this.sortIplData(iplRunSheetComparatorSixes,iplRunSheetComparatorFours);
    }

    public String getBestStrikeRatewithSixesandFoursWiseSortedData() throws CricketLeagueAnalyserException {
        if(iplRunSheetDAOMap ==null || iplRunSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        Comparator<IplRunSheetDAO> iplRunSheetComparatorSixes =Comparator.comparing(ipl->ipl.sixes);
        Comparator<IplRunSheetDAO> iplRunSheetComparatorFours=iplRunSheetComparatorSixes.thenComparing(ipl->ipl.fours);
        Comparator<IplRunSheetDAO> iplRunSheetComparatorStrikeRate=iplRunSheetComparatorFours.thenComparing(ipl->ipl.strikeRate);
        List sortedData= iplRunSheetDAOMap.values().stream().
                sorted(iplRunSheetComparatorSixes).
                sorted(iplRunSheetComparatorFours).
                sorted(iplRunSheetComparatorStrikeRate).
                collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    private String sortIplData(Comparator<IplRunSheetDAO> iplRunSheetComparator) throws CricketLeagueAnalyserException {
        if(iplRunSheetDAOMap ==null || iplRunSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= iplRunSheetDAOMap.values().stream().
                sorted(iplRunSheetComparator).collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    private String sortIplData(Comparator<IplRunSheetDAO>... iplRunSheetComparator) throws CricketLeagueAnalyserException {
        if(iplRunSheetDAOMap ==null || iplRunSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= iplRunSheetDAOMap.values().stream().
                sorted(iplRunSheetComparator[0]).
                sorted(iplRunSheetComparator[1]).
                collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }

    public String getBestAverageWithStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparatorAverage =Comparator.comparing(ipl->ipl.average);
        Comparator<IplRunSheetDAO> iplRunSheetComparatorStrikeRate=iplRunSheetComparatorAverage.thenComparing(ipl->ipl.strikeRate);
        return this.sortIplData(iplRunSheetComparatorAverage,iplRunSheetComparatorStrikeRate);
    }

    public String getMaximumRunsWithBestAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplRunSheetDAO> iplRunSheetComparatorRuns =Comparator.comparing(ipl->ipl.runs);
        Comparator<IplRunSheetDAO> iplRunSheetComparatorAverage=iplRunSheetComparatorRuns.thenComparing(ipl->ipl.average);
        return this.sortIplData(iplRunSheetComparatorRuns,iplRunSheetComparatorAverage);
    }

    public String getBowlingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplWktsSheetDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.average);
        return this.sortIplWktsData(iplWktsSheetComparator);
    }

    public String getBowlingStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplWktsSheetDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.strikeRate);
        return this.sortIplWktsData(iplWktsSheetComparator);
    }

    public String getBowlingEconomyWiseSortedData() throws CricketLeagueAnalyserException {
        Comparator<IplWktsSheetDAO> iplWktsSheetComparator = Comparator.comparing(ipl->ipl.economy);
        return this.sortIplWktsData(iplWktsSheetComparator);
    }

    private String sortIplWktsData(Comparator<IplWktsSheetDAO> iplWktsSheetComparator) throws CricketLeagueAnalyserException {
        if(iplWktsSheetDAOMap ==null || iplWktsSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        List sortedData= iplWktsSheetDAOMap.values().stream().
                sorted(iplWktsSheetComparator).collect(Collectors.toList());
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }
}