package GUI.MainWindowPages;

import GUI.MainWindow;
import com.sun.tools.javac.Main;

import javax.swing.*;

/**
 * JPanel représentant l'écran titre du jeu.
 */
public class TitlePage extends MainWindowPage {
   public TitlePage (MainWindow mw) {
      super(mw);
      add(new JLabel("NOM DU JEU"));
      JButton button = new JButton("Jouer");
      button.addActionListener(e -> mw.changeCard(MainWindow.CREATION_PAGE));
      add(button);
   }
}
