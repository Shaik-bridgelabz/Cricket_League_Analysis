package com.bridgelabz.ipl;

import java.util.Map;

public class BowlerAdapter extends IplAdapter {

    @Override
    public Map<String, IplRecordDAO> loadIPLData(IPLEntity iplEntity, String... csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IplRecordDAO> recordDAOMap = super.loadIPLData(IplWktsSheetCSV.class, csvFilePath);
        return recordDAOMap;

    }

}