package Army.Troups;

public class Droid extends Troup {

   private static int minHp = 100,
                      maxHp = 200,
                      minAtt = 100,
                      maxAtt = 200,
                      minDef = 60,
                      maxDef = 90,
                      minSpd = 10,
                      maxSpd = 15;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un Droid
    */
   public Droid() {
      super("Droid", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd);
   }

   /**
    * Constructeur de copie d'un droid
    * @param s Droid à copier
    */
   public Droid(Droid s) {
      super(s);
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
