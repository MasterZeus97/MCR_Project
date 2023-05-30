package Army.Troups;

import Army.Attributs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Stormtrooper extends Troup {

   private static int minHp = 10,
                      maxHp = 100,
                      minAtt = 50,
                      maxAtt = 100,
                      minDef = 30,
                      maxDef = 60,
                      minSpd = 20,
                      maxSpd = 30;


   public Stormtrooper() {
      super("Stormtrooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd);
   }

   public Stormtrooper(Stormtrooper s) {
      super(s);
   }

   @Override
   public Stormtrooper copy() {
      Stormtrooper newTroup = new Stormtrooper(this);
      return newTroup;
   }
}
