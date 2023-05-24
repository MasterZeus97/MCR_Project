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

      getAttributsMap().get("HP").setMaxValue(minHp);
      getAttributsMap().get("HP").setMinValue(maxHp);

      getAttributsMap().get("Attack").setMaxValue(minAtt);
      getAttributsMap().get("Attack").setMinValue(maxAtt);

      getAttributsMap().get("Defense").setMaxValue(minDef);
      getAttributsMap().get("Defense").setMinValue(maxDef);

      getAttributsMap().get("Speed").setMaxValue(minSpd);
      getAttributsMap().get("Speed").setMinValue(maxSpd);
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
