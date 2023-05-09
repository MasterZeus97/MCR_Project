package Troupes;

public class Droid extends Troup {

   static private int count = 0;
   public Droid() {
      super("Droid");
      setId(++count);
   }

   @Override
   public Prototypeable copy() {
      Droid newTroup = new Droid();
      newTroup.statsSet(this.getSpeed(), this.getPrecision(), this.getArmor(), this.getLoyalty());
      return newTroup;
   }
}
