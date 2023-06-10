package GUI.MainWindowPages;

import GUI.MainWindow;

import javax.swing.*;

/**
 * JPanel représentant le menu de combat.
 */
public class BattlePage extends MainWindowPage {
   public BattlePage (MainWindow mw) {
      super(mw);
      JButton button = new JButton("Terminer bataille");
      button.addActionListener(e -> mw.changeCard(MainWindow.CREATION_PAGE));
      add(button);
   }
}
