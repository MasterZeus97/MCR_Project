package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale qui gère les différents menus du jeu.
 */
public class MainWindow extends JFrame {

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

   public void changeCard(String card) {
      cardLayout.show(cardPanel, card);
   }

   /**
    * JPanel représentant l'écran titre du jeu.
    */
   private class TitlePage extends JPanel {
      public TitlePage() {
         add(new JLabel("NOM DU JEU"));
         JButton button = new JButton("Jouer");
         button.addActionListener(e -> changeCard("creationPage"));
         add(button);
      }
   }

   /**
    * JPanel représentant le menu de création d'unités.
    */
   private class CreationPage extends JPanel {
      public CreationPage() {

         JPanel leftPanel = new JPanel();
         JPanel rightPanel = new JPanel();

         setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

         JButton generateBtn = new JButton("Générer troupe");
         // TODO : Fonction
         leftPanel.add(generateBtn);

         JButton cloneBtn = new JButton("Cloner troupe");
         // TODO : Fonction
         leftPanel.add(cloneBtn);

         JButton startBtn = new JButton("Commencer bataille");
         startBtn.addActionListener(e -> changeCard("battlePage"));

         // TODO : Créer objet Simulation
         leftPanel.add(startBtn);

         JLabel stats = new JLabel("STATS:\n x\n y\n z\n");
         rightPanel.add(stats);

         JLabel money = new JLabel("Argent: ??? crédits");
         rightPanel.add(money);

         JButton squadronBtn = new JButton("Voir bataillons");
         squadronBtn.addActionListener(e -> {
            SquadronViewerWindow sqw = SquadronViewerWindow.getInstance();
            sqw.setVisible(true);
         });
         rightPanel.add(squadronBtn);

         add(leftPanel);
         add(rightPanel);
      }
   }

   /**
    * JPanel représentant le menu de combat.
    */
   private class BattlePage extends JPanel {
      public BattlePage() {
         JButton button = new JButton("Terminer bataille");
         button.addActionListener(e -> changeCard("creationPage"));
         add(button);}
   }
}
