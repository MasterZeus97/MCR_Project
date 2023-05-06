package Troupes;

import java.util.Random;

public abstract class Troup implements Cloneable {

   private final String name;
   static private int count = 0;
   private int id;
   private int speed, precision, armor, loyalty;

   static final int MIN_STAT = 0;
   static final int MAX_STAT = 99;

   /**
    * Crée une nouvelle troupe avec des stats aléatoires et un id unique.
    *
    * @param name Le nom de la troupe
    */
   protected Troup(String name) {
      this.name = name;
      id = ++count;

      speed = getRandomStat(MIN_STAT, MAX_STAT);
      precision = getRandomStat(MIN_STAT, MAX_STAT);
      armor = getRandomStat(MIN_STAT, MAX_STAT);
      loyalty = getRandomStat(MIN_STAT, MAX_STAT);
   }

   /**
    * Modifie aléatoirement toutes les stats de la troupe autour de l'intervalle donné.
    *
    * @param min La borne min de l'intervalle
    * @param max La borne max de l'intervalle
    */
   private void alterStats(int min, int max) {
      speed = getRandomStat(speed + min, speed + max);
      precision = getRandomStat(precision + min, precision + max);
      armor = getRandomStat(armor + min, armor + max);
      loyalty = getRandomStat(loyalty + min, loyalty + max);
   }

   /**
    * Retourne la valeur d'une stat aléatoire se trouvant dans l'intervalle donné ET entre MIN_STAT et MAX_STAT.
    *
    * @param min La borne min de l'intervalle
    * @param max La borne max de l'intervalle
    * @return La nouvelle stat générée aléatoirement
    */
   private int getRandomStat(int min, int max) {
      Random random = new Random();
      int val = random.nextInt(max - min) + min;
      return Math.max(MIN_STAT, Math.min(val, MAX_STAT));
   }

   /**
    * Clone la troupe et la retourne.
    * La troupe clonée peut aléatoirement faire diminuer ses stats.
    * La troupe d'origine peut aléatoirement faire augmenter ses stats.
    *
    * @return La nouvelle troupe clonée
    * @throws CloneNotSupportedException Si la troupe ne peut pas être clonée
    */
   public Troup clone() throws CloneNotSupportedException {
      Troup newTroup = (Troup) super.clone();
      newTroup.id = ++count;
      newTroup.alterStats(-5, 0);
      alterStats(0, 5);
      return newTroup;
   }

   @Override
   public String toString() {

      return name + " #" + id + "\n" +
              speed + "%, " + precision + "%, " + armor + "%, " + loyalty + "%\n";

      /*
      return name + " #" + id + "\n" +
              "Speed: " + speed + "%\n" +
              "Precision: " + precision + "%\n" +
              "Armor: " + armor + "%\n" +
              "Loyalty: " + loyalty + "%\n";
       */
   }
}
