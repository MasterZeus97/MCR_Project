package Army.Troups;

public class ScootTrooper extends Troup {

   private static int percentReduce = 20,
                      minHp = 50,
                      maxHp = 100,
                      minAtt = 25,
                      maxAtt = 30,
                      minDef = 10,
                      maxDef = 20,
                      minSpd = 80,
                      maxSpd = 100;

   int downGradeStatChances = 50;

   /**
    * Constructeur d'un ScootTrooper
    */
   public ScootTrooper() {
      super("ScootTrooper", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
   }

   /**
    * Constructeur de copie d'un ScootTrooper
    * @param s ScootTrooper à copier
    */
   public ScootTrooper(ScootTrooper s) {
      super(s);
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
