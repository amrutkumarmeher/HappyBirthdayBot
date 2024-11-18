
// This class is a helper class contain some utility functions.
// Every function or data member should be static here.
import java.util.Date;

public class BotHelper {
    public static int MonthNumber(String month) {
        if (month.equals("Jan")) {
            return 1;
        } else if (month.equals("Feb")) {
            return 2;
        } else if (month.equals("Mar")) {
            return 3;
        } else if (month.equals("Apr")) {
            return 4;
        } else if (month.equals("May")) {
            return 5;
        } else if (month.equals("Jun")) {
            return 6;
        } else if (month.equals("Jul")) {
            return 7;
        } else if (month.equals("Aug")) {
            return 8;
        } else if (month.equals("Sep")) {
            return 9;
        } else if (month.equals("Oct")) {
            return 10;
        } else if (month.equals("Nov")) {
            return 11;
        } else if (month.equals("Dec")) {
            return 12;
        } else {
            return -1;
        }
    }

    public static String rectifyDates(String date) {
        String MDY[] = date.split("/");
        return Integer.parseInt(MDY[0]) + "/" + Integer.parseInt(MDY[1]);
    }

    public static String getTodayDate() {
        String[] d = new Date().toString().split(" ");
        String date = MonthNumber(d[1]) + "/" + Integer.parseInt(d[2]);
        return date;
    }
}
