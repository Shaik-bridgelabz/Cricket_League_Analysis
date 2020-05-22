package com.bridgelabz.ipl;

import com.opencsv.bean.CsvBindByName;

public class IplRunsSheetCSV {

        @CsvBindByName(column ="POS" , required=true)
        public String position;

        @CsvBindByName(column ="PLAYER" , required=true)
        public String player;

        @CsvBindByName(column ="Mat")
        public int match;

        @CsvBindByName(column ="Inns")
        public int innings;

        @CsvBindByName(column ="NO")
        public int notOut;

        @CsvBindByName(column ="Runs" , required=true)
        public int runs;

        @CsvBindByName(column ="SR" )
        public double strikeRate;

        @CsvBindByName(column ="Avg" )
        public double average;

        @CsvBindByName(column ="100" )
        public int hundreds;

        @CsvBindByName(column ="50")
        public int fiftys;

        @CsvBindByName(column ="6s" , required=true)
        public int sixes;

        @CsvBindByName(column ="4s" , required=true)
        public int fours;
        
        public IplRunsSheetCSV() {
        }

    public IplRunsSheetCSV(String player, double battingAverage, double battingStrikeRate, int sixes, int fours, int battingRuns) {
    }
}