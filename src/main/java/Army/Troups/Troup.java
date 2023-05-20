package Army.Troups;

import Army.Attributs;
import Army.Prototypeable;

import java.util.*;

public abstract class Troup implements Prototypeable {
   static final ArrayList<String> ATTRIBUTS_NAME_LIST = new ArrayList<>(Arrays.asList(
                                                                           "HP",
                                                                           "Attack",
                                                                           "Defense",
                                                                           "Speed"));
   //Attributs du soldat
   HashMap<String, Attributs> attributsMap = new HashMap<>();

   static final int MIN_HP = 0;

   static final int MIN_STAT = 0;
   static final int MAX_STAT = 99;

   /**
    * Crée une nouvelle troupe avec des stats aléatoires et un id unique.
    *
    * @param name Le nom de la troupe
    */
   protected Troup(String name) {
      for(String s : ATTRIBUTS_NAME_LIST){
         attributsMap.put(s, new Attributs(s));
      }
   }

   /**
    * Constructeur de copie d'une troupe
    * @param troup La troupe à copier
    */
   protected Troup(Troup troup){
   }

   /**
    * Méthode pour ajouter un attribut à la troupe
    * @param attributs Attributs à ajouter à la troupe
    */
   protected void addAttribut(Attributs attributs){
      attributsMap.put(attributs.getName(), attributs);
   }

   /**
    * Récupère une map contenant les attributs
    * @return
    */
   public HashMap<String, Attributs> getAttributsMap() {
      return attributsMap;
   }

   /**
    * Setter pour set l'id de la troupe
    * @param id Le nouvel id de la troupe
    */
   /*protected void setId(int id){
      this.id = id;
   }*/

   /**
    * Modifie aléatoirement toutes les stats de la troupe autour de l'intervalle donné.
    *
    * @param min La borne min de l'intervalle
    * @param max La borne max de l'intervalle
    */
   /*protected void alterStats(int min, int max) {
      speed = getRandomStat(speed + min, speed + max);
      precision = getRandomStat(precision + min, precision + max);
      armor = getRandomStat(armor + min, armor + max);
      loyalty = getRandomStat(loyalty + min, loyalty + max);
   }*/

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
   public abstract Troup copy();

   @Override
   public String toString() {

      return "";
      /*return name + " #" + id + "\n" +
              speed + "%, " + precision + "%, " + armor + "%, " + loyalty + "%\n";
*/
      /*
      return name + " #" + id + "\n" +
              "Speed: " + speed + "%\n" +
              "Precision: " + precision + "%\n" +
              "Armor: " + armor + "%\n" +
              "Loyalty: " + loyalty + "%\n";
       */
   }
}
