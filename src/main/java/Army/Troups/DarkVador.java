package Army.Troups;

public class DarkVador extends Troup {

   private static int percentReduce = 10,
                      minHp = 1000,
                      maxHp = 1000,
                      minAtt = 500,
                      maxAtt = 500,
                      minDef = 150,
                      maxDef = 150,
                      minSpd = 100,
                      maxSpd = 100;

   int downGradeStatChances = 70;

   /**
    * Constructeur d'un DarkVador
    */
   public DarkVador() {
      super("Dark Vador", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
   }

   /**
    * Constructeur de copie d'un DarkVador
    * @param s DarkVador à copier
    */
   public DarkVador(DarkVador s) {
      super(s);
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
