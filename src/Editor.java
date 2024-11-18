import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends JFrame implements ActionListener {
    private MainFrame myParent;
    private String dbPath;
    private int index;
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

    Editor(String DatabasePath, MainFrame parent) {
        isInsert = true;
        this.myParent = parent;
        dbPath = DatabasePath;
        ico = new ImageIcon("..\\Imgs\\configIco.png");
        this.setResizable(false);
        this.setLayout(new GridLayout(6, 1));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

    Editor(String nameStr, String dobStr, String phoneStr, String emailStr, int indx, String DataBasePath,
            MainFrame parent) {
        this.index = indx + 1;
        this.myParent = parent;
        isInsert = false;
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

    public String[] data() {
        String[] data = { this.name, this.dob, this.phone, this.email };
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitButt) && isInsert == true) {
            ArrayList<String> datas = new ArrayList<>();
            this.name = nameField.getText();
            if (this.name.equals("")) {
                this.name = "NaN";
            }
            datas.add(this.name);
            this.dob = dobField.getText();
            if (this.dob.equals("")) {
                this.dob = "NaN";
            }
            datas.add(this.dob);
            this.phone = phoneField.getText();
            if (this.phone.equals("")) {
                this.phone = "NaN";
            }
            datas.add(this.phone);
            this.email = emailField.getText();
            if (this.email.equals("")) {
                this.email = "NaN";
            }
            datas.add(this.email);
            CSV csv = new CSV();
            csv.from_File(this.dbPath);
            csv.addRowAtEndOfTable(datas);
            csv.to_File(dbPath);
            submitButt.setEnabled(false);
            Restarter.DisposeDialog(this);
            Restarter.restart(myParent);
        } else if (e.getSource().equals(submitButt) && isInsert == false) {
            this.name = nameField.getText();
            this.dob = dobField.getText();
            this.phone = phoneField.getText();
            this.email = emailField.getText();
            if (this.name.equals("")) {
                this.name = "NaN";
            }
            if (this.dob.equals("")) {
                this.dob = "NaN";
            }
            if (this.phone.equals("")) {
                this.phone = "NaN";
            }
            if (this.email.equals("")) {
                this.email = "NaN";
            }
            CSV csv = new CSV();
            csv.from_File(this.dbPath);
            csv.writeCell(index, 0, this.name);
            csv.writeCell(index, 1, this.dob);
            csv.writeCell(index, 2, this.phone);
            csv.writeCell(index, 3, this.email);
            csv.to_File(dbPath);
            Restarter.DisposeDialog(this);
            Restarter.restart(myParent);
        }
    }
}
