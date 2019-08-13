package swingy.mvc.controller;

import swingy.mvc.models.Artifact.Armor;
import swingy.mvc.models.Artifact.Artifact;
import swingy.mvc.models.Artifact.Helm;
import swingy.mvc.models.Artifact.Weapon;
import swingy.mvc.models.Enemy.*;
import swingy.mvc.models.Hero.*;

import java.util.Random;

public class Heros {

    public static Player newHero(String hero, String player, HeroStats heroStats, Artifact art) {
        if (hero.equals("Nord")) {
            return new Nord(player, heroStats, art);
        } else if (hero.equals("DarkElf")) {
            return new DarkElf(player, heroStats, art);
        }else if (hero.equals("Khajit")) {
            return new Khajit(player, heroStats, art);
        }else if (hero.equals("Ork")) {
            return new Ork(player, heroStats, art);
        }else {
            return null;
        }
    }

    public static Enemy newEnemy(Player player) {
        Random rand = new Random();
        int enemies = rand.nextInt(2) + 1;
        String artif = Artifact.randomArt();
        int lvl = 0;
        int attack = 0;
        int protection = 0;
        int exp = 0;
        int hitp = 0;

        if (enemies == 1) {
            if (artif.equals("HELM")) {
                Helm h = new Helm("Helm");
                lvl = player.getHeroStats().getLvl();
                attack = h.getHitPointAmount() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new DragonPriest(lvl, attack, protection, hitp, exp, h));
            } else if (artif.equals("WEAPON")) {
                Weapon w = new Weapon("Weapon");
                lvl = player.getHeroStats().getLvl();
                attack = 100 + w.getAttackType();
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new DragonPriest(lvl, attack, protection, hitp, exp, w));
            } else if (artif.equals("ARMOR")) {
                Armor armor = new Armor("Armor");
                lvl = player.getHeroStats().getLvl();
                attack = 100;
                protection = armor.getProtection();
                hitp = 100;
                exp = 0;
                return (new DragonPriest(lvl, attack, protection, hitp, exp, armor));
            }
        } else if (enemies == 2) {
            if (artif.equals("HELM")) {
                Helm h = new Helm("Helm");
                lvl = player.getHeroStats().getLvl();
                attack = h.getHitPointAmount() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Draugr(lvl, attack, protection, hitp, exp, h));
            } else if (artif.equals("WEAPON")) {
                Weapon w = new Weapon("Wwapon");
                lvl = player.getHeroStats().getLvl();
                attack = w.getAttackType() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Draugr(lvl, attack, protection, hitp, exp, w));
            } else if (artif.equals("ARMOR")) {
                Armor armor = new Armor("Armor");
                lvl = player.getHeroStats().getLvl();
                attack = 100;
                protection = armor.getProtection() + 100;
                hitp = 100;
                exp = 0;
                return (new Draugr(lvl, attack, protection, hitp, exp, armor));
            }
        }else if (enemies == 3) {
            if (artif.equals("HELM")) {
                Helm h = new Helm("Helm");
                lvl = player.getHeroStats().getLvl();
                attack = h.getHitPointAmount() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Falmer(lvl, attack, protection, hitp, exp, h));
            } else if (artif.equals("WEAPON")) {
                Weapon w = new Weapon("Wwapon");
                lvl = player.getHeroStats().getLvl();
                attack = w.getAttackType() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Falmer(lvl, attack, protection, hitp, exp, w));
            } else if (artif.equals("ARMOR")) {
                Armor armor = new Armor("Armor");
                lvl = player.getHeroStats().getLvl();
                attack = 100;
                protection = armor.getProtection() + 100;
                hitp = 100;
                exp = 0;
                return (new Falmer(lvl, attack, protection, hitp, exp, armor));
            }
        }else if (enemies == 4) {
            if (artif.equals("HELM")) {
                Helm h = new Helm("Helm");
                lvl = player.getHeroStats().getLvl();
                attack = h.getHitPointAmount() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Vampire(lvl, attack, protection, hitp, exp, h));
            } else if (artif.equals("WEAPON")) {
                Weapon w = new Weapon("Wwapon");
                lvl = player.getHeroStats().getLvl();
                attack = w.getAttackType() + 100;
                protection = 100;
                hitp = 100;
                exp = 0;
                return (new Vampire(lvl, attack, protection, hitp, exp, w));
            } else if (artif.equals("ARMOR")) {
                Armor armor = new Armor("Armor");
                lvl = player.getHeroStats().getLvl();
                attack = 100;
                protection = armor.getProtection() + 100;
                hitp = 100;
                exp = 0;
                return (new Vampire(lvl, attack, protection, hitp, exp, armor));
            }
        }
        return null;
    }

}
