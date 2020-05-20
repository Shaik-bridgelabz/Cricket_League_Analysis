package com.bridgelabz.ipl;

public class Ipl2019DAO {

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

    public Ipl2019DAO(Ipl2019RunsSheetCSV ipl2019RunsSheetCSV) {
        this.player =ipl2019RunsSheetCSV.player;
        this.battingAverage =ipl2019RunsSheetCSV.average;
        this.battingStrikeRate=ipl2019RunsSheetCSV.strikeRate;
        this.sixes=ipl2019RunsSheetCSV.sixes;
        this.fours=ipl2019RunsSheetCSV.fours;
        this.battingRuns=ipl2019RunsSheetCSV.runs;
    }

    public Ipl2019DAO(Ipl2019WktsSheetCSV ipl2019WktsSheetCSV) {
        this.player =ipl2019WktsSheetCSV.player;
        this.bowlingAverage =ipl2019WktsSheetCSV.average;
        this.bowlingStrikeRate =ipl2019WktsSheetCSV.strikeRate;
        this.economy=ipl2019WktsSheetCSV.economy;
        this.fiveWickets=ipl2019WktsSheetCSV.fiveWkts;
        this.fourWickets=ipl2019WktsSheetCSV.fourWkts;
        this.wickets=ipl2019WktsSheetCSV.wickets;
        this.bowlingRuns=ipl2019WktsSheetCSV.runs;
    }
}