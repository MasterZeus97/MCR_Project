package GUI;

import Army.Army;
import Army.Squadron;
import Army.Troups.Troup;
import Army.Stat;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Fenêtre secondaire permettant de visualiser les bataillons et leur contenu.
 */
public class SquadronViewerWindow extends JFrame {
   private static SquadronViewerWindow instance = null;

   private final Army army;
   private ArrayList<SqudronJList> sLists;

   /**
    * Crée une nouvelle fenêtre.
    * Le constructeur est privé, car cette classe est un Singleton.
    *
    * @param army L'armée à afficher.
    */
   private SquadronViewerWindow(Army army) {
      setTitle("Squadron Viewer");
      setSize(400, 300);
      setLocationRelativeTo(null);

      this.army = army;

      sLists = new ArrayList<>();
      JPanel panel = new JPanel();
      for (int i = 0; i < army.getMaxSize(); i++) {
         panel.add(new JLabel("Squadron " + (i+1)));

         SqudronJList list = new SqudronJList(i);
         sLists.add(list);
         sLists.get(i).update();
         panel.add(list.getList());
      }
      add(panel);
   }

   /**
    * Met à jour une liste de squadrons.
    *
    * @param i L'id de la liste.
    */
   public void update(int i) {
      if (sLists != null) {
         sLists.get(i).update();
      }
   }

   /**
    * Modèle singleton.
    * Retourne une nouvelle fenêtre si aucune n'existe déjà ou l'instance de cette classe si elle a déjà été créée.
    *
    * @return L'unique instance de cette classe.
    */
   public static SquadronViewerWindow getInstance(Army army) {
      if (instance == null) {
         instance = new SquadronViewerWindow(army);
      }
      return instance;
   }

   /**
    * Classe représentant une liste de squadrons.
    */
   class SqudronJList {

      private final int id;
      private final JList list;

      public SqudronJList(int id) {
         this.id = id;
         list = new JList<>(new DefaultListModel());
      }

      /**
       * Met à jour toutes les listes de squadrons.
       */
      public void update() {

         Squadron s = army.getSquadron(id);

         DefaultListModel sqListModel = (DefaultListModel) list.getModel();
         sqListModel.clear();

         for (Troup t : s.getTroupList()) {

            StringBuilder stats = new StringBuilder();
            for (Stat stat : t.getStatsList()) {
               stats.append(stat.getName()).append(" = ").append(stat.getValue()).append(" | ");
            }

            sqListModel.addElement(t.getName() + ": " + stats);
         }

         int emptySize = s.getMaxSize() - s.getTroupNumber();
         for (int i = 0; i < emptySize; i++) {
            sqListModel.addElement("- - -");
         }

      }

      /**
       * Retourne la JList avec les squadrons.
       *
       * @return La liste de squadrons.
       */
      public JList getList() {
         return list;
      }
   }
}
