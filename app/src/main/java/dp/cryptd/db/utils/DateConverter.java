package dp.cryptd.db.utils;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import dp.cryptd.R;
import dp.cryptd.activities.MainActivity;

/**
 * Converts LocalDate to long and vice-versa, to store on DB and retrieve it again.
 */
public class DateConverter {

    @TypeConverter
    public static long toTimeStamp(LocalDate date) {
        return date == null ? 0 : date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @TypeConverter
    public static LocalDate toDate(Long timeStamp) {
        return timeStamp == 0 ? null : Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Not a TypeConverter. Just returns a String date given a long time
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