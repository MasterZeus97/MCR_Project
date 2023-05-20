package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fenêtre principale qui gère les différents menus du jeu.
 */
public class MainWindow extends JFrame implements ActionListener {

   public CardLayout cardLayout;
   public JPanel cardPanel;

   /**
    * Crée une nouvelle fenêtre.
    */
   public MainWindow() {
      setTitle("NOM DU JEU");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

      cardLayout = new CardLayout();

      cardPanel = new JPanel(cardLayout);
      cardPanel.add(new TitlePage(), "titlePage");
      cardPanel.add(new CreationPage(), "creationPage");
      cardPanel.add(new BattlePage(), "battlePage");

      add(cardPanel);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();

      cardLayout.show(cardPanel, command);
   }

   /**
    * JPanel représentant l'écran titre du jeu.
    */
   private class TitlePage extends JPanel {
      public TitlePage() {
         JButton button = new JButton("Jouer");
         button.setActionCommand("creationPage");
         button.addActionListener(MainWindow.this);
         add(button);
      }
   }

   /**
    * JPanel représentant le menu de création d'unités.
    */
   private class CreationPage extends JPanel {
      public CreationPage() {
         JButton button = new JButton("Commencer bataille");
         button.setActionCommand("battlePage");
         button.addActionListener(MainWindow.this);
         add(button);

         JButton button2 = new JButton("Voir bataillons");
         button2.addActionListener(e -> {
            SquadronViewerWindow sqw = SquadronViewerWindow.getInstance();
            sqw.setVisible(true);
         });
         add(button2);
      }
   }

   /**
    * JPanel représentant le menu de combat.
    */
   private class BattlePage extends JPanel {
      public BattlePage() {
         JButton button = new JButton("Terminer bataille");
         button.setActionCommand("creationPage");
         button.addActionListener(MainWindow.this);
         add(button);}
   }
}
