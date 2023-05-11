package Army;

import Army.Troups.Troup;

import java.util.ArrayList;
import java.util.List;

public class Squadron implements Prototypeable {
    static int count = 0;
    int squadronNumber;
    private final List<Troup> troupList = new ArrayList<>();

    public Squadron() {
        squadronNumber = ++count;
    }

    public void add(Troup troup){
        troupList.add(troup);
    }

    @Override
    public Prototypeable copy() {
        Squadron tmp = new Squadron();
        for(Troup t : troupList){
            tmp.add((Troup) t.copy());
        }
        return tmp;
    }

    @Override
    public String toString() {
        String returnString = "";
        for(Troup t : troupList)
            returnString = returnString + t + "\n";
        return "Squadron #" + squadronNumber + "\n" + returnString;
    }
}
