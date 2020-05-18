package com.bridgelabz.ipl;

import com.opencsv.bean.CsvBindByName;

public class Ipl2019RunsSheetCSV {

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


        @Override
        public String toString() {
            return "Ipl2019RunsSheet{" +
                    "position=" + position + '\'' +
                    "player=" + player + '\'' +
                    ", match=" + match +
                    ", innings=" + innings +
                    ", notOut=" + notOut +
                    ", runs=" + runs +
                    ", strikeRate=" + strikeRate +
                    ", average=" + average +
                    ", hundreds=" + hundreds +
                    ", fiftys=" + fiftys +
                    ", sixes=" + sixes +
                    ", fours=" + fours +
                    '}';
        }
}

