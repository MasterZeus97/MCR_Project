package Army.Troups;

public class DeathTrooper extends Troup {

   private static int percentReduce = 10,
                      minHp = 500,
                      maxHp = 600,
                      minAtt = 15,
                      maxAtt = 20,
                      minDef = 200,
                      maxDef = 300,
                      minSpd = 5,
                      maxSpd = 10;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un DeathTrooper
    */
   public DeathTrooper() {
      super("Death Trooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
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
