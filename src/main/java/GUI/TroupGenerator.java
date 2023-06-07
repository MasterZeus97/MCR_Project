package GUI;

import Army.Troups.Droid;
import Army.Troups.Stormtrooper;
import Army.Troups.Troup;

import java.util.Random;

/**
 * Classe utilitaire permettant d'obtenir une troupe aléatoirement.
 */
abstract public class TroupGenerator {

   /**
    * Retourne une troupe aléatoirement.
    *
    * @return La troupe choisie.
    */
   public static Troup getRandomTroup() {
      Random random = new Random();
      int RNG = random.nextInt(100 - 1) + 1;

      if (RNG <= 50) {
         return new Stormtrooper();
      } else {
         return new Droid();
      }
   }
}
