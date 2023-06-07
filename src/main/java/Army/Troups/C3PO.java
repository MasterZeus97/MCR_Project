package Army.Troups;

public class C3PO extends Troup {

   private static int percentReduce = 0,
                      minHp = 2000,
                      maxHp = 2000,
                      minAtt = 1,
                      maxAtt = 1,
                      minDef = 1,
                      maxDef = 1,
                      minSpd = 1,
                      maxSpd = 1;

   int downGradeStatChances = 0;

   /**
    * Constructeur d'un C3PO
    */
   public C3PO() {
      super("C3PO", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd, percentReduce);
   }

   /**
    * Constructeur de copie d'un C3PO
    * @param s C3PO à copier
    */
   public C3PO(C3PO s) {
      super(s);
   }

   /**
    * Methode pour cloner le C3PO. Clone également ses Stats
    * @return Un nouveau C3PO, clone du premier
    */
   @Override
   public C3PO copy() {
      C3PO newTroup = new C3PO(this);
      //newTroup.downGradeStat(downGradeStatChances);
      return newTroup;
   }

   /**
    * Override de la méthode pour downgrade les stats d'une troupe. C3PO ne peut pas avoir ses stats downgraded
    * @param chanceToDowngrade Chance que les stats soit dégradées
    */
   @Override
   public void downGradeStat(int chanceToDowngrade) {
      // Do nothing (stats
   }
}
