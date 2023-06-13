package GUI.MainWindowPages;

import Army.Squadron;
import Army.Stat;
import Army.Troups.Troup;
import GUI.ArmyJList;
import GUI.MainWindow;
import GUI.TroupGenerator;

import javax.swing.*;
import java.awt.*;

/**
 * Page représentant le menu de création d'unités.
 */
public class CreationPage extends MainWindowPage {

   private final static int GENERATE_PRICE = 100;

   private final static int IMAGE_MAX_WIDTH = 200;
   private final static int IMAGE_MAX_HEIGHT = 200;

   private final JLabel moneyLabel;
   private final JLabel imageLabel;
   private final JList<String> statsList;
   private final ArmyJList armyJList;

   private final JButton generateBtn;
   private final JButton cloneBtn;
   private final JButton startBtn;
   private final JButton debugBtn;

   private Troup actualTroup;
   private int actualSquadronIndex;

   /**
    * Construit la page.
    * @param mw La fenêtre principale où la page est assignée.
    */
   public CreationPage(MainWindow mw) {
      super(mw);

      setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

      imageLabel = new JLabel();
      add(imageLabel);

      // Compteur de crédits
      moneyLabel = new JLabel();
      updateMoney(0);
      add(moneyLabel);

      // Liste des stats de la troupe
      DefaultListModel<String> statsListModel = new DefaultListModel<>();
      statsList = new JList<>(statsListModel);
      add(statsList);

      // List des escadrilles
      armyJList = new ArmyJList(mw.getArmy(), true);
      armyJList.addListSelectionListener(e -> {
         if (!e.getValueIsAdjusting()) {

            // Sélectionne une escadrille dans la liste
            if (armyJList.getSelectedIndex() >= 0) {
               actualSquadronIndex = armyJList.getSelectedIndex();
            }
            updateButtons();
         }
      });
      actualSquadronIndex = 0;
      armyJList.update(0);
      add(armyJList);

      // Bouton générer
      generateBtn = new JButton("Générer troupe (" + GENERATE_PRICE + " crédits)");
      generateBtn.addActionListener(e -> {
         generateTroup();
         updateButtons();
         updateMoney(GENERATE_PRICE);
      });
      add(generateBtn);

      // Bouton cloner
      cloneBtn = new JButton("Cloner troupe");
      cloneBtn.addActionListener(e -> {
         Squadron squadron = mw.getArmy().getSquadron(actualSquadronIndex);

         if (!squadron.isFull()) {
            // Clone la troupe et l'ajoute à l'escadrille choisie
            squadron.add(actualTroup.copy());
            armyJList.update(actualSquadronIndex);

            // Baisse les stats de la troupe prototype
            actualTroup.drop();

            updateStatsList();
            updateButtons();
         }
      });
      add(cloneBtn);

      // Bouton debug (crée une armée complète)
      debugBtn = new JButton("Générer armée (debug)");
      debugBtn.addActionListener(e -> {
         mw.getPlayer().generateArmy();
         armyJList.update(actualSquadronIndex);
      });
      add(debugBtn);

      // Bouton commencer
      startBtn = new JButton("Commencer guerre");
      startBtn.addActionListener(e -> mw.changeCard(MainWindow.BATTLE_PAGE));
      add(startBtn);

      generateTroup();
      updateButtons();
   }

   @Override
   public void setupPage() {
      updateMoney(0);
      updateButtons();
      armyJList.update(0);
   }

   /**
    * Génère une nouvelle troupe et affiche ses stats et son image.
    */
   public void generateTroup() {
      actualTroup = TroupGenerator.getRandomTroup();
      updateStatsList();
      setImage(actualTroup.getName());
   }

   /**
    * Met à jour les boutons en les bloquant/débloquant.
    */
   private void updateButtons() {
      if (generateBtn == null || cloneBtn == null) return;

      int money = mw.getPlayer().getMoney();

      generateBtn.setEnabled(money >= GENERATE_PRICE);

      cloneBtn.setEnabled(actualTroup != null && !mw.getArmy().getSquadron(actualSquadronIndex).isFull());

      startBtn.setEnabled(!mw.getArmy().isEmpty());
   }

   /**
    * Débite un montant au joueur et met à jour l'affichage.
    * @param price Le montant à débiter.
    */
   private void updateMoney(int price) {
      if (mw.getPlayer().pay(price)) {
         moneyLabel.setText("Crédits = " + mw.getPlayer().getMoney());
      }
   }

   /**
    * Met à jour la liste de stats de la troupe actuellement affichée.
    */
   private void updateStatsList() {
      DefaultListModel<String> oldListModel = (DefaultListModel<String>) statsList.getModel();
      oldListModel.clear();
      for (Stat a : actualTroup.getStatsList()) {
         oldListModel.addElement(a.getName() + ": " + a.getValue());
      }
   }

   // TODO : Uniformiser les images pour pas devoir les redimensionner
   /**
    * Définit l'image à afficher.
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
