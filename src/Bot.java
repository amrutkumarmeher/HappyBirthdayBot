
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.nio.file.Files;
import java.io.File;
import java.lang.Integer;

class CSV {
    private ArrayList<ArrayList<String>> table;

    CSV() {
        table = new ArrayList<ArrayList<String>>();
    }

    CSV(ArrayList<ArrayList<String>> vals) {
        table = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < vals.size(); i++) {
            table.add(i, new ArrayList<String>());
            for (int j = 0; j < vals.get(i).size(); j++) {
                table.get(i).add(j, vals.get(i).get(j));
            }
        }
    }

    public void addCellAtEndOfRow(int rowIndx, String val) {
        table.get(rowIndx).add(val);
    }

    public String readCell(int rowIndx, int colIndx) {
        return table.get(rowIndx).get(colIndx);
    }

    public void insertCell(int rowIndx, int colIndx, String value) {
        table.get(rowIndx).add(colIndx, value);
    }

    public void writeCell(int rowIndx, int colIndx, String value) {
        table.get(rowIndx).remove(colIndx);
        table.get(rowIndx).add(colIndx, value);
    }

    public String removeCell(int rowIndx, int colIndx) {
        String value = table.get(rowIndx).get(colIndx);
        table.get(colIndx).remove(colIndx);
        return value;
    }

    public void addRowAtEndOfTable(ArrayList<String> newRow) {
        table.add(newRow);
    }

    public void insertRow(int rowIndx, ArrayList<String> newRow) {
        table.add(rowIndx, newRow);
    }

    public ArrayList<String> readRow(int rowIndx) {
        return table.get(rowIndx);
    }

    public ArrayList<String> removeRow(int rowIndx) {
        ArrayList<String> rowVal = table.get(rowIndx);
        table.remove(rowIndx);
        return rowVal;
    }

    public void addColAtEndOfTable(ArrayList<String> colVals) {
        for (int i = 0; i < colVals.size(); i++) {
            addCellAtEndOfRow(i, colVals.get(i));
        }
    }

    public void insertCol(int colIndx, ArrayList<String> colVals) {
        for (int i = 0; i < colVals.size(); i++) {
            insertCell(i, colIndx, colVals.get(i));
        }
    }

    public ArrayList<String> findThatCol(String head) {
        for (int i = 0; i < table.get(0).size(); i++) {
            if (head.equals(readCell(0, i))) {
                ArrayList<String> col = readCol(i);
                col.remove(0);
                return col;
            }
        }
        return new ArrayList<String>();
    }

    public ArrayList<String> readCol(int colIndx) {
        ArrayList<String> cols = new ArrayList<String>();
        for (int i = 0; i < table.size(); i++) {
            cols.add(table.get(i).get(colIndx));
        }
        return cols;
    }

    public ArrayList<String> removeCol(int colIndx) {
        ArrayList<String> colVals = new ArrayList<String>();
        for (int i = 0; i < table.size(); i++) {
            colVals.add(table.get(colIndx).get(i));
        }
        for (int i = 0; i < table.size(); i++) {
            removeCell(i, colIndx);
        }
        return colVals;
    }

    public void to_File(String filePath) {
        Path file = Paths.get(filePath);
        try {
            Files.write(file, getAllVals().getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void from_File(String filePath) {
        Path file = Paths.get(filePath);
        table = new ArrayList<ArrayList<String>>();
        String[] rows;
        String[] vals;
        String temp = "";
        boolean capture = false;
        String content = "";
        try {
            for (String line : Files.readAllLines(file)) {
                content += line + "\n";
            }
            rows = content.split("\n");
            for (int i = 0; i < rows.length; i++) {
                vals = rows[i].split(",");
                table.add(new ArrayList<String>());
                for (int j = 0; j < vals.length; j++) {
                    if (capture == false && vals[j].startsWith("\"")) {
                        capture = true;
                        temp = vals[j].substring(1) + ",";
                    } else if (capture == true && vals[j].endsWith("\"")) {
                        capture = false;
                        addCellAtEndOfRow(i, temp + vals[j].substring(0, vals[j].length() - 1));
                    } else if (capture == false) {
                        addCellAtEndOfRow(i, vals[j]);
                    } else if (capture == true) {
                        temp = temp + vals[j] + ",";
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void from_Multiple_File(File[] files) {
        table = new ArrayList<ArrayList<String>>();
        String[] rows;
        String[] vals;
        boolean first = false;
        String temp = "";
        boolean capture = false;
        String content = "";
        try {
            for (File filePath : files) {
                Path file = Paths.get(filePath.toString());
                for (String line : Files.readAllLines(file)) {
                    if (Files.readAllLines(file).indexOf(line) == 0 && first == true) {
                        continue;
                    } else if (Files.readAllLines(file).indexOf(line) == 0) {
                        first = true;
                    }
                    content += line + "\n";
                }
            }
            rows = content.split("\n");
            for (int i = 0; i < rows.length; i++) {
                vals = rows[i].split(",");
                table.add(new ArrayList<String>());
                for (int j = 0; j < vals.length; j++) {
                    if (capture == false && vals[j].startsWith("\"")) {
                        capture = true;
                        temp = vals[j].substring(1) + ",";
                    } else if (capture == true && vals[j].endsWith("\"")) {
                        capture = false;
                        addCellAtEndOfRow(i, temp + vals[j].substring(0, vals[j].length() - 1));
                    } else if (capture == false) {
                        addCellAtEndOfRow(i, vals[j]);
                    } else if (capture == true) {
                        temp = temp + vals[j] + ",";
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getAllVals() {
        String content = "";
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {
                if (content == "" || content.endsWith("\n")) {
                    content = content + table.get(i).get(j);
                } else {
                    if (table.get(i).get(j).contains(",")) {
                        content = content + ",\"" + table.get(i).get(j) + "\"";
                    } else {
                        content = content + "," + table.get(i).get(j);
                    }
                }
            }
            if (!(i == table.size() - 1)) {
                content = content + "\n";
            }
        }
        return content;
    }

    public void printTable() {
        System.out.print(getAllVals());
    }
}

public class Bot {
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

    public static String getTodayDate() {
        String[] d = new Date().toString().split(" ");
        String date = MonthNumber(d[1]) + "/" + Integer.parseInt(d[2]) + "/" + d[5];
        return date;
    }

    public static ArrayList<ArrayList<String>> checkIfBirthday() {
        // String today = getTodayDate();
        String today = "9/2/2006";
        CSV database = new CSV();
        database.from_File("birthdays.csv");
        ArrayList<String> names = database.findThatCol("name");
        ArrayList<String> dobs = database.findThatCol("D.O.B.");
        ArrayList<String> phones = database.findThatCol("phone");
        ArrayList<String> emails = database.findThatCol("email");
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (dobs.get(i).equals(today)) {
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
    public static void sendWish(String name, String email, String phone){
        // Function to be coded later for sending the actual msg via social media, SMS or email.

    }

    public static void main(String[] args) {
        System.out.println(checkIfBirthday());
    }
}