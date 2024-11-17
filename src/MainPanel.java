import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

class ButtonsPanel extends JPanel {
    private ImageIcon ButtonsPanelIco;
    private JButton add;
    private JButton delete;
    private JButton edit;
    private JButton check;

    ButtonsPanel(String buttonsBarBackground) {
        Image img = Toolkit.getDefaultToolkit().getImage(buttonsBarBackground).getScaledInstance(200, 460, 0);
        this.ButtonsPanelIco = new ImageIcon(img);
        JLabel bg = new JLabel(this.ButtonsPanelIco);
        bg.setBounds(0, 0, 200, 460);
        add = new JButton("ADD");
        delete = new JButton("DELETE");
        edit = new JButton("EDIT");
        check = new JButton("CHECK");
        add.setFocusable(false);
        add.setBounds(40, 60, 120,45);
        delete.setFocusable(false);
        delete.setBounds(40, 165, 120,45);
        edit.setFocusable(false);
        edit.setBounds(40, 270, 120,45);
        check.setFocusable(false);
        check.setBounds(40, 375, 120,45);
        this.setLayout(null);
        this.add(add);
        this.add(delete);
        this.add(edit);
        this.add(check);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D G = (Graphics2D) g;
        G.drawImage(ButtonsPanelIco.getImage(), 0, 0, null);
    }
}

public class MainPanel extends JLayeredPane {
    private ImageIcon background;
    private JPanel UI;
    private ButtonsPanel Buttons;
    private JScrollPane listRecPan;
    private int signX;
    private int signY;
    private String DataBasePath;

    MainPanel(String BackgroundPath, String buttonPanelIco, int signX, int signY, String databasePath) {
        this.signX = signX;
        this.signY = signY;
        this.background = new ImageIcon(BackgroundPath);
        this.DataBasePath = databasePath;
        this.Buttons = new ButtonsPanel(buttonPanelIco);
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

    public void setUI() {
        CSV database = new CSV();
        database.from_File(this.DataBasePath);
        JTable listRec = new JTable(new DefaultTableModel(database.returnRows(), database.returnColumns()) {
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
