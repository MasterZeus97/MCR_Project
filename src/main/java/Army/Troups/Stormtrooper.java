package Army.Troups;

public class Stormtrooper extends Troup {

   static private int count = 0;
   public Stormtrooper() {
      super("Stormtrooper");
      setId(++count);
   }

   public Stormtrooper(Stormtrooper s) {
      super(s.getName(), s.getSpeed(), s.getPrecision(), s.getArmor(), s.getLoyalty());
      setId(++count);
   }

   @Override
   public Stormtrooper copy() {
      Stormtrooper newTroup = new Stormtrooper(this);
      newTroup.alterStats(-5, 0);
      return newTroup;
   }
}
