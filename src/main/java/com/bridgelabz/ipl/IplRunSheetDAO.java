package com.bridgelabz.ipl;

public class IplRunSheetDAO {

    public double average;
    public String player;
    public double strikeRate;
    public int sixes;

    public IplRunSheetDAO(Ipl2019RunsSheetCSV ipl2019RunsSheetCSV) {
        this.player=ipl2019RunsSheetCSV.player;
        this.average=ipl2019RunsSheetCSV.average;
        this.strikeRate=ipl2019RunsSheetCSV.strikeRate;
        this.sixes=ipl2019RunsSheetCSV.sixes;
    }
}
