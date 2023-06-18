package GUI.MainWindowPages;

import GUI.MainWindow;

import javax.swing.*;

/**
 * Classe abstraite implémentée par les différentes pages de la fenêtre principale
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
public abstract class MainWindowPage extends JPanel {
   protected final MainWindow mw;

   /**
    * Construit une page de la fenêtre principale.
    * @param mw La fenêtre principale.
    */
   protected MainWindowPage(MainWindow mw) {
      this.mw = mw;
   }

   /**
    * Retourne la fenêtre où se trouve cette page.
    * @return La fenêtre où se trouve cette page.
    */
   public MainWindow getMainWindow() {
      return mw;
   }

   /**
    * Méthode appelée lorsque cette page est chargée.
    */
   public void setupPage() {}
}
