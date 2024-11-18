import java.util.ArrayList;

public class CheckBirthday {
    private static String DataBasePath = "..\\DataBase\\Birthdays.csv";
    private static String msg1 = "🎉 Happy Birthday, ";
    private static String msg2 = """
            ! 🎂 🎉

            Wishing you a day filled with laughter, love, and all the things that bring you joy! 🌟 May this year be your best one yet, full of new adventures and wonderful memories.

            Enjoy your special day to the fullest! 🥳🎁

            From all of your batchmates & lecturers
            CSE 2023-26
            Department of IT
            Govt. Polytechnic Bargarh
                        """;;
    private static String email1Sub = "🎉 Happy Birthday, ";
    private static String email2Sub = " ! 🎂";
    private static String email1Body = "Dear ";
    private static String email2body = """
            ,

            Happy Birthday! 🎉

            Today is all about celebrating you and the wonderful person you are. May your day be filled with joy, laughter, and the company of loved ones. 🌟 Here's to another amazing year ahead, full of exciting opportunities and memorable moments!

            Enjoy your special day to the fullest—you deserve it! 🎁🎊

            Warmest wishes,
            from your batchmates & lecturers
            CSE 2023-26
            Department of IT
            Govt. Polytecnic, Bargarh

            Even your friendly bot(3rd sem project) wanted to join in on the celebration! 🤖🎈
                        """;;
    private static String notifTitle = "Happy birthday ";
    private static String notifMsg1 = "Wish ";
    private static String notifMsg2 = " a very happy birthday!";

    private static String askWishMsg1 = "Today is the birthday of ";
    private static String askWishMsg2 = "! 🎉🎂 Don’t forget to wish him a very happy birthday and make his day special. 😊";
    private static String askWishEmail1Sub = "Wish ";
    private static String askWishEmail2Sub = " a Happy Birthday! 🎉";
    private static String askWishEmail1Body = "Hello ";
    private static String askWishEmail2Body = """
            ,

            Just a quick note to let you know that today is
                """;
    private static String askWishEmail3Body = """
            ’s birthday! 🎂

            Let’s make their day special—don’t forget to wish them a very happy birthday! 😊

            Best regards,

            Your lovely batchmates & lecturers
            CSE 2023-26
            Department of IT
            Govt. Polytechnic, Bargarh
            """;

    // In case date is not provided then take today as date
    public static ArrayList<ArrayList<String>> checkIfBirthday(String DatabasePath) {
        String today = BotHelper.getTodayDate();
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
            if (BotHelper.rectifyDates(dobs.get(i)).equals(today)) {
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

    // if date is provided
    public static ArrayList<ArrayList<String>> checkIfBirthday(String date, String DatabasePath) {
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
            if (BotHelper.rectifyDates(dobs.get(i)).equals(date)) {
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

    public static CSV getAllNames(String DatabasePath) {
        CSV database = new CSV();
        database.from_File(DatabasePath);
        ArrayList<String> names = database.findThatCol("name");
        ArrayList<String> phones = database.findThatCol("phone");
        ArrayList<String> emails = database.findThatCol("email");
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> buffer = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            buffer.add(names.get(i));
            buffer.add(phones.get(i));
            buffer.add(emails.get(i));
            result.add(buffer);
            buffer = new ArrayList<>();
        }
        return new CSV(result);
    }

    public static void main(String[] args) {
        CSV allNames = getAllNames("..\\DataBase\\Birthdays.csv");
        ArrayList<ArrayList<String>> nameDates = checkIfBirthday(DataBasePath);
        for (int i = 0; i < nameDates.get(0).size(); i++) {
            int indx = allNames.readCol(0).indexOf(nameDates.get(0).get(i));
            allNames.removeRow(indx);
            ArrayList<String> rowBdy = new ArrayList<>();
            rowBdy.add(nameDates.get(0).get(i));
            rowBdy.add(nameDates.get(1).get(i));
            rowBdy.add(nameDates.get(2).get(i));
            SendWish.sendMsg(rowBdy.get(1), msg1 + rowBdy.get(0) + msg2);
            SendWish.sendEmail(rowBdy.get(2), email1Sub + rowBdy.get(0) + email2Sub,
                    email1Body + rowBdy.get(0) + email2body);
            SendWish.sendNotification(notifTitle + rowBdy.get(0), notifMsg1 + rowBdy.get(0) + notifMsg2);
            for (int j = 0; j < allNames.readCol(0).size(); j++) {
                ArrayList<String> row = allNames.readRow(j);
                SendWish.sendMsg(row.get(1), askWishMsg1 + rowBdy.get(i) + askWishMsg2);
                SendWish.sendEmail(row.get(1), askWishEmail1Sub + rowBdy.get(0) + askWishEmail2Sub,
                        askWishEmail1Body + row.get(0) + askWishEmail2Body + rowBdy.get(0) + askWishEmail3Body);
            }
        }
    }

    public static void main() {
        CSV allNames = getAllNames("..\\DataBase\\Birthdays.csv");
        ArrayList<ArrayList<String>> nameDates = checkIfBirthday("..\\DataBase\\Birthdays.csv");
        for (int i = 0; i < nameDates.get(0).size(); i++) {
            int indx = allNames.readCol(0).indexOf(nameDates.get(0).get(i));
            allNames.removeRow(indx);
            ArrayList<String> rowBdy = new ArrayList<>();
            rowBdy.add(nameDates.get(0).get(i));
            rowBdy.add(nameDates.get(1).get(i));
            rowBdy.add(nameDates.get(2).get(i));
            SendWish.sendMsg(rowBdy.get(1), msg1 + rowBdy.get(0) + msg2);
            SendWish.sendEmail(rowBdy.get(2), email1Sub + rowBdy.get(0) + email2Sub,
                    email1Body + rowBdy.get(0) + email2body);
            SendWish.sendNotification(notifTitle + rowBdy.get(0), notifMsg1 + rowBdy.get(0) + notifMsg2);
            for (int j = 0; j < allNames.readCol(0).size(); j++) {
                ArrayList<String> row = allNames.readRow(j);
                SendWish.sendMsg(row.get(1), askWishMsg1 + rowBdy.get(i) + askWishMsg2);
                SendWish.sendEmail(row.get(1), askWishEmail1Sub + rowBdy.get(0) + askWishEmail2Sub,
                        askWishEmail1Body + row.get(0) + askWishEmail2Body + rowBdy.get(0) + askWishEmail3Body);
            }
        }
    }

    public static void main(String date, String DataBasePath) {
        CSV allNames = getAllNames(DataBasePath);
        ArrayList<ArrayList<String>> nameDates = checkIfBirthday(date, "..\\DataBase\\Birthdays.csv");
        for (int i = 0; i < nameDates.get(0).size(); i++) {
            int indx = allNames.readCol(0).indexOf(nameDates.get(0).get(i));
            allNames.removeRow(indx);
            ArrayList<String> rowBdy = new ArrayList<>();
            rowBdy.add(nameDates.get(0).get(i));
            rowBdy.add(nameDates.get(1).get(i));
            rowBdy.add(nameDates.get(2).get(i));
            SendWish.sendMsg(rowBdy.get(1), msg1 + rowBdy.get(0) + msg2);
            SendWish.sendEmail(rowBdy.get(2), email1Sub + rowBdy.get(0) + email2Sub,
                    email1Body + rowBdy.get(0) + email2body);
            SendWish.sendNotification(notifTitle + rowBdy.get(0), notifMsg1 + rowBdy.get(0) + notifMsg2);
            for (int j = 0; j < allNames.readCol(0).size(); j++) {
                ArrayList<String> row = allNames.readRow(j);
                SendWish.sendMsg(row.get(1), askWishMsg1 + rowBdy.get(i) + askWishMsg2);
                SendWish.sendEmail(row.get(1), askWishEmail1Sub + rowBdy.get(0) + askWishEmail2Sub,
                        askWishEmail1Body + row.get(0) + askWishEmail2Body + rowBdy.get(0) + askWishEmail3Body);
            }
        }
    }
}
