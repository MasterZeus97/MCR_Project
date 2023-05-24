package Army;

import Army.Troups.Troup;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class Army {
    private final static int maxSizeArmy = 10;

    private final List<Squadron> squadronsList = new ArrayList<>();

    /**
     * Méthode pour ajouter un squadron à l'armée
     * @param squadron Squadron à ajouter
     * @throws SizeLimitExceededException Exception lancée si l'armée est plein déjà plein
     */
    public void add(Squadron squadron) throws SizeLimitExceededException {
        if(isFull()){
            throw new SizeLimitExceededException("Army is already full" + squadron.toString());
        }else{
            squadronsList.add(squadron);
        }
    }

    /**
     * Vide l'armée
     */
    public void clearArmy(){
        squadronsList.clear();
    }

    /**
     * Méthode pour récupérer la taille maximum de l'armée
     * @return Taille max de l'armée
     */
    public int getMaxSize(){
        return maxSizeArmy;
    }

    /**
     * Méthode pour savoir si l'armée est pleine
     * @return True : L'armée est pleine - False : L'armée n'est pas pleine
     */
    public boolean isFull(){
        if(squadronsList.size() < 10){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Méthode pour obtenir la liste des squadrons
     * @return Liste des squadrons dans le squadron
     */
    List<Squadron> getSquadronsList(){
        return squadronsList;
    }
}
