package com.bridgelabz.ipl;

public class IplWktsSheetDAO {

    public double average;
    public String player;

    public IplWktsSheetDAO(Ipl2019WktsSheetCSV ipl2019WktsSheetCSV) {
        this.player=ipl2019WktsSheetCSV.player;
        this.average=ipl2019WktsSheetCSV.average;
    }

}
