package com.bridgelabz.ipl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        BATTING_AVERAGE, BOWLING_AVERAGE, STRIKERATE,BOWLING_STRIKERATE, CENTUARY, FOURS, HALFCENTUARY, HIGHSCORE, SIX,FIVE_WKTS,FOUR_WKTS, RUNBYBATSMAN, RUNBYBOWLER,
        SIX_AND_FOURS, SIX_AND_FOURS_WITH_STRIKERATE, BAT_STATS_AVG_WITH_STRIKERATE, BOWL_STATS_AVG_WITH_STRIKERATE,
        RUN_WITH_AVG, ECONOMY, FIVEWKT_FOURWKT_STRIKERATE, BAT_STATS_WKT_WITH_AVG, BOWL_STATS_WKT_WITH_AVG, BATTING_BOWLING_AVERAGE, IPL_BEST_ALLROUNDER;
    }

    SortByField() {

    }

    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<IplRecordDAO> BatAvgComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.battingAverage);
        Comparator<IplRecordDAO> BowlAvgComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.bowlingAverage);
        Comparator<IplRecordDAO> strikeRateComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.battingStrikeRate);
        Comparator<IplRecordDAO> BowlstrikeRateComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.bowlingStrikeRate);
        Comparator<IplRecordDAO> foursComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.fours);
        Comparator<IplRecordDAO> sixComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.sixes);
        Comparator<IplRecordDAO> batRunComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.battingRuns);
        Comparator<IplRecordDAO> bowlRunComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.bowlingRuns);
        Comparator<IplRecordDAO> ecoComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.economy);
        Comparator<IplRecordDAO> wktComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.wickets);
        Comparator<IplRecordDAO> fiveWktComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.fiveWickets);
        Comparator<IplRecordDAO> fourWktComparator = Comparator.comparing(mostRunCSV -> mostRunCSV.fourWickets);

        sortParameterComparator.put(Parameter.BATTING_AVERAGE, BatAvgComparator);
        sortParameterComparator.put(Parameter.BOWLING_AVERAGE, BowlAvgComparator);
        sortParameterComparator.put(Parameter.STRIKERATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.BOWLING_STRIKERATE,BowlstrikeRateComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.RUNBYBATSMAN, batRunComparator);
        sortParameterComparator.put(Parameter.RUNBYBOWLER, bowlRunComparator);
        sortParameterComparator.put(Parameter.FIVE_WKTS,fiveWktComparator);
        sortParameterComparator.put(Parameter.FOUR_WKTS,fourWktComparator);
        sortParameterComparator.put(Parameter.SIX_AND_FOURS, sixComparator.thenComparing(foursComparator));
        sortParameterComparator.put(Parameter.SIX_AND_FOURS_WITH_STRIKERATE, sixComparator.thenComparing(foursComparator).thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.BAT_STATS_AVG_WITH_STRIKERATE, BatAvgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.BOWL_STATS_AVG_WITH_STRIKERATE, BowlAvgComparator.thenComparing(BowlstrikeRateComparator));
        sortParameterComparator.put(Parameter.RUN_WITH_AVG, batRunComparator.thenComparing(BatAvgComparator));
        sortParameterComparator.put(Parameter.RUN_WITH_AVG, bowlRunComparator.thenComparing(BowlAvgComparator));
        sortParameterComparator.put(Parameter.ECONOMY, ecoComparator);
        sortParameterComparator.put(Parameter.FIVEWKT_FOURWKT_STRIKERATE, fiveWktComparator.thenComparing(fiveWktComparator).thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.BAT_STATS_WKT_WITH_AVG, wktComparator.thenComparing(BatAvgComparator));
        sortParameterComparator.put(Parameter.BOWL_STATS_WKT_WITH_AVG, wktComparator.thenComparing(BowlAvgComparator));
        sortParameterComparator.put(Parameter.BATTING_BOWLING_AVERAGE, BatAvgComparator.thenComparing(BowlAvgComparator));
        sortParameterComparator.put(Parameter.IPL_BEST_ALLROUNDER, batRunComparator.thenComparing(wktComparator));

        Comparator<IplRecordDAO> comparator = sortParameterComparator.get(parameter);
        return comparator;
    }

}
