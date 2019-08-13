package swingy.mvc.views.gui;

import swingy.mvc.controller.Heros;
import swingy.mvc.models.Artifact.Armor;
import swingy.mvc.models.Artifact.Artifact;
import swingy.mvc.models.Artifact.Helm;
import swingy.mvc.models.Artifact.Weapon;
import swingy.mvc.models.Hero.HeroStats;
import swingy.mvc.models.Hero.Player;

public class SwingyView {

    private static String art;
    private static String statistics;

    private static Player DBPlayer = new Player();
    private static Player newPlayer = new Player();

    private static int lvl;
    private static int attack;
    private static int protection;
    private static int hitp;
    private static int exp;

    public static Player addPlayer(String type, String player) {
        art = Artifact.randomArt();
        if (art.equals("ARMOR")) {
            Armor ar = new Armor("Armor");
            lvl = 1;
            attack = 100;
            protection = ar.getProtection() + 100;
            exp = 1000;
            hitp = 100;
            HeroStats heroStats = new HeroStats(type, lvl, attack, protection, hitp, exp);
            newPlayer = Heros.newHero(type, player, heroStats, ar);
            statistics = type + " " + player + " " + lvl + " " + attack + " " + protection + " " + hitp + " " + exp + " " + art;
        } else if (art.equals("WEAPON")) {
            Weapon w  = new Weapon("Weapon");
            lvl = 1;
            attack = w.getAttackType() + 100;
            protection = 100;
            exp = 1000;
            hitp = 100;
            HeroStats heroStats = new HeroStats(type, lvl, attack, protection, hitp, exp);
            newPlayer = Heros.newHero(type, player, heroStats, w);
            statistics = type + " " + player + " " + lvl + " " + attack + " " + protection + " " + hitp + " " + exp + " " + art;
        } else if (art.equals("HELM")) {
            Helm h = new Helm("Helm");
            lvl = 1;
            attack = h.getHitPointAmount() + 100;
            protection = 100;
            exp = 1000;
            hitp = 100;
            HeroStats heroStatistics = new HeroStats(type, lvl, attack, protection, hitp, exp);
            newPlayer = Heros.newHero(type, player, heroStatistics, h);
            statistics = type + " " + player + " " + lvl + " " + attack + " " + protection + " " + hitp + " " + exp + " " + art;
        }
        return newPlayer;
    }

    public static Player determinePlayer(String hero, long kind) {
        if (kind == 1) {
            return addPlayer("Nord", hero);
        } else if (kind == 2) {
            return addPlayer("DarkElf", hero);
        } else if (kind == 3) {
            return addPlayer("Khajit", hero);
        } else if (kind == 4) {
            return addPlayer("Ork", hero);
        } else {
            return null;
        }
    }

    public static Player DBPlayer(String player) {
        String[] elements = player.split(" ");
        String kind = elements[0];
        String hero = elements[1];
        int lvl = Integer.parseInt(elements[2]);
        int attack = Integer.parseInt(elements[3]);
        int protection = Integer.parseInt(elements[4]);
        int hitp = Integer.parseInt(elements[5]);
        int exp = Integer.parseInt(elements[6]);
        String art = elements[7];

        HeroStats heroStats = new HeroStats(kind, lvl, attack, protection, hitp, exp);

        if (art.equals("HELM")) {
            Helm h = new Helm("Helm");
            DBPlayer = Heros.newHero(kind, hero, heroStats, h);
        } else if (art.equals("ARMOR")) {
            Armor ar = new Armor("Armor");
            DBPlayer = Heros.newHero(kind, hero, heroStats, ar);
        } else if (art.equals("WEAPON")) {
            Weapon w = new Weapon("Weapon");
            DBPlayer = Heros.newHero(kind, hero, heroStats, w);
        }
        return DBPlayer;
    }

}
