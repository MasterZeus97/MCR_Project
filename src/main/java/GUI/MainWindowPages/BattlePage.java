package GUI.MainWindowPages;

import Army.Army;
import Army.Combat;
import GUI.ArmyJList;
import GUI.MainWindow;
import Player.Player;

import javax.naming.SizeLimitExceededException;
import javax.swing.*;

/**
 * JPanel représentant le menu de combat.
 */
public class BattlePage extends MainWindowPage {

   private final Player player;
   private final Player enemy;

   private Combat combat;
   private Army alliedArmy;
   private Army enemyArmy;

   private final ArmyJList alliedArmyList;
   private final ArmyJList enemyArmyList;

   private boolean fighting;

   private final JButton quitButton;
   private final JButton fightButton;

   private final JLabel fightLabel;

   public BattlePage(MainWindow mw) {
      super(mw);

      quitButton = new JButton("Quitter guerre");
      quitButton.addActionListener(e -> {
         alliedArmy.clearArmy();
         mw.changeCard(MainWindow.CREATION_PAGE);
      });
      add(quitButton);

      fightButton = new JButton("Combattre");
      fightButton.addActionListener(e -> {
         startFight();
      });
      add(fightButton);

      fightLabel = new JLabel("[...]");
      add(fightLabel);

      player = mw.getPlayer();
      enemy = new Player();

      alliedArmy = mw.getArmy();
      try {

         // Debug only
         // player.generateArmy();
         // alliedArmy = player.getArmy();

         enemy.generateArmy();
         enemyArmy = enemy.getArmy();
      } catch (SizeLimitExceededException e) {
         e.printStackTrace();
      }

      alliedArmyList = new ArmyJList(alliedArmy, true);
      enemyArmyList = new ArmyJList(enemyArmy, false);

      add(alliedArmyList);
      add(enemyArmyList);

      combat = null;
   }

   public void openBattlePage() {
      alliedArmyList.update(-1);
      enemyArmyList.update(-1);

      fighting = false;

      combat = new Combat(alliedArmy, enemyArmy);
   }

   public void startFight() {
      if (combat == null) return;

      fighting = true;
      boolean playerWinned = true;

      quitButton.setEnabled(false);

      int turnCount = 0;
      while (!combat.isCombatFinished()) {
         combat.nextTurn();

         System.out.println(turnCount++ + (combat.isPlayerAttacking() ? " (+) " : " (-) ")
                 + ": " + combat.getTroupAttacker().getName()
                 + " attaque " + combat.getTroupAttacked().getName());
      }

      if (isArmyEmpty(alliedArmy)) {
         playerWinned = false;
      }

      fightLabel.setText(playerWinned ? "GAGNÉ!" : "PERDU!");

      if (playerWinned) {
         try {
            enemy.generateArmy();
         } catch (SizeLimitExceededException e) {
            e.printStackTrace();
         }
         enemyArmy = enemy.getArmy();
      }

      alliedArmyList.update(-1);
      enemyArmyList.update(-1);

      quitButton.setEnabled(true);
   }

   private boolean isArmyEmpty(Army army) {
      for (int i = 0; i < army.getSquadronsList().size(); i++) {
         if (army.getSquadronsList().get(i).getTroupNumber() != 0)
            return false;
      }
      return true;
   }
}
