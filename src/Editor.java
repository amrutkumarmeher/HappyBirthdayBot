import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends JFrame implements ActionListener {
    private String dbPath;
    private boolean isInsert;
    private ImageIcon ico;
    private JPanel namePan;
    private String name;
    private JTextField nameField;
    private JPanel dobPan;
    private String dob;
    private JTextField dobField;
    private JPanel phonePan;
    private String phone;
    private JTextField phoneField;
    private JPanel emailPan;
    private String email;
    private JTextField emailField;
    private JPanel buttonArea;
    private JButton submitButt;

    Editor(String DatabasePath) {
        isInsert = false;
        dbPath = DatabasePath;
        ico = new ImageIcon("..\\Imgs\\configIco.png");
        this.setResizable(false);
        this.setLayout(new GridLayout(6, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 250);
        this.setTitle("Students Info");
        this.setIconImage(ico.getImage());
        nameField = new JTextField();
        dobField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        namePan = new JPanel();
        namePan.add(new JLabel("Name"));
        nameField.setPreferredSize(new Dimension(300, 20));
        namePan.add(nameField);
        this.add(namePan);

        dobPan = new JPanel();
        dobPan.add(new JLabel("D.O.B"));
        dobField.setPreferredSize(new Dimension(300, 20));
        dobPan.add(dobField);
        this.add(dobPan);

        phonePan = new JPanel();
        phonePan.add(new JLabel("phone"));
        phoneField.setPreferredSize(new Dimension(300, 20));
        phonePan.add(phoneField);
        this.add(phonePan);

        emailPan = new JPanel();
        emailPan.add(new JLabel("email"));
        emailField.setPreferredSize(new Dimension(300, 20));
        emailPan.add(emailField);
        this.add(emailPan);

        buttonArea = new JPanel();
        submitButt = new JButton("Submit");
        submitButt.addActionListener(this);
        submitButt.setFocusable(false);
        submitButt.setBackground(Color.LIGHT_GRAY);
        buttonArea.add(submitButt);

        this.add(buttonArea);
        this.setVisible(true);
    }

    Editor(String nameStr, String dobStr, String phoneStr, String emailStr,String DataBasePath) {
        isInsert = true;
        dbPath = DataBasePath;
        nameField = new JTextField();
        dobField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        nameField.setText(nameStr);
        dobField.setText(dobStr);
        phoneField.setText(phoneStr);
        emailField.setText(emailStr);

        ico = new ImageIcon("..\\Imgs\\configIco.png");
        this.setResizable(false);
        this.setLayout(new GridLayout(6, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 250);
        this.setTitle("Students Info");
        this.setIconImage(ico.getImage());

        namePan = new JPanel();
        namePan.add(new JLabel("Name"));
        nameField.setPreferredSize(new Dimension(300, 20));
        namePan.add(nameField);
        this.add(namePan);

        dobPan = new JPanel();
        dobPan.add(new JLabel("D.O.B"));
        dobField.setPreferredSize(new Dimension(300, 20));
        dobPan.add(dobField);
        this.add(dobPan);

        phonePan = new JPanel();
        phonePan.add(new JLabel("phone"));
        phoneField.setPreferredSize(new Dimension(300, 20));
        phonePan.add(phoneField);
        this.add(phonePan);

        emailPan = new JPanel();
        emailPan.add(new JLabel("email"));
        emailField.setPreferredSize(new Dimension(300, 20));
        emailPan.add(emailField);
        this.add(emailPan);

        buttonArea = new JPanel();
        submitButt = new JButton("Submit");
        submitButt.addActionListener(this);
        submitButt.setFocusable(false);
        submitButt.setBackground(Color.LIGHT_GRAY);
        buttonArea.add(submitButt);

        this.add(buttonArea);
        this.setVisible(true);
    }

    private void selfDestructor(){
        try{TimeUnit.MICROSECONDS.sleep(100);}catch(Exception e){}
        this.dispose();
    }
    public String[] data() {
        String[] data = { this.name, this.dob, this.phone, this.email };
        selfDestructor();
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitButt) && isInsert==false) {
            ArrayList<String> datas = new ArrayList<>();
            this.name = nameField.getText();
            datas.add(this.name);
            this.dob = dobField.getText();
            datas.add(this.dob);
            this.phone = phoneField.getText();
            datas.add(this.phone);
            this.email = emailField.getText();
            datas.add(this.email);

            CSV csv = new CSV();
            csv.from_File(this.dbPath);
            csv.addRowAtEndOfTable(datas);
            csv.to_File(dbPath);
            submitButt.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        Editor e = new Editor("..\\DataBase\\Birthdays.csv");
    }
}
