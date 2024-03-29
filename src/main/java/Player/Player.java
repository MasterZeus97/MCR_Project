package Player;

import Army.*;
import GUI.TroupGenerator;

/**
 * Classe permettant de stocker les information d'un joueur, telles que les crédits ou l'armée actuelle
 *
 * @author Marzullo Loris
 * @author Seem Thibault
 * @author Dos Santos Joel
 */
public class Player {
    private final Army army;
    private int money;
    static final int startMoney = 5000;
    static final int maxMoney = 200000;
    static final int minMoney = 0;

    /**
     * Constructeur de joueur. Crée une armée vide et donne une quandtité d'argent minimum pour commencer
     */
    public Player() {
        this.army = new Army();
        this.money = startMoney;
    }

    /**
     * Récupère l'armée du joueur
     * @return l'armée du joueur
     */
    public Army getArmy() {
        return army;
    }

    public int getMoney(){
        return money;
    }

    public Army generateArmy(){
        for(Squadron s : army.getSquadronsList()){
            for(int i = 0; i < s.getMaxSize(); i++) {
                s.add(TroupGenerator.getRandomTroup());
            }
        }
        return this.army;
    }

    /**
     * Ajoute de l'argent à la solde du joueur
     * @param moneyToAdd argent à ajouter au solde du joueur
     */
    public void getPayed(int moneyToAdd){
        if(money < maxMoney){
            money += moneyToAdd;
            if(money >= maxMoney){
                money = maxMoney;
            }
        }
    }

    /**
     * Méthode pour payer de l'argent
     * @param moneyToPay Argent à déduire du solde du joueur
     * @return True - L'argent a bien été débiter / False - Le solde restant n'est pas suffisant, rien n'a été débité
     */
    public boolean pay(int moneyToPay){
        if(money - moneyToPay >= minMoney){
            money -= moneyToPay;
            return true;
        }else{
            return false;
        }
    }
}
