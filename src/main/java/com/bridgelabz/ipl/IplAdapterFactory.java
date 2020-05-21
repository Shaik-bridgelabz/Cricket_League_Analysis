package com.bridgelabz.ipl;

import java.util.Map;

public class IplAdapterFactory {

    public Map<String, IplRecordDAO> cricketleagueFactory(IPLEntity iplEntity, String... csvFilePath) throws CricketLeagueAnalyserException {
        if (iplEntity.equals(iplEntity.BATTING))
            return new BatsmenAdapter().loadIPLData(iplEntity, csvFilePath);
            return new BowlerAdapter().loadIPLData(iplEntity, csvFilePath);
    }
}