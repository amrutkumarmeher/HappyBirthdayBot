import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deleting implements ActionListener{
    JComboBox<String> indxs;
    JFrame frame;
    String dbpath;
    JPanel pan;
    JButton submit;
    Deleting(String databasePath){
        CSV db = new CSV();
        dbpath = databasePath;
        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.addActionListener(this);
        db.from_File(dbpath);
        String[] options = new String[db.size()];
        for(int i = 0; i<db.size();i++){
            options[i] = ""+(i+1);
        }
        indxs = new JComboBox<>(options);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,100);
        pan = new JPanel();
        pan.add(new JLabel("Choose Index to delete"));
        pan.add(indxs);
        pan.add(submit);
        frame.add(pan);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(submit)){
            CSV db = new CSV();
            db.from_File(this.dbpath);
            db.removeRow(indxs.getSelectedIndex()+1);
            db.to_File(dbpath);
            submit.setEnabled(false);;
        }
    }
    public static void main(String[] args) {
        Deleting del = new Deleting("..\\DataBase\\Birthdays.csv");
    }
}
