package swingy.mvc.models.Hero;

import swingy.mvc.models.Artifact.Artifact;

import javax.validation.constraints.NotNull;

public class Player {

    public Player() {

    }

    @NotNull
    private Artifact art;

    @NotNull
    private HeroStats heroStats = new HeroStats();

    @NotNull
    private String newPlayer;


    protected Player(String newPlayer, HeroStats heroStats, Artifact art) {
        this.newPlayer = newPlayer;
        this.heroStats = heroStats;
        this.art = art;
    }

    public String getNewPlayer() {
        return this.newPlayer;
    }

    public HeroStats getHeroStats() {
        return this.heroStats = heroStats;
    }

    public Artifact getArt() {
        return art;
    }

    public void setArt(Artifact art) {
        this.art = art;
    }


}
