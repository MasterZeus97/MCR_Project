package Army.Troups;

import Army.Attributs;
import Army.Prototypeable;

import java.util.*;

public abstract class Troup implements Prototypeable {
   private static final ArrayList<String> ATTRIBUTS_NAME_LIST = new ArrayList<>(Arrays.asList(
                                                                           "HP",
                                                                           "Attack",
                                                                           "Defense",
                                                                           "Speed"));

   String name;

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
                                int minSpd, int maxSpd) {
      this.name = name;
      for(String s : ATTRIBUTS_NAME_LIST){
         attributsMap.put(s, new Attributs(s));
      }
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMaxValue(minHp);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMinValue(maxHp);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMaxValue(minAtt);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMinValue(maxAtt);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMaxValue(minDef);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMinValue(maxDef);

      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMaxValue(minSpd);
      attributsMap.get(ATTRIBUTS_NAME_LIST.get(0)).setMinValue(maxSpd);

   }

   /**
    * Constructeur de copie d'une troupe
    * @param troup La troupe à copier
    */
   protected Troup(Troup troup){
      this.name = troup.name;
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
    * Récupère une liste contenant les attributs
    * @return
    */
   public List<Attributs> getAttributsMap() {
      return new ArrayList<> (attributsMap.values());
   }

   @Override
   public abstract Troup copy();

   @Override
   public String toString() {
      return name;
   }
}
