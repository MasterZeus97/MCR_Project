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
    int round, activeDamageDealed;
    Troup activeAttacked, activeAttacker;

    List<Troup> timeline;

    public Combat(Army army1, Army army2) {
        this.army1 = army1;
        this.army2 = army2;

        round = 0;

        for(int i = 0; i < army1.getSquadronsList().size(); i++){
            if(army1.getSquadronsList().get(i).getTroupNumber() != 0)
                squadronActive1 = army1.getSquadronsList().get(i);
        }

        for(int i = 0; i < army2.getSquadronsList().size(); i++){
            if(army2.getSquadronsList().get(i).getTroupNumber() != 0)
                squadronActive2 = army2.getSquadronsList().get(i);
        }

        setTimeline();
    }

    private void setTimeline() {
        timeline = new LinkedList<>();

        for(int i = (100*round) + 1; i <= 100*(round+1); i++){
            for(Troup t : squadronActive1.getTroupList()){
                if (i % (101 - t.getSpeed()) == 0)
                    timeline.add(t);
            }

            for(Troup t : squadronActive2.getTroupList()){
                if (i % (101 - t.getSpeed()) == 0)
                    timeline.add(t);
            }
        }
    }

    public void nextTurn(){
        if(timeline.isEmpty() && !isCombatFinished()){
            round++;
            setTimeline();
        }

        activeAttacker = timeline.remove(0);
        Squadron squadronToAttack;

        if(squadronActive1.getTroupList().contains(activeAttacker))
            squadronToAttack = squadronActive2;
        else
            squadronToAttack = squadronActive1;

        Random random = new Random();

        if(squadronToAttack.getTroupNumber() > 1)
            activeAttacked = squadronToAttack.getTroupList().get(random.nextInt(0, squadronToAttack.getTroupNumber()-1));
        else
            activeAttacked = squadronToAttack.getTroupList().get(0);

        activeDamageDealed = activeAttacked.getAttacked(activeAttacker.attack());
        if(activeAttacked.getStatsList().get(0).getValue() <= 0){
            squadronToAttack.getTroupList().remove(activeAttacked);

            timeline.remove(activeAttacked);

            if(squadronToAttack.getTroupList().isEmpty()){
                if(army1.getSquadronsList().contains(squadronToAttack)){
                    if(!isCombatFinished()){
                        squadronActive1 = null;

                        for(int i = 0; i < army1.getSquadronsList().size(); i++){
                            if(army1.getSquadronsList().get(i).getTroupNumber() != 0)
                                squadronActive1 = army1.getSquadronsList().get(i);
                        }
                    }
                }
                else{
                    if(!isCombatFinished()){
                        squadronActive2 = null;

                        for(int i = 0; i < army2.getSquadronsList().size(); i++){
                            if(army2.getSquadronsList().get(i).getTroupNumber() != 0)
                                squadronActive2 = army2.getSquadronsList().get(i);
                        }
                    }
                }

                round = 0;
                setTimeline();
            }
        }
    }

    public boolean isCombatFinished(){
        boolean squadronsState1 = true,
                squadronsState2 = true;

        for(int i = 0; i < army2.getSquadronsList().size(); i++){
            if(army2.getSquadronsList().get(i).getTroupNumber() != 0)
                squadronsState2 = false;
        }

        for(int i = 0; i < army1.getSquadronsList().size(); i++){
            if(army1.getSquadronsList().get(i).getTroupNumber() != 0)
                squadronsState1 = false;
        }

        return squadronsState1 || squadronsState2;
    }

    public Troup getTroupAttacked(){
        return activeAttacked;
    }

    public Troup getTroupAttacker(){
        return activeAttacker;
    }

    public boolean isPlayerAttacking(){
        for(int i = 0; i < army1.getSquadronsList().size(); i++){
            if(army1.getSquadronsList().get(i).getTroupList().contains(activeAttacker))
                return true;
        }
        return false;
    }

    public int getActiveDamageDealed() {
        return activeDamageDealed;
    }
}
