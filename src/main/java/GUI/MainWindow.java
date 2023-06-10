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

   public final static String TITLE_PAGE = "titlePage";
   public final static String CREATION_PAGE = "creationPage";
   public final static String BATTLE_PAGE = "battlePage";

   /**
    * Crée une nouvelle fenêtre.
    */
   public MainWindow(Player player) {
      this.player = player;

      // Paramètres de la fenêtre
      setTitle("NOM DU JEU");
      setSize(800, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

      // Différentes pages
      cardLayout = new CardLayout();
      cardPanel = new JPanel(cardLayout);
      cardPanel.add(new TitlePage(this), TITLE_PAGE);
      cardPanel.add(new CreationPage(this), CREATION_PAGE);
      cardPanel.add(new BattlePage(this), BATTLE_PAGE);
      add(cardPanel);
   }

   public void changeCard(String card) {
      cardLayout.show(cardPanel, card);
   }

   public Player getPlayer() {
      return player;
   }

   public Army getArmy() {
      return getPlayer().getArmy();
   }
}

