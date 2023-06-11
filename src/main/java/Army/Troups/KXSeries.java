package Army.Troups;

public class KXSeries extends Troup {


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
   public KXSeries() {
      super("KX-series security droid", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce, minReward, maxReward);
   }

   /**
    * Constructeur de copie d'un KXSeries
    * @param s KXSeries à copier
    */
   public KXSeries(KXSeries s) {
      super(s);
   }

   /**
    * Methode pour cloner le KXSeries. Clone également ses Stats
    * @return Un nouveau KXSeries, clone du premier
    */
   @Override
   public KXSeries copy() {
      KXSeries newTroup = new KXSeries(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
