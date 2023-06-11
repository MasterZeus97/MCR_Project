package Army;

import Army.Troups.Troup;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class Squadron implements Prototypeable {

    private final static int maxSizeSquadron = 10;

    private final List<Troup> troupList = new ArrayList<>();

    /**
     * Méthode pour ajouter une troupe au squadron
     * @param troup Troupe à ajouter
     * @return True : La troupe a bien été ajoutée au squadron - False : Le squadron est plein et la troupe n'a pas été ajoutée
     */
    public boolean add(Troup troup) {
        if(!isFull()){
            troupList.add(troup);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Vide le squadron
     */
    public void clearSquadron(){
        troupList.clear();
    }

    /**
     * Méthode pour récupérer la taille maximum du squadron
     * @return Taille max du squadron
     */
    public int getMaxSize(){
        return maxSizeSquadron;
    }

    /**
     * Méthode pour savoir si le squadron est plein
     * @return True : Le squadron est plein - False : Le squadron n'est pas plein
     */
    public boolean isFull(){
        return troupList.size() >= maxSizeSquadron;
    }

    /**
     * Méthode pour savoir si le squadron est vide
     * @return True : Le squadron ne contient aucune troupe - False : Le squadron contient au moins une troupe
     */
    public boolean isEmpty(){
        return troupList.isEmpty();
    }

    /**
     * Méthode pour obtenir la liste des troupes
     * @return Liste des troupes dans le squadron
     */
    public List<Troup> getTroupList(){
        return troupList;
    }


    public int getTroupNumber(){
        return troupList.size();
    }

    /**
     * Methode pour cloner le squadron. Clone les troupes dans le squadron
     * @return Un nouveau squadron, clone du premier
     */
    @Override
    public Squadron copy() {
        Squadron tmp = new Squadron();
        for(Troup t : troupList){
            tmp.add(t.copy());
        }
        return tmp;
    }

    /**
     * Methode pour soigner toutes les troupes du squadron
     */
    public void heal(){
        for(Troup t : troupList)
            t.heal();
    }

    @Override
    public String toString() {
        return "Squadron{" +
                "troupList=" + troupList +
                '}';
    }
}
