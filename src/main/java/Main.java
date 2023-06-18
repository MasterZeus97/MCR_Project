
import GUI.MainWindow;
import Player.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        MainWindow mw = new MainWindow(player);
        mw.setVisible(true);
    }
}
