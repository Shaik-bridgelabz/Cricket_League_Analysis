package com.bridgelabz.ipl;

import com.opencsv.bean.CsvBindByName;

public class IplWktsSheetCSV {


    @CsvBindByName(column = "POS", required = true)
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "BBI", required = true)
    public int bestBowling;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWkts;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWkts;

    public IplWktsSheetCSV(String player, double bowlingAverage, double bowlingStrikeRate, double economy,
                           int fiveWickets, int fourWickets, int wickets, int bowlingRuns) {
        this.player=player;
        this.average=bowlingAverage;
        this.strikeRate=bowlingStrikeRate;
        this.economy=economy;
        this.fiveWkts=fiveWickets;
        this.fourWkts=fourWickets;
        this.wickets=wickets;
        this.runs=bowlingRuns;
    }

    public IplWktsSheetCSV() {
    }
}