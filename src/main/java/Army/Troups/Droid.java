package Army.Troups;

/**
 * Troupe spécialisée : Droid
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
public class Droid extends Troup {

   private static final int percentReduce = 10,
                            minHp = 100,
                            maxHp = 200,
                            minAtt = 100,
                            maxAtt = 200,
                            minDef = 60,
                            maxDef = 90,
                            minSpd = 25,
                            maxSpd = 30,
                           minReward = 200,
                           maxReward = 300;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un Droid
    */
   public Droid() {
      super("Droid", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minReward, maxReward, percentReduce);
   }

   /**
    * Constructeur de copie d'un droid
    * @param s Droid à copier
    */
   public Droid(Droid s) {
      super(s);
      this.downGradeStatChances = s.downGradeStatChances;
   }

   /**
    * Methode pour cloner le Droid. Clone également ses Stats
    * @return Un nouveau Droid, clone du premier
    */
   @Override
   public Droid copy() {
      Droid newTroup = new Droid(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
