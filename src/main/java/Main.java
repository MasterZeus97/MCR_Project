import Troupes.*;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Stormtrooper t1 = new Stormtrooper();
        System.out.println(t1);

        Stormtrooper t2 = (Stormtrooper) t1.copy();
        System.out.println(t2);
        System.out.println(t1);

        Stormtrooper t3 = (Stormtrooper) t1.copy();
        System.out.println(t3);

        Droid d1 = new Droid();
        System.out.println(d1);
        Droid d2 = (Droid) d1.copy();
        System.out.println(d1);
        System.out.println(d2);


        Droid tmp = new Droid();
        System.out.println(tmp);
        Troup tmp2 = (Troup) tmp.copy();
        System.out.println(tmp2);


        Squadron s = new Squadron();
        s.add(t1);
        s.add(t2);
        s.add(t3);
        s.add(d1);
        s.add(d2);

        System.out.println(s);
    }
}
