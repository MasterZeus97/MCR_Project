package GUI;

import Army.Squadron;
import Army.Troups.Troup;
import Army.Stat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SquadronViewerWindow extends JFrame {

   private final ArmyJList armyJList;
   private final int id;

   private final DefaultListModel<String> model;

   public SquadronViewerWindow(ArmyJList armyJList, int id) {
      setTitle("Squadron Viewer");
      setResizable(false);
      setLocationRelativeTo(null);

      this.armyJList = armyJList;
      this.id = id;

      JPanel panel = new JPanel();

      add(new JLabel("Squadron " + id));

      model = new DefaultListModel<>();
      panel.add(new JList(model));
      update();

      if (armyJList.isAlly()) {

         JButton clearButton = new JButton("Vider escadrille");
         clearButton.addActionListener(e -> {
            armyJList.getArmy().getSquadron(id).clearSquadron();
            armyJList.update(-1);
            update();
         });
         panel.add(clearButton);

         // TODO
         JButton cloneButton = new JButton("Cloner escadrille");
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
   }

   public void update() {
      model.clear();

      Squadron squadron = armyJList.getArmy().getSquadron(id);

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

      pack();
   }
}
