package Army;

import java.util.Random;

public class Stat implements Prototypeable{
    private final String name;
    private int value;
    private int minValue = 0;
    private int maxValue = 100;

    private double percentReduce;



    private double maxValueReduce = maxValue * percentReduce;

    /**
     * Constructeur pour une statistique dont on veut set les min et max
     * @param name nom de la statistique
     * @param minValue valeur minimum
     * @param maxValue
     */
    public Stat(String name, int minValue, int maxValue, int percentReduce) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.percentReduce = (percentReduce % 101) / 100.0;
        this.name = name;
        randomizeValue();
    }

    /**
     * Constructeur pour un Stats dont on connait la valeur
     * @param name Nom de l'Stats
     * @param value Valeur de base de l'Stats
     */
    public Stat(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Constructeur de copie d'un stat
     * @param stat
     */
    private Stat(Stat stat){
        this.name = stat.name;
        this.value = stat.value;
        this.minValue = stat.minValue;
        this.maxValue = stat.maxValue;
    }

    /**
     * Getter pour récupérer la valeur de l'Stats
     * @return Valeur de l'Stats
     */
    public int getValue() {
        return (int) value;
    }

    /**
     * Setteur pour modifier la valeur de l'Stats
     * @param value Nouvelle valeur de l'Stats
     */

    public void setValue(int value) {
        if(value > maxValue){
            this.value = maxValue;
        }else {
            this.value = value;
        }
    }

    /**
     * Getter pour récupérer le nom de l'Stats
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
        value = random.nextInt(maxValue - minValue) + minValue;
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
            value -= random.nextInt((int) Math.ceil(maxValueReduce) - 1)+1;
            if(value < 1)
                value = 1;
        }
    }

    /**
     * Méthode pour potentiellement améliorer une stat
     * @param chanceToUpgrade Chance que la stat soit améliorée
     */
    public void upgradeValue(int chanceToUpgrade){
        int upChance = chanceToUpgrade % 100;
        Random random = new Random();
        int checkChange = random.nextInt(100 - 1) + 1;
        if(value < maxValue && checkChange <= upChance){
            value += random.nextInt((int) Math.ceil(maxValueReduce) - 1)+1;
        }
    }

    /**
     * Methode pour cloner l'Stats.
     * @return Un nouvel Stats, clone du premier
     */
    @Override
    public Stat copy() {
        return new Stat(this);
    }
}
