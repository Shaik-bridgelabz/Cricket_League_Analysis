package com.bridgelabz.ipl;

public class IplRunSheetDAO {

    public double average;
    public String player;

    public IplRunSheetDAO(Ipl2019RunsSheetCSV ipl2019RunsSheetCSV) {
        this.player=ipl2019RunsSheetCSV.player;
        this.average=ipl2019RunsSheetCSV.average;
    }
}
