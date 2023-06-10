package GUI;

import Army.Troups.*;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe utilitaire permettant d'obtenir une troupe aléatoirement.
 */
abstract public class TroupGenerator {


   private static final List<Pair<Troup, Double>> weightedTroup = new ArrayList<>();
   static {

      weightedTroup.add(new Pair<>(new Stormtrooper(), 0.1));
      weightedTroup.add(new Pair<>(new ScootTrooper(), 0.1));
      weightedTroup.add(new Pair<>(new DarkTrooper(),  0.1));
      weightedTroup.add(new Pair<>(new DeathTrooper(), 0.1));
      weightedTroup.add(new Pair<>(new Droid(),        0.1));
      weightedTroup.add(new Pair<>(new KXSeries(),     0.1));
      weightedTroup.add(new Pair<>(new C3PO(),         0.1));
      weightedTroup.add(new Pair<>(new DarkVador(),    0.1));
   }
   static EnumeratedDistribution<Troup> possibleTroups = new EnumeratedDistribution<>(weightedTroup);

   /*static List<Pair<Integer, Double>> weights = new ArrayList<>();
   static {
      weights.add(new Pair<>(1, 0.1));
      weights.add(new Pair<>(2, 0.1));
      weights.add(new Pair<>(3, 0.1));
      weights.add(new Pair<>(4, 0.1));
      weights.add(new Pair<>(5, 0.1));
      weights.add(new Pair<>(6, 0.1));
      weights.add(new Pair<>(7, 0.1));
      weights.add(new Pair<>(8, 0.1));
   }
   static EnumeratedDistribution<Integer> possibleTroups = new EnumeratedDistribution<>(weights);*/

   /**
    * Retourne une troupe choisie aléatoirement.
    *
    * @return La troupe choisie.
    */
   public static Troup getRandomTroup() {
      Troup troup = possibleTroups.sample();
      troup.randomizeStats();
      return troup;
      /*
         return switch (possibleTroups.sample()) {
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
      */
   }



}
