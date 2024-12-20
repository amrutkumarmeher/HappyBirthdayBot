import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonsPanel extends JPanel implements ActionListener {
    private String dbpath;
    private MainPanel bossPane;
    private ImageIcon ButtonsPanelIco;
    private JLabel bg;
    private JButton add;
    private JButton delete;
    private JButton edit;
    private JButton check;
    private MainFrame par;

    ButtonsPanel(String buttonsBarBackground, String dbpath,MainFrame parent,MainPanel boss) {
        this.bossPane = boss;
        this.par = parent;
        this.dbpath = dbpath;
        Image img = Toolkit.getDefaultToolkit().getImage(buttonsBarBackground).getScaledInstance(200, 460, 0);
        this.ButtonsPanelIco = new ImageIcon(img);
        bg = new JLabel(this.ButtonsPanelIco);
        bg.setBounds(0, 0, 200, 460);
        add = new JButton("ADD");
        add.addActionListener(this);
        delete = new JButton("DELETE");
        delete.addActionListener(this);
        edit = new JButton("EDIT");
        edit.addActionListener(this);
        check = new JButton("CHECK");
        check.addActionListener(this);
        add.setFocusable(false);
        add.setBounds(40, 60, 120, 45);
        delete.setFocusable(false);
        delete.setBounds(40, 165, 120, 45);
        edit.setFocusable(false);
        edit.setBounds(40, 270, 120, 45);
        check.setFocusable(false);
        check.setBounds(40, 375, 120, 45);
        this.setLayout(null);
        this.add(add);
        this.add(delete);
        this.add(edit);
        this.add(check);
        this.add(bg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(add)) {
            Editor ed = new Editor(dbpath,this.par);
        } else if (e.getSource().equals(delete)) {
            Deleting del = new Deleting(dbpath,this.par);
        } else if (e.getSource().equals(edit)) {
            String [] selectRow = bossPane.returnSelect();
            if(selectRow!=null){
                Editor ed = new Editor(selectRow[0], selectRow[1], selectRow[2], selectRow[3],Integer.parseInt(selectRow[4]), dbpath, par);
            } else {
                JOptionPane.showMessageDialog(null, "Select a record!", "No record selected", JOptionPane.WARNING_MESSAGE);
            }
            // Editor ed = new Editor("name","D.O.B", "phone","email", dbpath, par);
        } else if (e.getSource().equals(check)){
            CheckBirthday.main();
            JOptionPane.showMessageDialog(null, "Checking successful","Birthday Check", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

public class MainPanel extends JLayeredPane {
    private ImageIcon background;
    private JTable listRec;
    private JPanel UI;
    private ButtonsPanel Buttons;
    private JScrollPane listRecPan;
    private int signX;
    private int signY;
    private String DataBasePath;

    MainPanel(String BackgroundPath, String buttonPanelIco, int signX, int signY, String databasePath,MainFrame parent) {
        this.signX = signX;
        this.signY = signY;
        this.background = new ImageIcon(BackgroundPath);
        this.DataBasePath = databasePath;
        this.Buttons = new ButtonsPanel(buttonPanelIco, databasePath,parent,this);
    }

    public void paint(Graphics g) {
        Graphics2D G = (Graphics2D) g;
        G.drawImage(background.getImage(), 0, 0, null);
        G.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35));
        G.setColor(Color.BLACK);
        G.drawString("Happy Birthday Bot", 320, 60);
        G.setFont(new Font("Eliphant", Font.BOLD, 15));
        G.setColor(new Color(0x1e7536));
        G.drawString("Made By", this.signX, this.signY);
        G.drawString("Amrut Kumar Meher", this.signX, this.signY + 15);
        G.drawString("CSE 2023-26", this.signX, this.signY + 30);
        G.drawString("Govt. Polytechnic Bargarh", this.signX, this.signY + 45);
        this.setUI();
    }
    public String[] returnSelect(){
        if(listRec.getSelectedRow()!=-1){
            CSV database = new CSV();
        database.from_File(this.DataBasePath);
            String[] result = {database.readCell(listRec.getSelectedRow()+1,0),
                database.readCell(listRec.getSelectedRow()+1,1),
                database.readCell(listRec.getSelectedRow()+1,2),
                database.readCell(listRec.getSelectedRow()+1,3),""+listRec.getSelectedRow()};
            return result;
        }else {
            return null;
        }
    }
    public void setUI() {
        CSV database = new CSV();
        database.from_File(this.DataBasePath);
        
        this.listRec = new JTable(new DefaultTableModel(database.returnRows(), database.returnColumns()) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        });
        // list
        listRec.setRowHeight(30);
        listRec.setBackground(Color.LIGHT_GRAY);
        listRecPan = new JScrollPane(listRec);
        listRecPan.setBounds(0, 0, 600, 460);
        UI = new JPanel(null);
        UI.setBounds(100, 120, 800, 460);
        // buttons & menu
        Buttons.setBounds(600, 0, 200, 460);
        UI.add(listRecPan);
        UI.add(Buttons);
        this.add(UI, 10);
    }

}
