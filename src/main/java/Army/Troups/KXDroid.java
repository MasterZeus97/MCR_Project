package Army.Troups;

public class KXDroid extends Troup {


   private static final int percentReduce = 10,
                            minHp = 200,
                            maxHp = 300,
                            minAtt = 150,
                            maxAtt = 250,
                            minDef = 90,
                            maxDef = 120,
                            minSpd = 15,
                            maxSpd = 20,
                            minReward = 300,
                            maxReward = 400;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un KXSeries
    */
   public KXDroid() {
      super("KX droid", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minReward, maxReward, percentReduce);
   }

   /**
    * Constructeur de copie d'un KXSeries
    * @param s KXSeries à copier
    */
   public KXDroid(KXDroid s) {
      super(s);
      this.downGradeStatChances = s.downGradeStatChances;
   }

   /**
    * Methode pour cloner le KXSeries. Clone également ses Stats
    * @return Un nouveau KXSeries, clone du premier
    */
   @Override
   public KXDroid copy() {
      KXDroid newTroup = new KXDroid(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
