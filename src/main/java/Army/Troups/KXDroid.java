package Army.Troups;

public class KXDroid extends Troup {

   private static final int percentReduce = 10,
                            minHp = 100,
                            maxHp = 200,
                            minAtt = 100,
                            maxAtt = 200,
                            minDef = 60,
                            maxDef = 90,
                            minSpd = 10,
                            maxSpd = 15;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un KXSeries
    */
   public KXDroid() {
      super("KX droid", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
   }

   /**
    * Constructeur de copie d'un KXSeries
    * @param s KXSeries à copier
    */
   public KXDroid(KXDroid s) {
      super(s);
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
