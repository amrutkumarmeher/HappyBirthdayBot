public class Restarter {
    public static void restart(MainFrame oldFrame){
        oldFrame.dispose();
        new MainFrame("Happy Birthday");
    }
}
