import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

public class SendWish {
    public static void sendEmail(String email, String subject ,String body){
        // This function needs to be completed.
    }
    public static void sendMsg(String phone,String msg){
        // This function needs to be completed.
    }
    public static void sendNotification(String title,String msg){
        SystemTray trayZone = SystemTray.getSystemTray();
        Image img = Toolkit.getDefaultToolkit().getImage("img.png");
        TrayIcon notifi = new TrayIcon(img,"wish");
        notifi.setImageAutoSize(true);
        try {
            trayZone.add(notifi);
            notifi.displayMessage(title, msg,TrayIcon.MessageType.NONE);
            trayZone.remove(notifi);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
