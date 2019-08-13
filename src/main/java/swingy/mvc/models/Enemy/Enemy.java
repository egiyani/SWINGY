package swingy.mvc.models.Enemy;

import swingy.mvc.models.Artifact.*;

public class Enemy {

    public Enemy() {

    }

    private Artifact art;
    private long id;
    private long idCount = 1;
    private static final String[] enemies = {"DragonPriest", "Draugr", "Falmer", "Vampire"};
    private int xCoordinate;
    private int yCoordinate;
    private int idType;

    private int exp;
    private int lvl;
    private int attack;
    private int hitp;
    private int protection;
    private String playerType;
    private int pow;

    private long nextId() {
        idCount += 1;
        return idCount;
    }

    public Enemy(int lvl, int attack, int protection, int hitp, int exp, Artifact art) {
        this.lvl = lvl;
        this.attack = attack;
        this.protection = protection;
        this.hitp = hitp;
        this.exp = exp;
        this.art = art;
        this.pow = protection + attack;
        this.id = nextId();
    }

    public int getxCoordinate() {
        return (this.xCoordinate);
    }

    public int getyCoordinate() {
        return (this.yCoordinate);
    }

    public int getIdType() {
        return (this.idType);
    }

    public int getLvl() {
        return (this.lvl);
    }

    public int getHitp() {
        return (this.hitp);
    }

    public int getPow() {
        return (this.pow);
    }

    public Artifact getArt() {
        return art;
    }


    public void setLvl(int lvl) {
        this.lvl += lvl;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setHitp(int hitp) {
        this.hitp += hitp;
        if (this.hitp <= 0) {
            this.hitp = 0;
        }
    }

    public void setEnemyPosition(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

}

