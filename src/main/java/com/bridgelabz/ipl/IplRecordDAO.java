package com.bridgelabz.ipl;

public class IplRecordDAO {

    public double battingAverage;
    public String player;
    public double battingStrikeRate;
    public int sixes;
    public int fours;
    public int bowlingRuns;
    public int battingRuns;
    public double bowlingAverage;
    public double bowlingStrikeRate;
    public double economy;
    public int fiveWickets;
    public int fourWickets;
    public int wickets;

    public IplRecordDAO(IplRunsSheetCSV iplRunsSheetCSV) {
        this.player = iplRunsSheetCSV.player;
        this.battingAverage = iplRunsSheetCSV.average;
        this.battingStrikeRate= iplRunsSheetCSV.strikeRate;
        this.sixes= iplRunsSheetCSV.sixes;
        this.fours= iplRunsSheetCSV.fours;
        this.battingRuns= iplRunsSheetCSV.runs;
    }

    public IplRecordDAO(IplWktsSheetCSV iplWktsSheetCSV) {
        this.player =iplWktsSheetCSV.player;
        this.bowlingAverage =iplWktsSheetCSV.average;
        this.bowlingStrikeRate =iplWktsSheetCSV.strikeRate;
        this.economy=iplWktsSheetCSV.economy;
        this.fiveWickets=iplWktsSheetCSV.fiveWkts;
        this.fourWickets=iplWktsSheetCSV.fourWkts;
        this.wickets=iplWktsSheetCSV.wickets;
        this.bowlingRuns=iplWktsSheetCSV.runs;
    }

    public Object getCensusDTO(IPLEntity iplEntity) {
        if (iplEntity.equals(IPLEntity.BATTING))
            return new IplRunsSheetCSV(player,battingAverage,battingStrikeRate,sixes,fours,battingRuns);
        return new IplWktsSheetCSV(player,bowlingAverage,bowlingStrikeRate,economy,fiveWickets,fourWickets,wickets,bowlingRuns);
    }
}