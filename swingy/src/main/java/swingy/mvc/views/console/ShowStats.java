package swingy.mvc.views.console;

import swingy.mvc.models.Hero.Player;

import java.util.Scanner;

public class ShowStats {

    public static int showInfo(long ch, String hero, Player player) {
        char eol = '\n';
        System.out.println("Welcome to SWINGY!\n\n" + hero + ", your stats: ");

        if (ch == 1) {
            System.out.println("Your choice is a Nord: " + eol);
            System.out.println("Lvl: " + player.getHeroStats().getLvl() + eol + "Attack: " + player.getHeroStats().getAttack() + eol +
                    "Protection: " + player.getHeroStats().getProtection() + eol + "Exp: " + player.getHeroStats().getExp() + eol +
                    "Hit Points: " + player.getHeroStats().getHitp() + eol + "Artif: " + player.getArt().getArtType() + eol + eol);
            chooseStartExit();
        } else if (ch == 2) {
            System.out.println("Your choice is a Dark Elf: " + eol);
            System.out.println("Lvl: " + player.getHeroStats().getLvl() + eol + "Attack: " + player.getHeroStats().getAttack() + eol +
                    "Protection: " + player.getHeroStats().getProtection() + eol + "Exp: " + player.getHeroStats().getExp() + eol +
                    "Hit Points: " + player.getHeroStats().getHitp() + eol + "Artif: " + player.getArt().getArtType() + eol + eol);
            chooseStartExit();
        }else if (ch == 3) {
            System.out.println("Your choice is a Khajit: " + eol);
            System.out.println("Lvl: " + player.getHeroStats().getLvl() + eol + "Attack: " + player.getHeroStats().getAttack() + eol +
                    "Protection: " + player.getHeroStats().getProtection() + eol + "Exp: " + player.getHeroStats().getExp() + eol +
                    "Hit Points: " + player.getHeroStats().getHitp() + eol + "Artif: " + player.getArt().getArtType() + eol + eol);
            chooseStartExit();
        }else if (ch == 4) {
            System.out.println("Your choice is an Ork: " + eol);
            System.out.println("Lvl: " + player.getHeroStats().getLvl() + eol + "Attack: " + player.getHeroStats().getAttack() + eol +
                    "Protection: " + player.getHeroStats().getProtection() + eol + "Exp: " + player.getHeroStats().getExp() + eol +
                    "Hit Points: " + player.getHeroStats().getHitp() + eol + "Artif: " + player.getArt().getArtType() + eol + eol);
            chooseStartExit();
        }
        int check = 0;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.matches("\\s*[1-4]\\s*")) {
                check = Integer.parseInt(str);
                break;
            } else {
                System.out.println("Choose from given options.");
            }
        }
        return check;
    }

    public static void chooseStartExit() {
        System.out.println("1. Start swingy.\n2. Exit swingy.");
    }

}
