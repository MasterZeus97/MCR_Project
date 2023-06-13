package GUI;

import Army.Troups.*;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire permettant d'obtenir une troupe aléatoirement.
 */
abstract public class TroupGenerator {

   private static final List<Pair<Troup, Double>> weightedTroup = new ArrayList<>();
   static {

      weightedTroup.add(new Pair<>(new Stormtrooper(), 1.0));
      weightedTroup.add(new Pair<>(new ScootTrooper(), 0.3));
      weightedTroup.add(new Pair<>(new DarkTrooper(),  0.1));
      weightedTroup.add(new Pair<>(new DeathTrooper(), 0.1));
      weightedTroup.add(new Pair<>(new Droid(),        0.3));
      weightedTroup.add(new Pair<>(new KXDroid(),      0.2));
      weightedTroup.add(new Pair<>(new C3PO(),         0.01));
      weightedTroup.add(new Pair<>(new DarkVador(),    0.01));
   }
   static EnumeratedDistribution<Troup> possibleTroups = new EnumeratedDistribution<>(weightedTroup);

   /**
    * Retourne une troupe choisie aléatoirement.
    *
    * @return La troupe choisie.
    */
   public static Troup getRandomTroup() {
      Troup troup = possibleTroups.sample().copy();
      troup.randomizeStats();
      return troup;
   }

}
