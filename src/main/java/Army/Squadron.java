package Army;

import Army.Troups.Troup;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class Squadron implements Prototypeable {

    private final static int maxSizeSquadron = 10;

    private final List<Troup> troupList = new ArrayList<>();

    public Squadron() {

    }

    public void add(Troup troup) throws SizeLimitExceededException {
        if(maxSizeSquadron > 10){
            throw new SizeLimitExceededException("Squadron is already full");
        }else{
            troupList.add(troup);
        }
    }

    public void clearSquadron(){
        troupList.clear();
    }

    public int getMaxSize(){
        return maxSizeSquadron;
    }

    public boolean isFull(){
        if(troupList.size() < 10){
            return false;
        }else{
            return true;
        }
    }

    List<Troup> getTroupList(){
        return troupList;
    }

    @Override
    public Squadron copy() {
        Squadron tmp = new Squadron();
        for(Troup t : troupList){
            try {
                tmp.add((Troup) t.copy());
            }catch (SizeLimitExceededException e){
                System.out.println(e.getMessage());
            }
        }
        return tmp;
    }

    @Override
    public String toString() {
        return "Squadron{" +
                "troupList=" + troupList +
                '}';
    }
}
