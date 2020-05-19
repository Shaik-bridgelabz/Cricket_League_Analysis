package com.bridgelabz.ipl;

public class IplWktsSheetDAO {

    public double average;
    public String player;
    public double strikeRate;
    public double economy;
    public int fiveWickets;

    public IplWktsSheetDAO(Ipl2019WktsSheetCSV ipl2019WktsSheetCSV) {
        this.player=ipl2019WktsSheetCSV.player;
        this.average=ipl2019WktsSheetCSV.average;
        this.strikeRate=ipl2019WktsSheetCSV.strikeRate;
        this.economy=ipl2019WktsSheetCSV.economy;
        this.fiveWickets=ipl2019WktsSheetCSV.fiveWkts;
    }

}
