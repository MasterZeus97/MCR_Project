package Army.Troups;

import Army.Attributs;

import java.util.HashMap;
import java.util.Map;

public class Stormtrooper extends Troup {

   private static int count = 0;

   private static int minHp = 10,
                      maxHp = 100,
                      minAtt = 50,
                      maxAtt = 100,
                      minDef = 30,
                      maxDef = 60,
                      minSpd = 20,
                      maxSpd = 30;


   public Stormtrooper() {
      super("Stormtrooper");
      for(String s : ATTRIBUTS_NAME_LIST){
         Attributs attributs = getAttributsMap().get(s);
         if(attributs != null){
            switch (s){
               case "HP":
                  attributs.setMaxValue(minHp);
                  attributs.setMinValue(maxHp);
                  break;
               case "Attack":
                  attributs.setMaxValue(minAtt);
                  attributs.setMinValue(maxAtt);
                  break;
               case "Defense":
                  attributs.setMaxValue(minDef);
                  attributs.setMinValue(maxDef);
                  break;
               case "Speed":
                  attributs.setMaxValue(minSpd);
                  attributs.setMinValue(maxSpd);
                  break;
            }
         }
      }
      //setId(++count);
   }

   public Stormtrooper(Stormtrooper s) {
//      super(s.getName(), s.getSpeed(), s.getPrecision(), s.getArmor(), s.getLoyalty());
      super(s);
      //setId(++count);
   }

   @Override
   public Stormtrooper copy() {
      Stormtrooper newTroup = new Stormtrooper(this);
      //newTroup.alterStats(-5, 0);
      return newTroup;
   }
}
