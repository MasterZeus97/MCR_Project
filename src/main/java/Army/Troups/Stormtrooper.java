package Army.Troups;

import Army.Stat;

import java.util.Random;

public class Stormtrooper extends Troup {

   private static final int percentReduce = 10,
                            minHp = 10,
                            maxHp = 100,
                            minAtt = 50,
                            maxAtt = 100,
                            minDef = 30,
                            maxDef = 60,
                            minSpd = 30,
                            maxSpd = 40,
                            minPrec = 10,
                            maxPrec = 50;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un stormtrooper
    */
   public Stormtrooper() {
      super("Stormtrooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
      getStatsMap().put("Precision", new Stat("Precision", minPrec, maxPrec, percentReduce));
   }

   /**
    * Constructeur de copie d'un stormtrooper
    * @param s Stormtrooper à copier
    */
   public Stormtrooper(Stormtrooper s) {
      super(s);
   }

   @Override
   public int attack() {
      Random random = new Random();
      int checkChange = random.nextInt(100 - 1) + 1;
      if(checkChange <= getStatsMap().get("Precision").getValue()){
         return super.attack();
      }else{
         return 0;
      }
   }


   /**
    * Methode pour cloner le Stormtrooper. Clone également ses Stats
    * @return Un nouveau Stormtrooper, clone du premier
    */
   @Override
   public Stormtrooper copy() {
      Stormtrooper newTroup = new Stormtrooper(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
