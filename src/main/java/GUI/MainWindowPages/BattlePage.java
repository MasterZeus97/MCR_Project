package GUI.MainWindowPages;

import Army.Army;
import Army.Troups.Troup;
import Combat.Combat;
import GUI.ArmyJList;
import GUI.MainWindow;
import Player.Player;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Page représentant le menu de combat.
 */
public class BattlePage extends MainWindowPage {

   private static final int MAX_TURNS = 1000000;

   private final Player player;
   private final Player enemy;

   private Combat combat;
   private Army alliedArmy;
   private Army enemyArmy;

   private final ArmyJList alliedArmyList;
   private final ArmyJList enemyArmyList;

   private final JButton quitButton;
   private final JButton fightButton;

   /**
    * Construit la page.
    * @param mw La fenêtre principale où la page est assignée.
    */
   public BattlePage(MainWindow mw) {
      super(mw);

      quitButton = new JButton("Quitter guerre (Attention: supprime l'armée!)");
      quitButton.addActionListener(e -> {
         quitBattlePage();
      });
      add(quitButton);

      fightButton = new JButton("Combattre");
      fightButton.addActionListener(e -> {
         startFight();
      });
      add(fightButton);

      player = mw.getPlayer();
      enemy = new Player();

      alliedArmy = mw.getArmy();
      enemyArmy = enemy.generateArmy();

      alliedArmyList = new ArmyJList(alliedArmy, true);
      enemyArmyList = new ArmyJList(enemyArmy, false);

      add(alliedArmyList);
      add(enemyArmyList);

      combat = null;
   }

   @Override
   public void setupPage() {
      enemyArmy = enemy.generateArmy();

      alliedArmyList.update(-1);
      enemyArmyList.update(-1);

      combat = new Combat(alliedArmy, enemyArmy);

      setAllEnabled(true);
   }

   /**
    * Quitte le mode bataille et retourne à la page de création de troupes.
    */
   public void quitBattlePage() {
      alliedArmy.clearArmy();
      mw.changeCard(MainWindow.CREATION_PAGE);
   }

   /**
    * Débute un combat entre les 2 armées définies.
    */
   public void startFight() {
      if (combat == null) return;

      boolean playerWinned = true;

      setAllEnabled(false);

      int turnCount = 0;
      int moneyWinned = 0;
      while (!combat.isCombatFinished() && turnCount < MAX_TURNS) {
         combat.nextTurn();

         Troup attacker = combat.getTroupAttacker();
         Troup attacked = combat.getTroupAttacked();

         // TODO : Afficher une liste avec les logs ?
         System.out.println(++turnCount + (combat.isPlayerAttacking() ? " (+) " : " (-) ")
                 + ": " + attacker.getName() + " [" + attacker.getHp() + " HP]"
                 + " attaque " + attacked.getName() + " [" + attacked.getHp() + " HP]");

         if (combat.isPlayerAttacking() && !attacked.isAlive()) {
            moneyWinned += attacked.defeatedMoney();
         }
      }

      if (alliedArmy.isEmpty()) {
         playerWinned = false;
      }

      player.getPayed(moneyWinned);

      alliedArmyList.update(-1);
      enemyArmyList.update(-1);

      if (playerWinned) {
         enemy.generateArmy();
         enemyArmy = enemy.getArmy();
      }

      JFrame gameOverWindow = new gameOverWindow(this, playerWinned, turnCount, moneyWinned);
      gameOverWindow.setVisible(true);
   }

   /**
    * Défini si les composants de la page sont actifs ou non.
    * @param enabled true pour activer les composants, false sinon
    */
   private void setAllEnabled(boolean enabled) {
      quitButton.setEnabled(enabled);
      fightButton.setEnabled(enabled);
      alliedArmyList.setEnabled(enabled);
      enemyArmyList.setEnabled(enabled);
   }

   /**
    * Petite fenêtre affichée lorsqu'un combat est terminé
    */
   private static class gameOverWindow extends JFrame {

      /**
       * Crée la fenêtre de Game Over.
       * @param battlePage La page utilisée pour le combat.
       * @param playerWinned true si le joueur a gagné, false si l'ennemi a gagné.
       * @param turnCount Le nombre de tours écoulés durant le combat.
       * @param moneyWinned L'argent gagné par le joueur durant le combat.
       */
      // TODO : Singleton ?
      public gameOverWindow(BattlePage battlePage, boolean playerWinned, int turnCount, int moneyWinned) {

         // Paramètres de la fenêtre
         setTitle("GAME OVER");
         setResizable(false);
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               if (!playerWinned || askCloseConfirmation()) {
                  dispose();
                  battlePage.quitBattlePage();
               }
            }
         });
         setLocationRelativeTo(null);

         JPanel panel = new JPanel();

         panel.add(new JLabel("Vous avez " + (playerWinned ? "gagné!" : "perdu!")));
         panel.add(new JLabel("Nombre de tours: " + turnCount));
         panel.add(new JLabel("Crédits gagnés: " + moneyWinned));

         JButton quitButton = new JButton("Quitter");
         quitButton.addActionListener(e -> {
            if (!playerWinned || askCloseConfirmation()) {
               dispose();
               battlePage.quitBattlePage();
            }
         });
         panel.add(quitButton);

         if (playerWinned) {
            JButton restartButton = new JButton("Continuer");
            restartButton.addActionListener(e -> {
               dispose();
               battlePage.setupPage();
            });
            panel.add(restartButton);
         }

         add(panel);
         pack();
      }

      /**
       * Ouvre un pop-up pour demander la confirmation de fermer la fenêtre.
       * @return true si l'utilisateur a confirmé la fermeture de la fenêtre, false sinon.
       */
      private boolean askCloseConfirmation() {
         int option = JOptionPane.showConfirmDialog(this,
                 "Êtes-vous sûr de vouloir quitter?\nVotre armée sera supprimée!",
                 "", JOptionPane.YES_NO_OPTION);
         return option == JOptionPane.YES_OPTION;
      }
   }
}
