package fr.icdc.todolist.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class ProgressUtil {


    private static int toCurrentDateDuration(Date date) {
        Date currentDate = Calendar.getInstance().getTime();
        return durationInDays(date, currentDate);
    }



    public static int durationInDays(Date beginning, Date end) {
        return (int)  (TimeUnit.MILLISECONDS.toDays(end.getTime() - beginning.getTime()));
    }

    public static int currentAdvancementRatioAsPercent(Date beginningDate, Date endingDate) {
        return   advancementRatioAsPercent(beginningDate,Calendar.getInstance().getTime(), endingDate);
    }

    public static int advancementRatioAsPercent(Date beginningDate, Date currentDate, Date endingDate) {
        return (int)  ( advancementRatio(beginningDate, currentDate, endingDate)*100 );
    }

    public static double advancementRatio(Date beginningDate, Date currentDate, Date endingDate) {
        double progressRatio = (double)durationInDays(beginningDate,currentDate) / (double)durationInDays(beginningDate,endingDate);
        progressRatio =  Math.min(1, progressRatio);
        progressRatio =  Math.max(0, progressRatio);
        return progressRatio;

    }
}
