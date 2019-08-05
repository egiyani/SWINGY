package swingy.mvc.models.Artifact;

public class Armor extends Artifact{

    private int protection = 100;

    public Armor(String armorType) {
        super(armorType);
    }

    public int getProtection() {
        return this.protection;
    }

}
