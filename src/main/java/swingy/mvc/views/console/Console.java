package swingy.mvc.views.console;

import swingy.mvc.controller.GameControll;
import swingy.mvc.models.Hero.Player;
import swingy.mvc.reader.ReadFromFile;
import swingy.mvc.views.gui.SwingyView;

import java.util.Scanner;

public class Console {

    public static void start() {
        String hero;
        Player player;
        int type;
        int option = 0;
        int start;
        int createPlayer;

        try {
            createPlayer = DisplayPlayer.setUpHero();
            if (createPlayer == 1) {
                hero = DisplayPlayer.greetPlayer();
                type = DisplayPlayer.printHeroList();
                player = SwingyView.determinePlayer(hero, type);
                start = ShowStats.showInfo(type, hero, player);

                if (start == 1) {
                    GameControll.go(player);
                } else {
                    System.out.println("\nThank You for Playing\n");
                    System.exit(0);
                }
            } else if (createPlayer == 2) {
                ReadFromFile.getBaseOfPlayers();

                Scanner scanner = new Scanner(System.in);

                while (scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    int linesNbr = ReadFromFile.getLinesInFile();
                    if (isNbr(str) == true) {
                        try {
                            int ind = Integer.parseInt(str);
                            if (ind > 0 && ind <= linesNbr) {
                                option = ind;
                                break ;
                            }
                        } catch (Exception exception) {
                            System.out.println("Try one more time.");
                        }
                    } else {
                        System.out.println("Try one more time.");
                    }
                    player = SwingyView.DBPlayer(ReadFromFile.getPlayer(option));
                    GameControll.go(player);
                }
            }
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    public static boolean isNbr(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c) != true) {
                return false;
            }
        }
        return true;
    }


}
