package GUI;

import javax.swing.*;

/**
 * Fenêtre secondaire permettant de visualiser les bataillons et leur contenu.
 */
public class SquadronViewerWindow extends JFrame {
   private static SquadronViewerWindow instance = null;

   /**
    * Crée une nouvelle fenêtre.
    * Le constructeur est privé, car cette classe est un Singleton.
    */
   private SquadronViewerWindow() {
      setTitle("Squadron Viewer");
      setSize(400, 300);
      setLocationRelativeTo(null);

      JPanel panel = new JPanel();
      for (int i = 0; i < 5; i++) {
         SqudronJList SList = new SqudronJList();
         panel.add(SList.getList());
      }
      add(panel);
   }

   /**
    * Retourne une nouvelle fenêtre si aucune n'existe déjà ou l'instance de cette classe si elle a déjà été créée.
    *
    * @return L'unique instance de cette classe.
    */
   public static SquadronViewerWindow getInstance() {
      if (instance == null) {
         instance = new SquadronViewerWindow();
      }
      return instance;
   }
}

class SqudronJList {

   private final JList list;

   public SqudronJList() {
      DefaultListModel<String> squadronsListModel = new DefaultListModel<>();
      squadronsListModel.addElement("Stormtrooper: 6 / 53 / 23 / 62");
      squadronsListModel.addElement("Stormtrooper: 6 / 53 / 23 / 62");
      squadronsListModel.addElement("Stormtrooper: 6 / 53 / 23 / 62");
      squadronsListModel.addElement("");
      squadronsListModel.addElement("");
      squadronsListModel.addElement("");
      squadronsListModel.addElement("");
      squadronsListModel.addElement("");
      squadronsListModel.addElement("");
      squadronsListModel.addElement("");
      list = new JList<>(squadronsListModel);
   }

   public JList getList() {
      return list;
   }
}
