package Army.Troups;

/**
 * Troupe spécialisée : Dark Vador
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
public class DarkVador extends Troup {

   private static final int percentReduce = 10,
                            minHp = 1000,
                            maxHp = 1000,
                            minAtt = 500,
                            maxAtt = 500,
                            minDef = 150,
                            maxDef = 150,
                            minSpd = 100,
                            maxSpd = 100,
                            minReward = 1000,
                            maxReward = 1500;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un DarkVador
    */
   public DarkVador() {
      super("Dark Vador", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minReward, maxReward, percentReduce);
   }

   /**
    * Constructeur de copie d'un DarkVador
    * @param s DarkVador à copier
    */
   public DarkVador(DarkVador s) {
      super(s);
      this.downGradeStatChances = s.downGradeStatChances;
   }

   /**
    * Methode pour cloner le DarkVador. Clone également ses Stats
    * @return Un nouveau DarkVador, clone du premier
    */
   @Override
   public DarkVador copy() {
      DarkVador newTroup = new DarkVador(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
