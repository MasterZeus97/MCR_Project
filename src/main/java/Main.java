import Troupes.Stormtrooper;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Stormtrooper t1 = new Stormtrooper();
        System.out.println(t1);

        Stormtrooper t2 = (Stormtrooper) t1.clone();
        System.out.println(t2);
        System.out.println(t1);

        Stormtrooper t3 = (Stormtrooper) t1.clone();
        System.out.println(t3);
    }
}
