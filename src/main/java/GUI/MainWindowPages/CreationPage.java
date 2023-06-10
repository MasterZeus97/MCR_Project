package GUI.MainWindowPages;

import Army.Army;
import Army.Squadron;
import Army.Stat;
import Army.Troups.Troup;
import GUI.MainWindow;
import GUI.SquadronViewerWindow;
import GUI.TroupGenerator;

import javax.naming.SizeLimitExceededException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPanel représentant le menu de création d'unités.
 */
public class CreationPage extends MainWindowPage {

   private final static int GENERATE_PRICE = 100;
   private final static int CLONE_PRICE = 50;

   private final static int IMAGE_MAX_WIDTH = 200;
   private final static int IMAGE_MAX_HEIGHT = 200;

   private final JLabel moneyLabel;
   private final JLabel imageLabel;
   private final JList<String> statsList;
   private final JList<String> squadronsList;

   private boolean payGenerate;
   private final JButton generateBtn;
   private final JButton cloneBtn;

   private Troup actualTroup;

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
      DefaultListModel<String> squadronsListModel = new DefaultListModel<>();
      squadronsList = new JList<>(squadronsListModel);
      updateSquadronsList();
      add(squadronsList);

      // Bouton générer
      generateBtn = new JButton("Générer troupe (" + GENERATE_PRICE + " crédits)");
      generateBtn.addActionListener(e -> {
         actualTroup = TroupGenerator.getRandomTroup();
         setStatsList((ArrayList<Stat>) actualTroup.getStatsList());
         setImage(actualTroup.getName());

         updateMoney(payGenerate ? GENERATE_PRICE : 0);
         updateButtons();
      });
      add(generateBtn);

      // Bouton cloner
      cloneBtn = new JButton("Cloner troupe (" + CLONE_PRICE + " crédits)");
      cloneBtn.addActionListener(e -> {
         try {
            mw.getArmy().getSquadron(0).add(actualTroup.copy());
            updateSquadronsList();
         } catch (SizeLimitExceededException ex) {
            ex.printStackTrace();
         }

         actualTroup.drop();

         updateMoney(CLONE_PRICE);
         updateButtons();
      });
      add(cloneBtn);

      // Bouton commencer
      JButton startBtn = new JButton("Commencer bataille");
      startBtn.addActionListener(e -> {
         mw.changeCard(MainWindow.BATTLE_PAGE);
         // TODO : Créer objet Simulation
      });
      add(startBtn);

      // Bouton voir bataillons
      JButton squadronBtn = new JButton("Voir bataillons");
      squadronBtn.addActionListener(e -> {
         SquadronViewerWindow sqw = SquadronViewerWindow.getInstance(mw.getArmy());
         sqw.setVisible(true);
         sqw.update(0);
      });
      add(squadronBtn);

      // Simule un clic sur le bouton générer sans déduire l'argent du joueur
      payGenerate = false;
      generateBtn.doClick();
      payGenerate = true;

      updateButtons();
   }

   /**
    * Met à jour les boutons en les bloquant/débloquant.
    */
   private void updateButtons() {
      int money = mw.getPlayer().getMoney();
      generateBtn.setEnabled(money >= GENERATE_PRICE);
      cloneBtn.setEnabled(money >= CLONE_PRICE && actualTroup != null);
   }

   /**
    * Débite un montant au joueur et met à jour l'affichage.
    *
    * @param price Le montant à débiter.
    */
   private void updateMoney(int price) {
      if (mw.getPlayer().pay(price)) {
         moneyLabel.setText("Crédits = " + mw.getPlayer().getMoney());
      }
   }

   /**
    * Définit la liste avec les statistiques de la troupe actuellement affichée.
    *
    * @param newStats La liste des stats à afficher.
    */
   private void setStatsList(ArrayList<Stat> newStats) {
      DefaultListModel<String> oldListModel = (DefaultListModel<String>) statsList.getModel();
      oldListModel.clear();
      for (Stat a : newStats) {
         oldListModel.addElement(a.getName() + ": " + a.getValue());
      }
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

   public void updateSquadronsList() {
      DefaultListModel<String> squadronsListModel = (DefaultListModel<String>) squadronsList.getModel();
      squadronsListModel.clear();

      Army army = mw.getArmy();
      for (int i = 0; i < army.getMaxSize(); i++) {
         Squadron s = army.getSquadron(i);
         squadronsListModel.addElement("Squadron " + i + ": (" + s.getTroupNumber() + "/" + s.getMaxSize() + ")");
      }
   }
}
