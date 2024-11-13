
// This class is a helper class contain some utility functions.
// Every function or data member should be static here.
import java.util.ArrayList;
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

    public static String rectifyDates(String date){
        String MDY[] = date.split("/");
        return Integer.parseInt(MDY[0]) +"/"+Integer.parseInt(MDY[1]); 
    }

    public static String getTodayDate() {
        String[] d = new Date().toString().split(" ");
        String date = MonthNumber(d[1]) + "/" + Integer.parseInt(d[2]);
        return date;
    }

    // if date is provided
    public static ArrayList<ArrayList<String>> checkIfBirthday(String date,String DatabasePath) {
        CSV database = new CSV();
        database.from_File(DatabasePath);
        ArrayList<String> names = database.findThatCol("name");
        ArrayList<String> dobs = database.findThatCol("D.O.B.");
        ArrayList<String> phones = database.findThatCol("phone");
        ArrayList<String> emails = database.findThatCol("email");
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (rectifyDates(dobs.get(i)).equals(date)) {
                name.add(names.get(i));
                phone.add(phones.get(i));
                email.add(emails.get(i));
            }
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(name);
        result.add(phone);
        result.add(email);
        return result;
    }

    // In case date is not provided then take today as date
    public static ArrayList<ArrayList<String>> checkIfBirthday(String DatabasePath) {
        String today = getTodayDate();
        CSV database = new CSV();
        database.from_File(DatabasePath);
        ArrayList<String> names = database.findThatCol("name");
        ArrayList<String> dobs = database.findThatCol("D.O.B.");
        ArrayList<String> phones = database.findThatCol("phone");
        ArrayList<String> emails = database.findThatCol("email");
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (rectifyDates(dobs.get(i)).equals(today)) {
                name.add(names.get(i));
                phone.add(phones.get(i));
                email.add(emails.get(i));
            }
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(name);
        result.add(phone);
        result.add(email);
        return result;
    }

    public static void sendWish(String name, String email, String phone) {
        // Function to be coded later for sending the actual msg via social media, SMS
        // or email.
        System.out.println("Happy Birthday" + name + " " + email);

    }
}
