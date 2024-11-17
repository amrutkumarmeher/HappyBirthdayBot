// this class is main runner class used to run the entire project.

import java.awt.Graphics;

import javax.swing.JFrame;

public class Bot {
    public static void main(String[] args) {
        CSV csv = new CSV();
        csv.from_File("..\\DataBase\\Birthdays.csv");
        System.out.println(csv.returnRows());
        // System.out.println(csv);
    }
}