package ir.sahand.tour;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
    public static String ConvertInputStreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            while (bis.available() != 0) {
                sb.append((char) bis.read());
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String convertTimestampToHumanReadableString(String datetime) {
        GregorianCalendar gc = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            gc.setTime(sdf.parse(datetime));
        } catch (Exception e) {
            //nothing
        }

        JalaliCalendar jc = new JalaliCalendar(gc);

        return jc.getDay() + " " + jc.getMonthString();
    }

    public static String formatMoney(String amount) {
        return formatMoney(amount, "تومان");
    }

    public static String formatMoney(String amount, String currency) {
        if (amount.equals("0")) {
            return "رایگان";
        }

        return String.format("%,d", Integer.parseInt(amount)) + " " + currency;
    }

}
