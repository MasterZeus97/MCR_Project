package Army.Troups;

public class DarkTrooper extends Troup {

   private static final int percentReduce = 10,
                            minHp = 50,
                            maxHp = 100,
                            minAtt = 300,
                            maxAtt = 400,
                            minDef = 20,
                            maxDef = 30,
                            minSpd = 30,
                            maxSpd = 50,
                            minReward = 300,
                            maxReward = 400;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un DarkTrooper
    */
   public DarkTrooper() {
      super("Dark Trooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minReward, maxReward, percentReduce);
   }

   /**
    * Constructeur de copie d'un DarkTrooper
    * @param s DarkTrooper à copier
    */
   public DarkTrooper(DarkTrooper s) {
      super(s);
   }

   /**
    * Methode pour cloner le DarkTrooper. Clone également ses Stats
    * @return Un nouveau DarkTrooper, clone du premier
    */
   @Override
   public DarkTrooper copy() {
      DarkTrooper newTroup = new DarkTrooper(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
