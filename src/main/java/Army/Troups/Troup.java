package Army.Troups;

import Army.Prototypeable;
import java.util.Random;

public abstract class Troup implements Prototypeable {

   private final String name;
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

      speed = getRandomStat(MIN_STAT, MAX_STAT);
      precision = getRandomStat(MIN_STAT, MAX_STAT);
      armor = getRandomStat(MIN_STAT, MAX_STAT);
      loyalty = getRandomStat(MIN_STAT, MAX_STAT);
   }

   protected Troup(String name, int speed, int precision, int armor, int loyalty) {
      this.name = name;
      this.speed = speed;
      this.precision = precision;
      this.armor = armor;
      this.loyalty = loyalty;
   }

   public String getName() { return name; }

   public int getSpeed() {
      return speed;
   }

   public int getPrecision() {
      return precision;
   }

   public int getArmor() {
      return armor;
   }

   public int getLoyalty() {
      return loyalty;
   }

   public void setSpeed(int speed) {
      this.speed = speed;
   }

   public void setPrecision(int precision) {
      this.precision = precision;
   }

   public void setArmor(int armor) {
      this.armor = armor;
   }

   public void setLoyalty(int loyalty) {
      this.loyalty = loyalty;
   }

   /**
    * Setter pour set l'id de la troupe
    * @param id Le nouvel id de la troupe
    */
   protected void setId(int id){
      this.id = id;
   }

   /**
    * Modifie aléatoirement toutes les stats de la troupe autour de l'intervalle donné.
    *
    * @param min La borne min de l'intervalle
    * @param max La borne max de l'intervalle
    */
   protected void alterStats(int min, int max) {
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
