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

public abstract class IplAdapter {

    public abstract Map<String, IplRecordDAO> loadIPLData(IPLEntity iplEntity, String... csvFilePath) throws CricketLeagueAnalyserException;

    public <T> Map<String, IplRecordDAO> loadIPLData(Class<T> iplCSVClass, String... csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IplRecordDAO> iplRecordDAOMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
                ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                Iterator csvIterator = csvBuilder.getCSVfileIterator(reader, iplCSVClass);
                Iterable<T> csvIterable = () -> csvIterator;
            if (iplCSVClass.getName().contains("IplRunsSheetCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplRunsSheetCSV.class::cast)
                        .forEach(IplRunsSheetCSV -> iplRecordDAOMap.
                                put(IplRunsSheetCSV.player, new IplRecordDAO(IplRunsSheetCSV)));
            } else if (iplCSVClass.getName().contains("IplWktsSheetCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IplWktsSheetCSV.class::cast)
                        .filter(IplWktsSheetCSV -> IplWktsSheetCSV.average != 0.0)
                        .forEach(IplWktsSheetCSV -> iplRecordDAOMap.put(IplWktsSheetCSV.player, new IplRecordDAO(IplWktsSheetCSV)));
            }
            return iplRecordDAOMap;
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
    }
}