package dp.cryptd.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dp.cryptd.R;
import dp.cryptd.activities.MainActivity;

/**
 * Converts a long date time to String
 */
public class DateConverter {

    /**
     * Just returns a String date given a long time
     *
     * @param time
     * @return
     */
    public static String dateFromLong(long time) {
        Locale local = Locale.getDefault();
        DateFormat format = new SimpleDateFormat(MainActivity.getInstance().getResources().getString(R.string.dateTimePatternSimple), local);
        return format.format(new Date(time));
    }

}