package swingy.mvc.controller;

import swingy.mvc.models.Enemy.Enemy;
import swingy.mvc.models.Hero.Player;
import swingy.mvc.reader.ReadFromFile;
import swingy.mvc.views.console.DisplayPlayer;

import java.io.Console;

import java.util.Random;

public class GameControll {

    public GameControll() {

    }

    public static boolean luck() {
        Random rand = new Random();
        int l = rand.nextInt(2) + 1;
        if (l == 1) {
            return true;
        }
        return false;
    }

    public static void go(Player player) {
        ConsoleMap map = new ConsoleMap(player);
        map.showGameField();
        DisplayPlayer.showAvailableDirections();
        Console console = System.console();

        while (true) {
            String str = console.readLine();

            if (str.matches("\\s*[1-5]\\s*")) {
                int dir = Integer.parseInt(str);
                if (dir == 1) {
                    map.updatePlayerPos(1, 0);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 2) {
                    map.updatePlayerPos(0, 1);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 3) {
                    map.updatePlayerPos(0, -1);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 4) {
                    map.updatePlayerPos(-1, 0);
                    map.showGameField();
                    DisplayPlayer.showAvailableDirections();
                } else if (dir == 5) {
                    System.exit(0);
                }
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public static int fight(Player player, Enemy enemy) {
        Random rand = new Random();
        int fi = 0;
        int success = 0;
        int shot = 0;

        if (luck() == true || player.getHeroStats().getPow() > enemy.getPow()) {
            fi = 1;
        }
        if (player.getHeroStats().getHitp() > 0) {
            while (player.getHeroStats().getHitp() > 0 && enemy.getHitp() > 0) {
                System.out.println("Your HP: " + player.getHeroStats().getHitp());
                System.out.println("Enemy HP: " + enemy.getHitp());

                if (fi == 0) {
                    shot = rand.nextInt(30) + 1;
                    if (enemy.getHitp() > 0) {
                        player.getHeroStats().setHitp(-shot);
                        ReadFromFile.refreshFile(player);
                        System.out.println("You've been attacked. You've lost" + shot + " HP");
                        if (player.getHeroStats().getHitp() <= 0) {
                            success = 0;
                            break ;
                        }
                        fi = 1;
                    }
                } else if (fi == 1) {
                    shot = rand.nextInt(50) + 1;
                    if (player.getHeroStats().getHitp() > 0) {
                        enemy.setHitp(-shot);
                        System.out.println("You damaged your enemy with " + shot + "points.");
                        if (enemy.getHitp() <= 0) {
                            success = 1;
                            break;
                        }
                        fi = 0;
                    }
                }
            }
        } else {
            System.out.println("You are too weak to fight!\n");
            System.out.println("Your HP is: " + player.getHeroStats().getHitp());
        }
        return success;
    }



}
