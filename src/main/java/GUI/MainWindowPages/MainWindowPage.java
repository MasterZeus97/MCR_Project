package GUI.MainWindowPages;

import GUI.MainWindow;

import javax.swing.*;

public abstract class MainWindowPage extends JPanel {
   protected final MainWindow mw;

   protected MainWindowPage(MainWindow mw) {
      this.mw = mw;
   }

   public MainWindow getMainWindow() {
      return mw;
   }
}
