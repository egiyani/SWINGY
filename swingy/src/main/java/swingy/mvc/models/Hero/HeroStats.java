package swingy.mvc.models.Hero;

public class HeroStats {

    public HeroStats() {
    }

    private int exp;
    private int lvl;
    private int attack;
    private int hitp;
    private int protection;
    private String playerType;
    private int pow;

    public HeroStats(String playerType, int lvl, int attack, int protection, int hitp, int exp) {
        this.playerType = playerType;
        this.lvl = lvl;
        this.protection = protection;
        this.hitp = hitp;
        this.exp = exp;
        this.attack = attack;
        this.pow = attack + protection;
    }

    public String getPlayerType() {
        return (this.playerType);
    }

    public int getExp() {
        return (this.exp);
    }

    public int getLvl() {
        return (this.lvl);
    }

    public int getAttack() {
        return (this.attack);
    }

    public int getHitp() {
        return (this.hitp);
    }

    public int getProtection() {
        return (this.protection);
    }

    public int getPow() {
        return (this.pow);
    }


    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHitp(int hitp) {
        this.hitp += hitp;

        if (this.hitp <= 0) {
            this.hitp = 0;
        }
    }

    public void setProtection(int protection) {
        this.protection += protection;

        if (this.protection <= 0) {
            this.protection = 0;
        }
    }


}