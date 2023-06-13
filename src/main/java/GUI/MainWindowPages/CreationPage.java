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

   private final static int IMAGE_MAX_WIDTH = 500;
   private final static int IMAGE_MAX_HEIGHT = 500;

   private final JLabel moneyLabel;
   private final JLabel imageLabel;
   private final JLabel nameLabel;
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

      // PANNEAU DE GAUCHE //

      JPanel buttonsPanel = new JPanel();
      buttonsPanel.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.insets = new Insets(5, 5, 5, 5);

      // Nom de la troupe
      nameLabel = new JLabel();
      gbc.gridx++;
      buttonsPanel.add(nameLabel, gbc);

      // Liste des stats de la troupe
      DefaultListModel<String> statsListModel = new DefaultListModel<>();
      statsList = new JList<>(statsListModel);
      gbc.gridy++;
      buttonsPanel.add(statsList, gbc);

      // Compteur de crédits
      moneyLabel = new JLabel();
      updateMoney(0);
      gbc.gridy++;
      buttonsPanel.add(moneyLabel, gbc);

      // List des escadrons
      armyJList = new ArmyJList(mw.getArmy(), true);
      armyJList.addListSelectionListener(e -> {
         if (!e.getValueIsAdjusting()) {

            // Sélectionne un escadron dans la liste
            if (armyJList.getSelectedIndex() >= 0) {
               actualSquadronIndex = armyJList.getSelectedIndex();
            }
            updateButtons();
         }
      });
      actualSquadronIndex = 0;
      armyJList.update(0);
      gbc.gridy++;
      buttonsPanel.add(armyJList, gbc);

      // Bouton générer
      generateBtn = new JButton("<html><center>Générer troupe<p>(" + GENERATE_PRICE + " crédits)</html>");
      generateBtn.setHorizontalAlignment(SwingConstants.CENTER);
      generateBtn.addActionListener(e -> {
         generateTroup();
         updateButtons();
         updateMoney(GENERATE_PRICE);
      });
      gbc.gridy++;
      buttonsPanel.add(generateBtn, gbc);

      // Bouton cloner
      cloneBtn = new JButton("Cloner troupe");
      cloneBtn.addActionListener(e -> {
         Squadron squadron = mw.getArmy().getSquadron(actualSquadronIndex);

         if (!squadron.isFull()) {
            // Clone la troupe et l'ajoute à l'escadron choisi
            squadron.add(actualTroup.copy());
            armyJList.update(actualSquadronIndex);

            // Baisse les stats de la troupe prototype
            actualTroup.drop();

            updateStatsList();
            updateButtons();
         }
      });
      gbc.gridy++;
      buttonsPanel.add(cloneBtn, gbc);

      // Bouton debug (crée une armée complète)
      debugBtn = new JButton("<html><center>Générer armée<p>(debug)</html>");
      debugBtn.setHorizontalAlignment(SwingConstants.CENTER);
      debugBtn.addActionListener(e -> {
         mw.getPlayer().generateArmy();
         armyJList.update(actualSquadronIndex);
      });
      gbc.gridy++;
      buttonsPanel.add(debugBtn, gbc);

      // Bouton commencer
      startBtn = new JButton("Commencer guerre");
      startBtn.addActionListener(e -> mw.changeCard(MainWindow.BATTLE_PAGE));
      gbc.gridy++;
      buttonsPanel.add(startBtn, gbc);

      // PANNEAU DE DROITE //

      JPanel imagePanel = new JPanel();

      // Image de la troupe
      imageLabel = new JLabel();
      imagePanel.add(imageLabel);

      JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPanel, imagePanel);
      splitPane.setResizeWeight(0.3);
      setLayout(new BorderLayout());
      add(splitPane, BorderLayout.CENTER);

      generateTroup();
      updateButtons();
   }

   @Override
   public void setupPage() {
      updateMoney(0);
      updateButtons();
      armyJList.update(0);

      mw.pack();
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

      nameLabel.setText(troupName);
      imageLabel.setIcon(imageIcon);
   }
}
