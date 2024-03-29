package Army;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de stocker les squadron nécessaire à la formation d'une armée
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
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
        if(squadronIndex >= 0 && squadronIndex < maxSizeArmy)
            return squadronsList.get(squadronIndex);
        return null;
    }

    /**
     * Permet de redéfinir un squadron spécifique avec un index
     * @param squadronIndex Index du squadron qu'on veut remplacer
     * @param newSquadron Le nouveau squadron
     */
    public void setSquadron(int squadronIndex, Squadron newSquadron) {
        if(squadronIndex >= 0 && squadronIndex < maxSizeArmy)
            squadronsList.set(squadronIndex, newSquadron);
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
        for(Squadron s : squadronsList){
            if(!s.isFull()){
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode pour savoir si l'armée est vide
     * @return True : L'armée est complètement vide - False : Il y a au moins une troupe dans un bataillon de l'armée
     */
    public boolean isEmpty(){
        for(Squadron s : squadronsList){
            if(!s.getTroupList().isEmpty()){
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode pour obtenir la liste des squadrons
     * @return Liste des squadrons dans le squadron
     */
    public List<Squadron> getSquadronsList(){
        return squadronsList;
    }
}
