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
                      maxSpd = 30,
                      minPrec = 10,
                      maxPrec = 50;

   int downGradeStatsChances = 70;

   /**
    * Constructeur d'un stormtrooper
    */
   public Stormtrooper() {
      super("Stormtrooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minPrec, maxPrec);
   }

   /**
    * Constructeur de copie d'un stormtrooper
    * @param s Stormtrooper à copier
    */
   public Stormtrooper(Stormtrooper s) {
      super(s);
   }

   /**
    * Methode pour cloner le Stormtrooper. Clone également ses attributs
    * @return Un nouveau Stormtrooper, clone du premier
    */
   @Override
   public Stormtrooper copy() {
      Stormtrooper newTroup = new Stormtrooper(this);
      newTroup.downGradeStats(downGradeStatsChances);
      return newTroup;
   }
}
