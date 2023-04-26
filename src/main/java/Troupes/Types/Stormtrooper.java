package Troupes.Types;

import Troupes.Attributs;
import Troupes.Troupe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stormtrooper extends Troupe {

    //private List<Tuple2<String, Integer>> statList = new ArrayList<>();
    private List<Attributs> statsList = new ArrayList<>();
    private Map<String, Integer> stats = new HashMap<>();

    public Stormtrooper(){
        statsList.add(new Attributs("Health", 10));
        statsList.add(new Attributs("Strenght", 20));
    }

    private Stormtrooper(Stormtrooper stt){
        for(Attributs a : statsList){

        }
    }

    @Override
    public Troupe clone() {
        return new Stormtrooper(this);
    }

    @Override
    public Map<String, Integer> getStatus() {
        return null;
    }
}
