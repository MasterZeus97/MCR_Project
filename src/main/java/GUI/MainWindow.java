package GUI;

import Army.*;
import GUI.MainWindowPages.BattlePage;
import GUI.MainWindowPages.CreationPage;
import GUI.MainWindowPages.TitlePage;
import Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale qui gère les différents menus du jeu.
 */
public class MainWindow extends JFrame {

   private final Player player;

   private final CardLayout cardLayout;
   private final JPanel cardPanel;

   private final TitlePage titlePage;
   private final CreationPage creationPage;
   private final BattlePage battlePage;

   public final static String TITLE_PAGE = "titlePage";
   public final static String CREATION_PAGE = "creationPage";
   public final static String BATTLE_PAGE = "battlePage";

   /**
    * Crée une nouvelle fenêtre.
    * @param player Le profil du joueur
    */
   public MainWindow(Player player) {
      this.player = player;

      // Paramètres de la fenêtre
      setTitle("");
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Différentes pages
      cardLayout = new CardLayout();
      cardPanel = new JPanel(cardLayout);

      titlePage = new TitlePage(this);
      cardPanel.add(titlePage, TITLE_PAGE);

      creationPage = new CreationPage(this);
      cardPanel.add(creationPage, CREATION_PAGE);

      battlePage = new BattlePage(this);
      cardPanel.add(battlePage, BATTLE_PAGE);

      add(cardPanel);

      changeCard(TITLE_PAGE);
   }

   /**
    * Change la page affichée sur la fenêtre
    * @param card Le nom de la page à afficher
    */
   public void changeCard(String card) {
      cardLayout.show(cardPanel, card);

      switch (card) {
         case TITLE_PAGE -> titlePage.setupPage();
         case CREATION_PAGE -> creationPage.setupPage();
         case BATTLE_PAGE -> battlePage.setupPage();
      }
   }

   /**
    * Retourne le joueur actuel.
    * @return Le joueur actuel.
    */
   public Player getPlayer() {
      return player;
   }

   /**
    * Retourne l'armée du joueur actuel.
    * @return L'armée du joueur actuel.
    */
   public Army getArmy() {
      return getPlayer().getArmy();
   }
}

