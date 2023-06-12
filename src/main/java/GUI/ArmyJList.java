package GUI;

import Army.Army;
import Army.Squadron;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Spécialisation de la classe JList pour afficher une armée (liste d'escadrilles).
 */
public class ArmyJList extends JList {

   private final Army army;
   private final DefaultListModel<String> model;
   private final boolean ally;

   private static SquadronViewerWindow[][] squadronViewerWindows;

   /**
    * Crée la liste.
    *
    * @param army L'armée à afficher.
    */
   public ArmyJList(Army army, boolean ally) {
      this.army = army;
      this.ally = ally;

      model = new DefaultListModel<>();
      setModel(model);

      squadronViewerWindows = new SquadronViewerWindow[army.getMaxSize()][2];

      // Gestion du double clic
      addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {

               int x = getSelectedIndex();
               int y = (ally ? 0 : 1);

               if (squadronViewerWindows[x][y] == null) {
                  squadronViewerWindows[x][y] = new SquadronViewerWindow((ArmyJList) e.getSource(), x);
                  squadronViewerWindows[x][y].setVisible(true);
               }
            }
         }
      });
   }

   /**
    * Met à jour l'affichage de la liste.
    *
    * @param selectedIndex L'index où doit se trouver le curseur de la souris. Si la valeur est < 0, aucun curseur n'est affiché.
    */
   public void update(int selectedIndex) {
      model.clear();
      for (int i = 0; i < army.getMaxSize(); i++) {
         Squadron s = army.getSquadron(i);
         model.addElement("Squadron " + i + ": (" + s.getTroupNumber() + "/" + s.getMaxSize() + ")");

         updateSVW(i);
      }

      if (selectedIndex >= 0) {
         setSelectedIndex(selectedIndex);
      }
   }

   public Army getArmy() {
      return army;
   }

   public boolean isAlly() {
      return ally;
   }

   public void updateSVW(int x) {
      int y = (ally ? 0 : 1);
      if (squadronViewerWindows[x][y] == null) return;
      squadronViewerWindows[x][y].update();
   }

   public void clearSVW(int x) {
      int y = (ally ? 0 : 1);
      squadronViewerWindows[x][y] = null;
   }
}
