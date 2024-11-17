import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    MainPanel panelBG;
    ImageIcon icon;
    MainFrame(String title){
        panelBG = new MainPanel("..\\Imgs\\Background.png","..\\Imgs\\buttonBarBackground.png",0,610,"..\\DataBase\\Birthdays.csv");
        icon = new ImageIcon("..\\Imgs\\ico.png");
        this.setTitle(title);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panelBG);
        this.setSize(1000,700);
        this.setResizable(false);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame("Happy birthday");
    }
}
