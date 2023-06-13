package GUI.MainWindowPages;

import Army.Army;
import Army.Troups.Troup;
import Combat.Combat;
import GUI.ArmyJList;
import GUI.MainWindow;
import Player.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Page représentant le menu de combat.
 */
public class BattlePage extends MainWindowPage {

   private static final int MAX_TURNS = 1000000;

   private final Player player;
   private final Player enemy;

   private Combat combat;
   private final Army alliedArmy;
   private Army enemyArmy;

   private final ArmyJList alliedArmyList;
   private final ArmyJList enemyArmyList;

   private final JButton quitButton;
   private final JButton fightButton;

   /**
    * Construit la page.
    *
    * @param mw La fenêtre principale où la page est assignée.
    */
   public BattlePage(MainWindow mw) {
      super(mw);

      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.insets = new Insets(5, 5, 5, 5);

      player = mw.getPlayer();
      enemy = new Player();

      alliedArmy = mw.getArmy();
      enemyArmy = enemy.generateArmy();

      alliedArmyList = new ArmyJList(alliedArmy, true);
      enemyArmyList = new ArmyJList(enemyArmy, false);

      gbc.gridy++;
      gbc.gridx = 0;
      add(new JLabel("Votre armée"), gbc);
      gbc.gridx = 2;
      add(new JLabel("Armée ennemie"), gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      add(alliedArmyList, gbc);
      gbc.gridx = 2;
      add(enemyArmyList, gbc);

      fightButton = new JButton("Combattre");
      fightButton.addActionListener(e -> startFight());
      gbc.gridx = 1;
      add(fightButton, gbc);

      quitButton = new JButton("<html><center>Quitter guerre<p>(Attention: supprime l'armée!)</html>");
      quitButton.setHorizontalAlignment(SwingConstants.CENTER);
      quitButton.addActionListener(e -> quitBattlePage());
      gbc.gridy++;
      add(quitButton, gbc);

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

      setAllEnabled(false);

      LinkedList<ArrayList<String>> logs = new LinkedList<>();

      boolean playerWinned = true;
      int turnCount = 0;
      int moneyWinned = 0;
      while (!combat.isCombatFinished() && turnCount < MAX_TURNS) {
         combat.nextTurn();

         Troup attacker = combat.getTroupAttacker();
         Troup attacked = combat.getTroupAttacked();

         Troup a = attacker;
         Troup b = attacked;
         String action[] = {"Inflige ", " dmg à"};
         if (!combat.isPlayerAttacking()) {
            Troup c = a;
            a = b;
            b = c;
            action[0] = "Subit ";
            action[1] = " dmg de";
         }

         ArrayList<String> log = new ArrayList<>(Arrays.asList(
                 String.valueOf(++turnCount),
                 a.getName() + " [" + a.getHp() + " HP]",
                 action[0] + combat.getActiveDamageDealed() + action[1],
                 b.getName() + " [" + b.getHp() + " HP]"
         ));
         logs.add(log);

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

      JFrame gameOverWindow = new gameOverWindow(this, playerWinned, turnCount, moneyWinned, logs);
      gameOverWindow.setVisible(true);
   }

   /**
    * Défini si les composants de la page sont actifs ou non.
    *
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
       *
       * @param battlePage   La page utilisée pour le combat.
       * @param playerWinned true si le joueur a gagné, false si l'ennemi a gagné.
       * @param turnCount    Le nombre de tours écoulés durant le combat.
       * @param moneyWinned  L'argent gagné par le joueur durant le combat.
       * @param logs         Liste avec les informations de chaque 1vs1 du combat
       */
      // TODO : Singleton ?
      public gameOverWindow(BattlePage battlePage, boolean playerWinned, int turnCount, int moneyWinned, LinkedList<ArrayList<String>> logs) {

         // Paramètres de la fenêtre
         setTitle("GAME OVER");
         setSize(800, 800);
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

         setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.anchor = GridBagConstraints.CENTER;
         gbc.insets = new Insets(5, 5, 5, 5);

         add(new JLabel("Vous avez " + (playerWinned ? "gagné!" : "perdu!")), gbc);

         gbc.gridy++;
         add(new JLabel("Nombre de tours: " + turnCount), gbc);

         gbc.gridy++;
         add(new JLabel("Crédits gagnés: " + moneyWinned), gbc);

         gbc.gridy++;
         add(new JLabel("Logs du combat"), gbc);

         if (logs.size() > 0) {
            gbc.gridy++;
            Object[][] data = new Object[logs.size()][logs.get(0).size()];
            int i = 0;
            for (ArrayList<String> log : logs) {
               int j = 0;
               for (String s : log)
                  data[i][j++] = s;
               i++;
            }

            JScrollPane scrollPane = new JScrollPane(new JTable(data, new Object[]{"Tour", "Votre troupe", "Action", "Troupe ennemie"}));
            scrollPane.setMinimumSize(new Dimension(100, 100));
            add(scrollPane, gbc);
         }

         if (playerWinned) {
            JButton restartButton = new JButton("Continuer");
            restartButton.addActionListener(e -> {
               dispose();
               battlePage.setupPage();
            });
            gbc.gridy++;
            add(restartButton, gbc);
         }

         JButton quitButton = new JButton("Quitter");
         quitButton.addActionListener(e -> {
            if (!playerWinned || askCloseConfirmation()) {
               dispose();
               battlePage.quitBattlePage();
            }
         });
         gbc.gridy++;
         add(quitButton, gbc);
      }

      /**
       * Ouvre un pop-up pour demander la confirmation de fermer la fenêtre.
       *
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
