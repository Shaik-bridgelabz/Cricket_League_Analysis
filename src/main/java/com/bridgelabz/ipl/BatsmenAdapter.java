package com.bridgelabz.ipl;

import com.Csv.CSVBuilderException;
import com.Csv.CSVBuilderFactory;
import com.Csv.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmenAdapter extends IplAdapter {
    @Override
    public Map<String, IplRecordDAO> loadIPLData(IPLEntity iplEntity, String... csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IplRecordDAO> recordDAOMap = super.loadIPLData(IplRunsSheetCSV.class, csvFilePath[0]);
        if (csvFilePath.length == 2)
            this.loadMostWKTSCSV(recordDAOMap, csvFilePath[1]);
        return recordDAOMap;
    }

    private int loadMostWKTSCSV(Map<String, IplRecordDAO> recordDAOMap, String csvFilePath) throws CricketLeagueAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplWktsSheetCSV> wktsCSVIterator = csvBuilder.getCSVfileIterator(reader, IplWktsSheetCSV.class);
            Iterable<IplWktsSheetCSV> wktsCSVS = () -> wktsCSVIterator;
            StreamSupport.stream(wktsCSVS.spliterator(), false)
                    .filter(csvIPL -> recordDAOMap.get(csvIPL.player) != null)
                    .forEach(mostWktsCSV -> {
                        recordDAOMap.get(mostWktsCSV.player).bowlingAverage = mostWktsCSV.average;
                        recordDAOMap.get(mostWktsCSV.player).wickets = mostWktsCSV.wickets;

                    });
            return recordDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_FILE_FOUND, "File Not Found");
        } catch(IOException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "CSV file Not Proper");
        } catch(CSVBuilderException e){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.UNABLE_TO_PARSE, e.getMessage());
        }
    }
}