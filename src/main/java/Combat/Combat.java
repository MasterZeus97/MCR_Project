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

    /**
     * Constructeur de la classe
     * @param army1 Armée du joueur
     * @param army2 Armée de l'IA
     */
    public Combat(Army army1, Army army2) {
        this.army1 = army1;
        this.army2 = army2;

        round = 0;

        for(int i = 0; i < army1.getSquadronsList().size(); i++){
            if(!army1.getSquadronsList().get(i).isEmpty())
                squadronActive1 = army1.getSquadronsList().get(i);
        }

        for(int i = 0; i < army2.getSquadronsList().size(); i++){
            if(!army2.getSquadronsList().get(i).isEmpty())
                squadronActive2 = army2.getSquadronsList().get(i);
        }

        setTimeline();
    }

    /**
     * Initialise la liste mettant en place la chronolgie d'attaque des troupes
     */
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

    /**
     * Cette méthode implémente le fonctionnement d'un tour d'un combat. Il prend la prochaine troupe devant attaquer
     * et attaque une troupe adversaire de manière aléatoire. Il vérifie ensuite si la troupe est morte.
     */
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
        if(activeAttacked.getHp() <= 0){
            squadronToAttack.getTroupList().remove(activeAttacked);

            timeline.remove(activeAttacked);

            if(squadronToAttack.getTroupList().isEmpty()){
                if(army1.getSquadronsList().contains(squadronToAttack)){
                    if(!isCombatFinished()){
                        squadronActive1 = null;

                        for(int i = 0; i < army1.getSquadronsList().size(); i++){
                            if(!army1.getSquadronsList().get(i).isEmpty())
                                squadronActive1 = army1.getSquadronsList().get(i);
                        }
                    }
                }
                else{
                    if(!isCombatFinished()){
                        squadronActive2 = null;

                        for(int i = 0; i < army2.getSquadronsList().size(); i++){
                            if(!army2.getSquadronsList().get(i).isEmpty())
                                squadronActive2 = army2.getSquadronsList().get(i);
                        }
                    }
                }

                round = 0;
                setTimeline();
            }
        }
    }

    /**
     * Indique si le combat est terminé
     * @return True si le combat est fini
     */
    public boolean isCombatFinished(){
        boolean squadronsState1 = true,
                squadronsState2 = true;

        for(int i = 0; i < army2.getSquadronsList().size(); i++){
            if(!army2.getSquadronsList().get(i).isEmpty())
                squadronsState2 = false;
        }

        for(int i = 0; i < army1.getSquadronsList().size(); i++){
            if(!army1.getSquadronsList().get(i).isEmpty())
                squadronsState1 = false;
        }

        return squadronsState1 || squadronsState2;
    }

    /**
     * Retourne la troupe se faisant attaquer lors de la manche courante
     * @return troupe attaquée
     */
    public Troup getTroupAttacked(){
        return activeAttacked;
    }

    /**
     * Retourne la troupe qui attaque lors de la manche courante
     * @return troupe attaquant
     */
    public Troup getTroupAttacker(){
        return activeAttacker;
    }

    /**
     * Indique si c'est le joueur qui attaque lors de la manche courante
     * @return true si le joueur attaque
     */
    public boolean isPlayerAttacking(){
        for(int i = 0; i < army1.getSquadronsList().size(); i++){
            if(army1.getSquadronsList().get(i).getTroupList().contains(activeAttacker))
                return true;
        }
        return false;
    }

    /**
     * Retourne les dégâts infligés lors de la manche courante
     * @return dégâts infligés
     */
    public int getActiveDamageDealed() {
        return activeDamageDealed;
    }
}
