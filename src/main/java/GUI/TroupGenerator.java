package GUI;

import Army.Troups.*;

import java.util.Random;

/**
 * Classe utilitaire permettant d'obtenir une troupe aléatoirement.
 */
abstract public class TroupGenerator {

   private static final int TROUP_NUMBER = 8;

   /**
    * Retourne une troupe choisie aléatoirement.
    *
    * @return La troupe choisie.
    */
   public static Troup getRandomTroup() {
      Random random = new Random();
      int RNG = random.nextInt(TROUP_NUMBER) + 1;

      return switch (RNG) {
         case 1 -> new Stormtrooper();
         case 2 -> new ScootTrooper();
         case 3 -> new DarkTrooper();
         case 4 -> new DeathTrooper();
         case 5 -> new Droid();
         case 6 -> new KXSeries();
         case 7 -> new C3PO();
         case 8 -> new DarkVador();
         default -> null;
      };
   }
}
