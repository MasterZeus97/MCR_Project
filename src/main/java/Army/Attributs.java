package Army;

import java.util.Random;

public class Attributs implements Prototypeable{
    private final String name;
    private int value;
    private int minValue = 0;
    private int maxValue = 100;


    /**
     * Constructeur pour un attribut dont on veut randomiser la valeur
     * @param name Nom de l'attribut
     */
    public Attributs(String name) {
        this.name = name;
        randomizeValue();
    }

    /**
     * Constructeur pour un attribut dont on connait la valeur
     * @param name Nom de l'attribut
     * @param value Valeur de base de l'attribut
     */
    public Attributs(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private Attributs(Attributs attributs){
        this.name = attributs.name;
        this.value = attributs.value;
        this.minValue = attributs.minValue;
        this.maxValue = attributs.maxValue;
    }

    /**
     * Getter pour récupérer la valeur de l'attribut
     * @return Valeur de l'attribut
     */
    public int getValue() {
        return value;
    }

    /**
     * Setteur pour modifier la valeur de l'attribut
     * @param value Nouvelle valeur de l'attribut
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Méthode pour modifier la valeur minimum de la stat. Si la stat est plus basse que minValue, monte la stat
     * au niveau de minValue
     * @param minValue
     */
    public void setMinValue(int minValue) {
        if(value < minValue){
            value = minValue;
        }
        this.minValue = minValue;
    }

    /**
     * Méthode pour modifier la valeur maximum de la stat. Si la stat est plus haute que maxValue, descend la stat
     * au niveau de maxValue
     * @param maxValue Valeur maximal que peut prendre la stat
     */
    public void setMaxValue(int maxValue) {
        if(value > maxValue){
            value = maxValue;
        }
        this.maxValue = maxValue;
    }

    /**
     * Getter pour récupérer le nom de l'attribut
     * @return Nom de l'attribut
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

    @Override
    public Attributs copy() {
        return new Attributs(this);
    }
}
