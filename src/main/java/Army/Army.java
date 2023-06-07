package Army;

import Army.Troups.Troup;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class Army {
    private final static int maxSizeArmy = 5;

    private final List<Squadron> squadronsList = new ArrayList<>();

    /**
     * Constructeur de l'armée. Crée un nombre de squadron égal à la taille de l'armée
     */
    public Army(){
        for(int i = 0; i < maxSizeArmy; i++){
            squadronsList.add(new Squadron());
        }
    }

    /**
     * Permet de récupérer un squadron spécifique avec un index
     * @param squadronIndex Index du squadron qu'on veut récupérer
     * @return Le squadron à l'index squadronIndex
     */
    public Squadron getSquadron(int squadronIndex){
        if(squadronIndex > 0 && squadronIndex < 5)
            return squadronsList.get(squadronIndex);
        return null;
    }

    /**
     * Vide l'armée
     */
    public void clearArmy(){
        for(Squadron s : squadronsList)
            s.clearSquadron();
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
    public List<Squadron> getSquadronsList(){
        return squadronsList;
    }
}
