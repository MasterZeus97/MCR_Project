package Army.Troups;

import Army.Attributs;
import Army.Prototypeable;

import java.util.*;

public abstract class Troup implements Prototypeable {
   private static final ArrayList<String> ATTRIBUTS_NAME_LIST = new ArrayList<>(Arrays.asList(
                                                                           "HP",
                                                                           "Attack",
                                                                           "Defense",
                                                                           "Speed",
                                                                           "Precision"));

   final String name;

   final int hpMax;

   //Attributs du soldat
   Map<String, Attributs> attributsMap = new HashMap<>();


   /**
    * Crée une nouvelle troupe avec des stats aléatoires et un id unique.
    *
    * @param name Le nom de la troupe
    */
   protected Troup(String name, int minHp, int maxHp,
                                int minAtt, int maxAtt,
                                int minDef, int maxDef,
                                int minSpd, int maxSpd,
                                int minPrec, int maxPrec) {
      this.name = name;
      for(String s : ATTRIBUTS_NAME_LIST){
         attributsMap.put(s, new Attributs(s));
      }

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMaxValue(minHp);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMinValue(maxHp);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(1)).setMaxValue(minAtt);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(1)).setMinValue(maxAtt);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(2)).setMaxValue(minDef);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(2)).setMinValue(maxDef);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(3)).setMaxValue(minSpd);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(3)).setMinValue(maxSpd);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(4)).setMaxValue(minPrec);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(4)).setMinValue(maxPrec);

      hpMax = attributsMap.get("HP").getValue();
   }

   /**
    * Constructeur de copie d'une troupe
    * @param troup La troupe à copier
    */
   protected Troup(Troup troup){
      this.name = troup.name;
      this.hpMax = troup.hpMax;
      for(String a : troup.attributsMap.keySet()){
         attributsMap.put(a, troup.attributsMap.get(a).copy());
      }
   }

   /**
    * Méthode pour ajouter un attribut à la troupe
    * @param attributs Attributs à ajouter à la troupe
    */
   protected void addAttribut(Attributs attributs){
      attributsMap.put(attributs.getName(), attributs);
   }

   /**
    * Méthode pour obtenir la map contenant les attributs
    * @return map des attributs
    */
   protected Map<String, Attributs> getAttributsMap() {
      return attributsMap;
   }

   /**
    * Récupère une liste contenant les attributs
    * @return
    */
   public List<Attributs> getAttributsList() {
      return new ArrayList<> (attributsMap.values());
   }

   /**
    * Méthode pour obtenir les hp max de la troupe
    * @return nombre de hp max que peut avoir cette troupe
    */
   public int getHpMax() {
      return hpMax;
   }

   public void heal(){
      attributsMap.get("HP").setValue(hpMax);
   }

   /**
    * Méthode pour obtenir le nom de la troupe
    * @return Nom de la troupe
    */
   public String getName() {
      return name;
   }

   /**
    * Méthode pour downgrade les attributs d'une troupe
    * @param chanceToDowngrade
    */
   public void downGradeStats(int chanceToDowngrade){
      for (Attributs attributs : attributsMap.values()){
         attributs.downgradeValue(chanceToDowngrade);
      }
   }

   @Override
   public abstract Troup copy();

   @Override
   public String toString() {
      return name;
   }
}
