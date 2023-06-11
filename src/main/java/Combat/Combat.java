package Combat;

import Army.Army;
import Army.Squadron;
import Army.Troups.Troup;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Combat {
    Army army1, army2;
    Squadron squadronActive1, squadronActive2;
    int round;
    Troup activeAttacked;

    List<Troup> timeline;

    public Combat(Army army1, Army army2) {
        this.army1 = army1;
        this.army2 = army2;

        round = 0;

        squadronActive1 = army1.getSquadron(0);
        squadronActive2 = army2.getSquadron(0);

        setTimeline();
    }

    private void setTimeline() {
        timeline = new LinkedList<>();

        for(int i = (100*round) + 1; i <= 100*(round+1); i++){
            for(Troup t : squadronActive1.getTroupList()){
                if ((100 - t.getSpeed()) % i == 0)
                    timeline.add(t);
            }

            for(Troup t : squadronActive2.getTroupList()){
                if ((100 - t.getSpeed()) % i == 0)
                    timeline.add(t);
            }
        }
    }

    public void nextTurn(){
        if(timeline.isEmpty() && !isCombatFinished()){
            round++;
            setTimeline();
        }

        Troup attacker = timeline.remove(0);
        Squadron squadronToAttack;

        if(squadronActive1.getTroupList().contains(attacker))
            squadronToAttack = squadronActive2;
        else
            squadronToAttack = squadronActive1;

        Random random = new Random();

        if(squadronToAttack.getTroupNumber() != 1)
            activeAttacked = squadronToAttack.getTroupList().get(random.nextInt(0, squadronToAttack.getTroupNumber()-1));
        else
            activeAttacked = squadronToAttack.getTroupList().get(0);

        activeAttacked.getAttacked(attacker.attack());
        if(activeAttacked.getStatsList().get(0).getValue() <= 0){
            squadronToAttack.getTroupList().remove(activeAttacked);

            timeline.remove(activeAttacked);

            if(squadronToAttack.getTroupList().isEmpty()){
                if(army1.getSquadronsList().contains(squadronToAttack)){
                    army1.getSquadronsList().remove(squadronToAttack);

                    if(!isCombatFinished())
                        squadronActive1 = army1.getSquadron(0);
                }
                else{
                    army2.getSquadronsList().remove(squadronToAttack);

                    if(!isCombatFinished())
                        squadronActive2 = army2.getSquadron(0);
                }

                round = 0;
                setTimeline();
            }
        }
    }

    public boolean isCombatFinished(){
        return army1.getSquadronsList().isEmpty() && army2.getSquadronsList().isEmpty();
    }

    public Troup getTroupAttacked(){
        return activeAttacked;
    }

    public Troup getTroupAttacker(){
        return timeline.get(0);
    }


}
