package GUI.MainWindowPages;

import Army.Army;
import Combat.Combat;
import GUI.ArmyJList;
import GUI.MainWindow;
import Player.Player;

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
      enemy.generateArmy();
      enemyArmy = enemy.getArmy();

      alliedArmyList = new ArmyJList(alliedArmy, true);
      enemyArmyList = new ArmyJList(enemyArmy, false);

      add(alliedArmyList);
      add(enemyArmyList);

      combat = null;
   }

   public void openBattlePage() {
      alliedArmyList.update(-1);
      enemyArmyList.update(-1);

      combat = new Combat(alliedArmy, enemyArmy);
   }

   public void startFight() {
      if (combat == null) return;

      boolean playerWinned = true;

      quitButton.setEnabled(false);

      int turnCount = 0;
      while (!combat.isCombatFinished()) {
         combat.nextTurn();

         System.out.println(turnCount++ + (combat.isPlayerAttacking() ? " (+) " : " (-) ")
                 + ": " + combat.getTroupAttacker().getName()
                 + " attaque " + combat.getTroupAttacked().getName());
      }

      if (alliedArmy.isEmpty()) {
         playerWinned = false;
      }

      fightLabel.setText(playerWinned ? "GAGNÉ!" : "PERDU!");

      if (playerWinned) {
         enemy.generateArmy();
         enemyArmy = enemy.getArmy();
      }

      alliedArmyList.update(-1);
      enemyArmyList.update(-1);

      quitButton.setEnabled(true);
   }
}
