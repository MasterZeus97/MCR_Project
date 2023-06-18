package GUI;

import Army.Squadron;
import Army.Troups.Troup;
import Army.Stat;
import Army.Army;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Fenêtre affichant un escadron avec la liste de ses troupes.
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
public class SquadronViewerWindow extends JFrame {

   private final ArmyJList armyJList;
   private final int id;

   private final DefaultListModel<String> model;

   private final JButton clearButton;
   private final JButton cloneButton;

   /**
    * Construit une fenêtre.
    * @param armyJList La liste des escadrons utilisée pour créer cette fenêtre.
    * @param id L'id de l'escadron dans cette liste.
    */
   public SquadronViewerWindow(ArmyJList armyJList, int id) {
      this.armyJList = armyJList;
      this.id = id;

      setTitle("Escadron " + (armyJList.isAlly() ? "allié " : "ennemi ") + (id + 1));
      setMinimumSize(new Dimension(300, 0));
      setResizable(false);
      setLocationRelativeTo(null);

      JPanel panel = new JPanel();

      model = new DefaultListModel<>();
      panel.add(new JList(model));

      clearButton = new JButton("Vider escadron");
      clearButton.addActionListener(e -> {
         armyJList.getArmy().getSquadron(id).clearSquadron();
         armyJList.update(-1);
      });

      cloneButton = new JButton("Cloner escadron");
      cloneButton.addActionListener(e -> {
         int emptySquadronIndex = getEmptySquadron();
         if (emptySquadronIndex >= 0) {
            armyJList.getArmy().setSquadron(emptySquadronIndex, getMySquadron().copy());
            armyJList.update(-1);
         }
      });

      if (armyJList.isAlly()) {
         panel.add(clearButton);
         panel.add(cloneButton);
      }

      add(panel);
      pack();

      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            dispose();
            armyJList.clearSVW(id);
         }
      });

      update();
   }

   /**
    * Met à jour l'affichage des composants de la fenêtre.
    */
   public void update() {
      model.clear();

      Squadron squadron = getMySquadron();

      for (Troup t : squadron.getTroupList()) {

         StringBuilder stats = new StringBuilder();
         for (Stat stat : t.getStatsList()) {
            stats.append(stat.getName()).append(" = ").append(stat.getValue()).append(" | ");
         }

         model.addElement(t.getName() + ": " + stats);
      }

      int emptySize = squadron.getMaxSize() - squadron.getTroupNumber();
      for (int i = 0; i < emptySize; i++) {
         model.addElement("[ VIDE ]");
      }

      clearButton.setEnabled(armyJList.isAlly() && !getMySquadron().isEmpty());
      cloneButton.setEnabled(clearButton.isEnabled() && getEmptySquadron() >= 0);

      pack();
   }

   /**
    * Retourne l'escadron affichée.
    * @return L'escadron affichée.
    */
   public Squadron getMySquadron() {
      return armyJList.getArmy().getSquadron(id);
   }

   /**
    * Permet de savoir s'il existe une escadron vide dans l'armée utilisée.
    * @return L'index de la première escadron vide de l'armée, -1 s'il n'en existe pas.
    */
   public int getEmptySquadron() {
      Army army = armyJList.getArmy();

      int i = 0;
      for (Squadron s : army.getSquadronsList()) {
         if (s.isEmpty()) {
            return i;
         }
         i++;
      }

      return -1;
   }
}
