package GUI;

import Army.Troups.*;
import Army.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Fenêtre principale qui gère les différents menus du jeu.
 */
public class MainWindow extends JFrame {

   private final CardLayout cardLayout;
   private final JPanel cardPanel;

   private Troup actualTroup;

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

      private final static int START_MONEY = 1000;
      private final static int GENERATE_PRICE = 100;
      private final static int CLONE_PRICE = 50;

      private final static int IMAGE_MAX_WIDTH = 100;
      private final static int IMAGE_MAX_HEIGHT = 100;

      private int moneyCount;
      private final JLabel moneyLabel;

      private final JLabel imageLabel;

      private final JList<String> statsList;

      public CreationPage() {

         setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

         // Image
         imageLabel = new JLabel("---");
         add(imageLabel);

         // Compteur de crédits
         moneyCount = START_MONEY;
         moneyLabel = new JLabel();
         setMoney(moneyCount);
         add(moneyLabel);

         // Liste des stats de la troupe
         DefaultListModel<String> listModel = new DefaultListModel<>();
         statsList = new JList<>(listModel);
         add(statsList);

         // Bouton générer
         JButton generateBtn = new JButton("Générer troupe");
         generateBtn.addActionListener(e -> {
            actualTroup = new Stormtrooper();
            setStatsList((ArrayList<Attributs>) actualTroup.getAttributsMap());

            setMoney(moneyCount - GENERATE_PRICE);
            setImage("Darth Vader");
         });
         add(generateBtn);

         // Bouton cloner
         JButton cloneBtn = new JButton("Cloner troupe");
         cloneBtn.addActionListener(e -> {
            Troup copiedTroup = actualTroup.copy();
            setStatsList((ArrayList<Attributs>) copiedTroup.getAttributsMap());

            setMoney(moneyCount - CLONE_PRICE);
            setImage("Stormtrooper");
         });
         add(cloneBtn);

         // Bouton commencer
         JButton startBtn = new JButton("Commencer bataille");
         startBtn.addActionListener(e -> {
            changeCard("battlePage");
            // TODO : Créer objet Simulation
         });
         add(startBtn);

         // Bouton voir bataillons
         JButton squadronBtn = new JButton("Voir bataillons");
         squadronBtn.addActionListener(e -> {
            SquadronViewerWindow sqw = SquadronViewerWindow.getInstance();
            sqw.setVisible(true);
         });
         add(squadronBtn);
      }

      /**
       * Définir la liste avec les statistiques de la troupe actuellement affichée.
       *
       * @param newStats La liste des stats à afficher.
       */
      private void setStatsList(ArrayList<Attributs> newStats) {
         DefaultListModel<String> oldListModel = (DefaultListModel<String>) statsList.getModel();
         oldListModel.clear();
         for (Attributs a : newStats) {
            oldListModel.addElement(a.getName() + ": " + a.getValue());
         }
      }

      /**
       * Défini la valeur du compteur de crédits.
       *
       * @param val La nouvelle somme de crédits.
       */
      private void setMoney(int val) {
         moneyCount = val;
         moneyLabel.setText("Crédits = " + moneyCount);
      }

      /**
       * Définit l'image à afficher.
       *
       * @param troupName Le nom de la troupe.
       */
      private void setImage(String troupName) {
         ImageIcon originalImageIcon = new ImageIcon("img/" + troupName + ".png");
         Image image = originalImageIcon.getImage();

         int maxWidth = IMAGE_MAX_WIDTH; // Largeur maximale souhaitée
         int maxHeight = IMAGE_MAX_HEIGHT; // Hauteur maximale souhaitée

         int originalWidth = image.getWidth(null);
         int originalHeight = image.getHeight(null);

         int newWidth = originalWidth;
         int newHeight = originalHeight;

         // Vérification si un redimensionnement est nécessaire
         if (originalWidth > maxWidth || originalHeight > maxHeight) {
            double widthRatio = (double) maxWidth / originalWidth;
            double heightRatio = (double) maxHeight / originalHeight;

            double scaleFactor = Math.min(widthRatio, heightRatio);

            newWidth = (int) (originalWidth * scaleFactor);
            newHeight = (int) (originalHeight * scaleFactor);
         }

         Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
         ImageIcon imageIcon = new ImageIcon(resizedImage);

         imageLabel.setText(troupName);
         imageLabel.setIcon(imageIcon);
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
