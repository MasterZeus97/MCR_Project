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
