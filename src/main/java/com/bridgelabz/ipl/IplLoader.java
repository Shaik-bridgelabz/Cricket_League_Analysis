package com.bridgelabz.ipl;

import com.Csv.CSVBuilderException;
import com.Csv.CSVBuilderFactory;
import com.Csv.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IplLoader {

    public Map<String, Ipl2019DAO> loadIplRunsSheetData(Class<Ipl2019RunsSheetCSV> csvClass, String csvFilePath) throws CricketLeagueAnalyserException {
    Map<String,Ipl2019DAO> iplRunsMap=new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Ipl2019RunsSheetCSV> csvIterator=csvBuilder.getCSVfileIterator(reader,csvClass);
            Iterable<Ipl2019RunsSheetCSV> csvIterable=()->csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(iplRunsCSV -> iplRunsMap.
                            put(iplRunsCSV.player,new Ipl2019DAO(iplRunsCSV)));
            return iplRunsMap;
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch(IOException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch(CSVBuilderException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
    }

    public Map<String, Ipl2019DAO> loadIplWktsSheetData(Class<Ipl2019WktsSheetCSV> csvClass, String csvFilePath) throws CricketLeagueAnalyserException {
    Map<String,Ipl2019DAO> iplWktsMap=new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<Ipl2019WktsSheetCSV> csvIterator=csvBuilder.getCSVfileIterator(reader,csvClass);
            Iterable<Ipl2019WktsSheetCSV> csvIterable=()->csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(iplWktsCSV -> iplWktsMap.
                            put(iplWktsCSV.player,new Ipl2019DAO(iplWktsCSV)));
            return iplWktsMap;
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch(IOException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch(CSVBuilderException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
    }
}
