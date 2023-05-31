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
     * @throws SizeLimitExceededException Exception lancée si le squadron est plein déjà plein
     */
    public void add(Troup troup) throws SizeLimitExceededException {
        if(isFull()){
            throw new SizeLimitExceededException("Squadron is already full" + troup.toString());
        }else{
            troupList.add(troup);
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
        if(troupList.size() < 10){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Méthode pour obtenir la liste des troupes
     * @return Liste des troupes dans le squadron
     */
    List<Troup> getTroupList(){
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
