package Army.Troups;

public class KXSeries extends Troup {

   private static int percentReduce = 10,
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
   public KXSeries() {
      super("KXSeries", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
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
