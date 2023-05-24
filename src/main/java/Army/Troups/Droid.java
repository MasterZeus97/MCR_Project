package Army.Troups;

public class Droid extends Troup {

   private static int minHp = 10,
                      maxHp = 100,
                      minAtt = 50,
                      maxAtt = 100,
                      minDef = 30,
                      maxDef = 60,
                      minSpd = 20,
                      maxSpd = 30;

   public Droid() {
      super("Droid", minHp, maxHp, minAtt, maxAtt, minDef, maxDef, minSpd, maxSpd);
   }

   public Droid(Droid s) {
      super(s);
   }

   @Override
   public Droid copy() {
      return new Droid(this);
   }
}
