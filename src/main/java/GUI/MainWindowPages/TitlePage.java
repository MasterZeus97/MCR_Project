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
      JButton button = new JButton("Jouer");
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

/*
      setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.insets = new Insets(10, 10, 10, 10);

      JLabel imageLabel = new JLabel();
      imageLabel.setIcon(new ImageIcon("img/titleScreen.png"));
      add(imageLabel, gbc);

      gbc.gridy = 1;
      JButton button = new JButton("Jouer");
      button.addActionListener(e -> mw.changeCard(MainWindow.CREATION_PAGE));
      add(button, gbc);
 */