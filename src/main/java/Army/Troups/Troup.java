package Army.Troups;

import Army.Stat;
import Army.Prototypeable;

import java.util.*;

public abstract class Troup implements Prototypeable {
   private static final ArrayList<String> STATS_NAME_LIST = new ArrayList<>(Arrays.asList(
                                                                           "HP",
                                                                           "Attack",
                                                                           "Defense",
                                                                           "Speed"));

   private static final int dropChance = 90;

   private final String name;
   private final int hpMax;
   private final int minReward, maxReward;

   //Stat du soldat
   private final Map<String, Stat> statsMap = new HashMap<>();
   private List<Stat> sortedStats = new ArrayList<>();


   /**
    * Crée une nouvelle troupe avec des stats aléatoires et un id unique.
    *
    * @param name Le nom de la troupe
    */
   protected Troup(String name, int minHp, int maxHp,
                                int minAtt, int maxAtt,
                                int minDef, int maxDef,
                                int minSpd, int maxSpd,
                                int minReward, int maxReward,
                                int percentReduce) {
      this.name = name;
      this.minReward = minReward;
      this.maxReward = maxReward;
      statsMap.put(STATS_NAME_LIST.get(0), new Stat(STATS_NAME_LIST.get(0), minHp, maxHp, percentReduce));
      statsMap.put(STATS_NAME_LIST.get(1), new Stat(STATS_NAME_LIST.get(1), minAtt, maxAtt, percentReduce));
      statsMap.put(STATS_NAME_LIST.get(2), new Stat(STATS_NAME_LIST.get(2), minDef, maxDef, percentReduce));
      statsMap.put(STATS_NAME_LIST.get(3), new Stat(STATS_NAME_LIST.get(3), minSpd, maxSpd, percentReduce));

      hpMax = statsMap.get("HP").getValue();
   }

   /**
    * Constructeur de copie d'une troupe.
    * @param troup La troupe à copier
    */
   protected Troup(Troup troup){
      this.name = troup.name;
      this.hpMax = troup.hpMax;
      this.minReward = troup.minReward;
      this.maxReward = troup.maxReward;
      for(String a : troup.statsMap.keySet()){
         statsMap.put(a, troup.statsMap.get(a).copy());
      }
   }

   /**
    * Méthode pour ajouter une stat à la troupe
    * @param stat Stat à ajouter à la troupe
    */
   protected void addStat(Stat stat){
      statsMap.put(stat.getName(), stat);
   }

   /**
    * Méthode pour obtenir la map contenant les Stats
    * @return map des Stats
    */
   protected Map<String, Stat> getStatsMap() {
      return statsMap;
   }

   /**
    * Récupère une liste contenant les Stats
    * @return Liste des stats de la troupe
    */
   public List<Stat> getStatsList() {
      if(sortedStats.isEmpty()){
         sortedStats = new ArrayList<> (statsMap.values());
         sortedStats.sort((o1, o2) -> {
            if (!isStatBelow(o1, o2)) {
               return 1;
            } else if (isStatBelow(o1, o2)) {
               return -1;
            } else {
               return 0;
            }
         });
      }
      return sortedStats;
   }

   /**
    * Méthode pour obtenir les hp max de la troupe
    * @return nombre de hp max que peut avoir cette troupe
    */
   public int getHpMax() {
      return hpMax;
   }

   /**
    * Méthode pour obtenir les hp actuels de la troupe
    * @return nombre de hp actuels de la troupe
    */
   public int getHp() {
      return statsMap.get("HP").getValue();
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
      int damageTaken = damageDealed > defense ? damageDealed - defense : 1;
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
    * @param chanceToDowngrade Entier représentant le poucentage de chance que la stat se dégrade. La valeur est réduite
    *                          à une valeur entre 0 et 100.
    */
   protected void downGradeStat(int chanceToDowngrade){
      for (Stat stat : statsMap.values()){
         stat.downgradeValue(chanceToDowngrade);
      }
   }

   /**
    * Permet de savoir si la troupe est en vie
    * @return True si la troupe a encore 1HP ou plus - False si elle a moins de 1HP
    */
   public boolean isAlive(){
      return statsMap.get("HP").getValue() <= 0;
   }

   /**
    * Méthode pour diminuer les stats de cette troupe
    */
   public void drop(){
      downGradeStat(dropChance);
   }

   public void maximizeStats(){
      for(Stat s : statsMap.values())
         s.maximizeVal();
   }

   /**
    * Permet de savoir l'argent que cette troupe donne lorsque vaincue
    * @return montant gagné par l'adversaire
    */
   public int defeatedMoney(){
      Random random = new Random();

      if(maxReward != minReward) {
         return random.nextInt(maxReward - minReward) + minReward;
      }else{
         return minReward;
      }
   }

   @Override
   public abstract Troup copy();

   @Override
   public String toString() {
      return name;
   }

   /**
    * Méthode permettant de trier la liste de statistiques.
    * @param stat1 Statistique comparée
    * @param stat2 statistique à laquelle stat1 est comparée
    * @return True si stat1 doit être positionnée en dessous de stat2 -
    *         False si stat1 doit être au dessus de stat2 ou si stat1 == stat2
    */
   private boolean isStatBelow(Stat stat1, Stat stat2){
      if(STATS_NAME_LIST.contains(stat1.getName())){
         if(stat1.getName().equals(STATS_NAME_LIST.get(0))){
            return true;
         }else if(stat1.getName().equals(STATS_NAME_LIST.get(1)) && !(stat2.getName().equals(STATS_NAME_LIST.get(0)) ||
                                                                      stat2.getName().equals(STATS_NAME_LIST.get(1)))){
            return true;
         }else if(stat1.getName().equals(STATS_NAME_LIST.get(2)) && !(stat2.getName().equals(STATS_NAME_LIST.get(0)) ||
                                                                      stat2.getName().equals(STATS_NAME_LIST.get(1)) ||
                                                                      stat2.getName().equals(STATS_NAME_LIST.get(2)))){
            return true;
         }else if(stat1.getName().equals(STATS_NAME_LIST.get(3)) && !(stat2.getName().equals(STATS_NAME_LIST.get(0)) ||
                                                                      stat2.getName().equals(STATS_NAME_LIST.get(1)) ||
                                                                      stat2.getName().equals(STATS_NAME_LIST.get(2)) ||
                                                                      stat2.getName().equals(STATS_NAME_LIST.get(3)))){
            return true;
         }else{
            return false;
         }
      }else{
         return false;
      }
   }
}
