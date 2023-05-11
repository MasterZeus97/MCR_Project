package Army.Troups;

import Army.Prototypeable;

public class Stormtrooper extends Troup {

   static private int count = 0;
   public Stormtrooper() {
      super("Stormtrooper");
      setId(++count);
   }

   @Override
   public Prototypeable copy() {
      Stormtrooper newTroup = new Stormtrooper();
      newTroup.statsSet(this.getSpeed(), this.getPrecision(), this.getArmor(), this.getLoyalty());
      newTroup.alterStats(-5, 0);
      return newTroup;
   }
}
