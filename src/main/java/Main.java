import Army.*;
import Army.Troups.Droid;
import Army.Troups.Stormtrooper;
import Combat.Combat;
import Player.Player;

import javax.naming.SizeLimitExceededException;

public class Main {
    public static void main(String[] args) throws SizeLimitExceededException {

        Stormtrooper t1 = new Stormtrooper();
        Stormtrooper t2 = (Stormtrooper) t1.copy();
        Stormtrooper t3 = (Stormtrooper) t1.copy();
        Droid d1 = new Droid();
        Droid d2 = (Droid) d1.copy();

        Squadron s = new Squadron();
        try {
            s.add(t1);
            s.add(t2);
            s.add(t3);
            s.add(d1);
            s.add(d2);
        }catch (SizeLimitExceededException e){
            System.out.println(e.getMessage());
        }

        Squadron s2 = (Squadron) s.copy();

        Player p1 = new Player();
        p1.generateArmy();
        Player p2 = new Player();
        p2.generateArmy();

        System.out.println(p1.getArmy().getSquadron(0).getTroupList().size());

        Combat combat = new Combat(p1.getArmy(), p2.getArmy());

        while (!combat.isCombatFinished())
            combat.nextTurn();

        System.out.println(s);
        System.out.println(s2);
    }
}
