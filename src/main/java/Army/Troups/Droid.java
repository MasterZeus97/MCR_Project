package Army.Troups;

public class Droid extends Troup {

   static private int count = 0;
   public Droid() {
      super("Droid");
      setId(++count);
   }

   public Droid(Droid s) {
      super(s.getName(), s.getSpeed(), s.getPrecision(), s.getArmor(), s.getLoyalty());
      setId(++count);
   }

   @Override
   public Droid copy() {
      return new Droid(this);
   }
}
