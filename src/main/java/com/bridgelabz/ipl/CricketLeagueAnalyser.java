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

    List<Ipl2019RunsSheetCSV> iplRunSheetList =null;
    Map<String, IplRunSheetDAO> iplRunSheetDAOMap=null;

    public CricketLeagueAnalyser(){
        iplRunSheetDAOMap=new HashMap<String, IplRunSheetDAO>();
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

    public String getBattingAverageWiseSortedData() throws CricketLeagueAnalyserException {
        if(iplRunSheetDAOMap ==null || iplRunSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(average->average.average);
        List sortedDataByAverage= iplRunSheetDAOMap.values().stream().
                sorted(iplCSVComparator).collect(Collectors.toList());
        String sortedAverageDataInJson=new Gson().toJson(sortedDataByAverage);
        return sortedAverageDataInJson;
    }

    public String getStrikeRateWiseSortedData() throws CricketLeagueAnalyserException {
        if(iplRunSheetDAOMap ==null || iplRunSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(strikeRate->strikeRate.strikeRate);
        List sortedDataByAverage= iplRunSheetDAOMap.values().stream().
                sorted(iplCSVComparator).collect(Collectors.toList());
        String sortedAverageDataInJson=new Gson().toJson(sortedDataByAverage);
        return sortedAverageDataInJson;
    }

    public String getSixesHitWiseSortedData() throws CricketLeagueAnalyserException {
        if(iplRunSheetDAOMap ==null || iplRunSheetDAOMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        Comparator<IplRunSheetDAO> iplCSVComparator =Comparator.comparing(strikeRate->strikeRate.sixes);
        List sortedDataByAverage= iplRunSheetDAOMap.values().stream().
                sorted(iplCSVComparator).collect(Collectors.toList());
        String sortedAverageDataInJson=new Gson().toJson(sortedDataByAverage);
        return sortedAverageDataInJson;
    }
}