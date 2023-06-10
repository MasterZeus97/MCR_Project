package Army;

import java.util.Random;

public class Stat implements Prototypeable{
    private final String name;
    private int value;
    private final int minValue;
    private final int maxValue;

    private final int maxValueReduce;

    /**
     * Constructeur pour une statistique dont on veut set les min et max
     * @param name nom de la statistique
     * @param minValue valeur minimum possible pour cette stat à son initialisation
     * @param maxValue valeur maximum possible pour cette stat à son initialisation
     * @param percentReduce pourcent dont la valeur de la stat va diminuer. Ce pourcentage est calculé en fonction de la
     *                      valeur maximum de la stat
     */
    public Stat(String name, int minValue, int maxValue, double percentReduce) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        percentReduce = (percentReduce % 101) / 100.0;
        this.maxValueReduce = (int) Math.ceil(maxValue * percentReduce);
        this.name = name;
        randomizeValue();
    }

    /**
     * Constructeur de copie d'une stat
     * @param stat stat qu'on souhaite copier
     */
    private Stat(Stat stat){
        this.name = stat.name;
        this.value = stat.value;
        this.minValue = stat.minValue;
        this.maxValue = stat.maxValue;
        this.maxValueReduce = stat.maxValueReduce;
    }

    /**
     * Getter pour récupérer la valeur de la stats
     * @return Valeur de la stats
     */
    public int getValue() {
        return value;
    }

    /**
     * Setteur pour modifier la valeur de la stats
     * @param value Nouvelle valeur de la stats
     */

    public void setValue(int value) {
        this.value = Math.min(value, maxValue);
    }

    /**
     * Getter pour récupérer le nom de la stats
     * @return Nom de l'Stats
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode pour randomiser la valeur de la stat entre minValue et maxValue
     */
    public void randomizeValue(){
        Random random = new Random();
        if(maxValue != minValue) {
            value = random.nextInt(maxValue - minValue) + minValue;
        }else{
            value = maxValue;
        }
    }

    /**
     * Méthode pour potentiellement dégrader la statistique
     * @param chanceToDowngrade Chance que la stat soit baissée
     */
    public void downgradeValue(int chanceToDowngrade){
        int downChance = chanceToDowngrade % 101;

        Random random = new Random();
        int checkChange = random.nextInt(100 - 1) + 1;
        if(value > 1 && checkChange <= downChance){
            value -= maxValueReduce > 1 ? random.nextInt( maxValueReduce - 1)+1 : 1;
          
            if(value < 1)
                value = 1;
        }
    }

    /**
     * Méthode pour potentiellement améliorer une stat
     * @param chanceToUpgrade Chance que la stat soit améliorée
     */
    public void upgradeValue(int chanceToUpgrade){
        int upChance = chanceToUpgrade % 101;
        Random random = new Random();
        int checkChange = random.nextInt(100 - 1) + 1;
        if(value < maxValue && checkChange <= upChance){
            value += maxValueReduce > 1 ? random.nextInt( maxValueReduce - 1)+1 : 1;
        }
    }

    /**
     * Permet de maximiser la valeur de la stat
     */
    public void maximizeVal(){
        this.value = this.maxValue;
    }

    /**
     * Methode pour cloner la stat.
     * @return Un nouvel Stats, clone du premier
     */
    @Override
    public Stat copy() {
        return new Stat(this);
    }

    @Override
    public String toString() {
        return "Stat{" +
                "name='" + name +
                ", value=" + value +
                '}';
    }
}
