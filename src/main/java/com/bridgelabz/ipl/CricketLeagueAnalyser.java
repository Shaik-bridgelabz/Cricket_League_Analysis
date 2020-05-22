package com.bridgelabz.ipl;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {

    Map<String, IplRecordDAO> iplDataMap;
    public IPLEntity iplEntity;

    public CricketLeagueAnalyser(IPLEntity iplEntity) {
        this.iplEntity=iplEntity;
    }

    public  int loadIplData(String... csvFilePath) throws CricketLeagueAnalyserException {
       iplDataMap=new IplAdapterFactory().cricketleagueFactory(iplEntity,csvFilePath);
       return iplDataMap.size();
    }

    public String getFieldWiseSortedData(SortByField.Parameter parameter) throws CricketLeagueAnalyserException {
        Comparator<IplRecordDAO> iplComparator;
        if(iplDataMap ==null || iplDataMap.size()==0){
            throw new CricketLeagueAnalyserException(CricketLeagueAnalyserException.TypeOfException.NO_DATA_FOUND, "No Data Found");
        }
        iplComparator = SortByField.getParameter(parameter);
        ArrayList sortedData= iplDataMap.values().stream().
                sorted(iplComparator).collect(Collectors.toCollection(ArrayList::new));
        String sortedDataInJson=new Gson().toJson(sortedData);
        return sortedDataInJson;
    }
}