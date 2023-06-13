package GUI.MainWindowPages;

import GUI.MainWindow;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Page représentant l'écran titre du jeu.
 */
public class TitlePage extends MainWindowPage {

   /**
    * Construit la page.
    * @param mw La fenêtre principale où la page est assignée.
    */
   public TitlePage (MainWindow mw) {
      super(mw);

      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      JLabel imageLabel = new JLabel(new ImageIcon("img/titleScreen.png"));
      imageLabel.setLayout(new BorderLayout());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);
      JButton button = new JButton("Jouer") {
         @Override
         protected void paintComponent(Graphics g) {
            final Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0),
                    Color.YELLOW.brighter(),
                    new Point(0, getHeight()),
                    Color.ORANGE.darker()));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();

            super.paintComponent(g);
         }
      };
      button.setContentAreaFilled(false);
      button.setFont(new Font("Arial", Font.PLAIN, 30));
      button.addActionListener(e -> mw.changeCard(MainWindow.CREATION_PAGE));
      button.setPreferredSize(new Dimension(200, 50));
      buttonPanel.add(button);

      imageLabel.add(buttonPanel, BorderLayout.SOUTH);

      add(imageLabel);
   }

   @Override
   public void setupPage() {
      mw.pack();
      mw.setSize(mw.getWidth() - 2, mw.getHeight() - 2); // Permet de supprimer la petite ligne blanche à droite et en bas de la fenêtre...
      mw.setLocationRelativeTo(null);
   }
}
