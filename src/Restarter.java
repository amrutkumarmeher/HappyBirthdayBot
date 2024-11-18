public class Restarter {
    public static void restart(MainFrame oldFrame){
        oldFrame.dispose();
        oldFrame.setVisible(false);
        oldFrame = null;
        MainFrame frame = new MainFrame("Happy Birthday");
    }
    public static void DisposeDialog(Editor e){
        e.dispose();
        e.setVisible(false);
        e = null;
    }
    public static void DisposeDialog(Deleting d){
        d.clear();
        d = null;
    }
}
