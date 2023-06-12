package Army.Troups;

public class DeathTrooper extends Troup {

   private static final int percentReduce = 10,
                            minHp = 500,
                            maxHp = 600,
                            minAtt = 100,
                            maxAtt = 110,
                            minDef = 200,
                            maxDef = 300,
                            minSpd = 5,
                            maxSpd = 10,
                            minReward = 300,
                            maxReward = 400;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un DeathTrooper
    */
   public DeathTrooper() {
      super("Death Trooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, minReward, maxReward, percentReduce);
   }

   /**
    * Constructeur de copie d'un DeathTrooper
    * @param s DeathTrooper à copier
    */
   public DeathTrooper(DeathTrooper s) {
      super(s);
   }

   /**
    * Methode pour cloner le DeathTrooper. Clone également ses Stats
    * @return Un nouveau DeathTrooper, clone du premier
    */
   @Override
   public DeathTrooper copy() {
      DeathTrooper newTroup = new DeathTrooper(this);
      newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }
}
