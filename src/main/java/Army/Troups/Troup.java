package Army.Troups;

import Army.Statistique;
import Army.Prototypeable;

import java.util.*;

public abstract class Troup implements Prototypeable {
   private static final ArrayList<String> STATS_NAME_LIST = new ArrayList<>(Arrays.asList(
                                                                           "HP",
                                                                           "Attack",
                                                                           "Defense",
                                                                           "Speed"));

   final String name;

   final int hpMax;

   //Statistique du soldat
   private final Map<String, Statistique> statsMap = new HashMap<>();


   /**
    * Crée une nouvelle troupe avec des stats aléatoires et un id unique.
    *
    * @param name Le nom de la troupe
    */
   protected Troup(String name, int minHp, int maxHp,
                                int minAtt, int maxAtt,
                                int minDef, int maxDef,
                                int minSpd, int maxSpd) {
      this.name = name;

      statsMap.put(STATS_NAME_LIST.get(0), new Statistique(STATS_NAME_LIST.get(0), minHp, maxHp));
      statsMap.put(STATS_NAME_LIST.get(1), new Statistique(STATS_NAME_LIST.get(1), minAtt, maxAtt));
      statsMap.put(STATS_NAME_LIST.get(2), new Statistique(STATS_NAME_LIST.get(2), minDef, maxDef));
      statsMap.put(STATS_NAME_LIST.get(3), new Statistique(STATS_NAME_LIST.get(3), minSpd, maxSpd));

      hpMax = statsMap.get("HP").getValue();
   }

   /**
    * Constructeur de copie d'une troupe
    * @param troup La troupe à copier
    */
   protected Troup(Troup troup){
      this.name = troup.name;
      this.hpMax = troup.hpMax;
      for(String a : troup.statsMap.keySet()){
         statsMap.put(a, troup.statsMap.get(a).copy());
      }
   }

   /**
    * Méthode pour ajouter une stat à la troupe
    * @param statistique Statistique à ajouter à la troupe
    */
   protected void addStat(Statistique statistique){
      statsMap.put(statistique.getName(), statistique);
   }

   /**
    * Méthode pour obtenir la map contenant les Stats
    * @return map des Stats
    */
   protected Map<String, Statistique> getStatsMap() {
      return statsMap;
   }

   /**
    * Récupère une liste contenant les Stats
    * @return
    */
   public List<Statistique> getStatsList() {
      return new ArrayList<> (statsMap.values());
   }

   /**
    * Méthode pour obtenir les hp max de la troupe
    * @return nombre de hp max que peut avoir cette troupe
    */
   public int getHpMax() {
      return hpMax;
   }

   /**
    * Méthode pour soigner la troupe
    */
   public void heal(){
      statsMap.get("HP").setValue(hpMax);
   }

   /**
    * Méthode donnant les dégats de base d'une attaque par cette troupe
    * @return Valeur des dégats de base de l'attaque de cette troupe
    */
   public int attack(){
      if(statsMap.get("HP").getValue() <= 0)
         return 0;
      return statsMap.get("Attack").getValue();
   }

   /**
    * Méthode utilisée lorsque la troupe prend des dégats
    * @param damageDealed Dégats subits par la troupe
    */
   public int getAttacked(int damageDealed){
      int defense = statsMap.get("Defense").getValue();
      int hpLeft = statsMap.get("HP").getValue();
      int damageTaken = damageDealed > defense ? damageDealed - defense : 0;
      statsMap.get("HP").setValue(hpLeft > damageTaken ? (hpLeft - damageTaken) : 0);
      return damageTaken;
   }

   /**
    * Méthode pour obtenir la vitesse de la troupe
    * @return Vitesse de la troupe
    */
   public int getSpeed(){
      return statsMap.get("Speed").getValue();
   }

   /**
    * Méthode pour obtenir le nom de la troupe
    * @return Nom de la troupe
    */
   public String getName() {
      return name;
   }

   /**
    * Méthode pour downgrade les Statss d'une troupe
    * @param chanceToDowngrade
    */
   public void downGradeStat(int chanceToDowngrade){
      for (Statistique statistique : statsMap.values()){
         statistique.downgradeValue(chanceToDowngrade);
      }
   }

   @Override
   public abstract Troup copy();

   @Override
   public String toString() {
      return name;
   }
}
