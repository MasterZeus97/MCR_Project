package Army.Troups;

/**
 * Troupe spécialisée : Scoot Trooper
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
public class ScootTrooper extends Troup {

   private static final int percentReduce = 20,
                            minHp = 50,
                            maxHp = 150,
                            minAtt = 25,
                            maxAtt = 30,
                            minDef = 10,
                            maxDef = 20,
                            minSpd = 80,
                            maxSpd = 100,
                            minReward = 100,
                            maxReward = 150;

   int downGradeStatChances = 50;

   /**
    * Constructeur d'un ScootTrooper
    */
   public ScootTrooper() {
      super("Scoot Trooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minReward, maxReward, percentReduce);
   }

   /**
    * Constructeur de copie d'un ScootTrooper
    * @param s ScootTrooper à copier
    */
   public ScootTrooper(ScootTrooper s) {
      super(s);
      this.downGradeStatChances = s.downGradeStatChances;
   }

   /**
    * Methode pour cloner le ScootTrooper. Clone également ses Stats
    * @return Un nouveau ScootTrooper, clone du premier
    */
   @Override
   public ScootTrooper copy() {
      ScootTrooper newTroup = new ScootTrooper(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
