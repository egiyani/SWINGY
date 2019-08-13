package swingy.mvc.models.Artifact;

public class Helm extends Artifact {

    private int hitPointAmount = 50;

    public Helm(String hitPointAmount) {
        super(hitPointAmount);
    }

    public int getHitPointAmount() {
        return this.hitPointAmount;
    }

}
