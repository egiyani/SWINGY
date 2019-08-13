package swingy.mvc.controller;

import swingy.mvc.models.Artifact.Armor;
import swingy.mvc.models.Artifact.Helm;
import swingy.mvc.models.Artifact.Weapon;
import swingy.mvc.models.Enemy.Enemy;
import swingy.mvc.models.Hero.Player;
import swingy.mvc.reader.ReadFromFile;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleMap {

    private static ArrayList<Enemy> enemyArray = new ArrayList();
    private static ArrayList<Enemy> tmpArray = new ArrayList();

    private static int size;
    private static int[][] map;
    private int enemyNbr;

    private int xCoordinate;
    private int yCoordinate;

    private static int xmap;
    private static int ymap;

    private boolean set = false;
    private int lvl;

    private static Player player;
    private Enemy enemy = new Enemy();

    public ConsoleMap(Player player) {
        this.player = player;
    }

    public void endOfGame() {
        System.out.println("\nGAME OVER\n\n");
        System.exit(0);
    }

    public void setEnemies() {
        switch (this.enemyNbr = player.getHeroStats().getLvl() * 8) {

        }
    }

    public static void setMap() {
        size = (player.getHeroStats().getLvl() - 1) * 5 + 10 - (player.getHeroStats().getLvl() % 2);
        xmap = size;
        ymap = size;
        map = new int[size][size];
    }

    public void setPlayerPosition() {
        int x = 0;
        int y = 0;
        if ((size % 2) == 1) {
            x = (int)(size / 2);
            y = (int)(size / 2);
        } else if ((size % 2) == 0) {
            x = (size / 2);
            y = (size / 2);
        }
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void victory() {
        if (player.getHeroStats().getExp() > 1000 && player.getHeroStats().getExp() < 2450) {
            this.lvl = 1;
        } else if (player.getHeroStats().getExp() >= 2450 && player.getHeroStats().getExp() < 4800) {
            this.lvl = 2;
        } else if (player.getHeroStats().getExp() >= 4800 && player.getHeroStats().getExp() < 8050) {
            this.lvl = 3;
        } else if (player.getHeroStats().getExp() >= 8050 && player.getHeroStats().getExp() < 12200) {
            this.lvl = 4;
        } else if (player.getHeroStats().getExp() == 12200) {
            this.lvl = 5;
        }

        if (this.lvl > player.getHeroStats().getLvl()) {
            player.getHeroStats().setLvl(this.lvl);
            ReadFromFile.refreshFile(player);
            System.out.println("SUCCESS\n\n");
            System.out.println("1. CONTINUE.\n2. EXIT\n");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int ch = Integer.parseInt(str);
                    if (ch == 1) {
                        enemyArray.removeAll(enemyArray);
                        GameControll.go(player);
                        System.out.println("CONTINUE");
                    } else if (ch == 2) {
                        System.out.println("Thanks for playing SWINGY!\n\n");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Select 1 or 2!");
                }
            }
        } else if (this.lvl == player.getHeroStats().getLvl()) {
            enemyArray.removeAll(enemyArray);
        }
    }

    public void upgrdExp(int type) {
        if (type == 1) {
            int exp;
            if (player.getHeroStats().getExp() < 2450) {
                exp = 2450;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 4800) {
                exp = 4800;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() > 8050) {
                exp = 8050;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 12200) {
                exp = 12200;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 12201) {
                System.out.println("     GAME OVER     \n\n");
                System.exit(0);
            }
            victory();
        } else if (type == 2) {
            player.getHeroStats().setExp(player.getHeroStats().getExp() + enemy.getPow());
            ReadFromFile.refreshFile(player);
            victory();
        }
    }

    public static void regEnemy(Enemy enemy) {
        if (enemyArray.contains(enemy)) {
            return ;
        }
        enemyArray.add(enemy);
    }

    public static void deleteEnemy(Enemy enemy) {
        if (enemyArray.contains(enemy)) {
           enemyArray.remove(enemy);
        }
    }

    public void createEnemies() {
        for (int i = 0; i < this.enemyNbr; i++) {
            Random rand = new Random();
            int enemyPosX = rand.nextInt(size);
            int enemyPosY = rand.nextInt(size);
            while (enemyPosY == this.yCoordinate || enemyPosX == this.xCoordinate) {
                enemyPosX = rand.nextInt(size);
                enemyPosY = rand.nextInt(size);
            }
            enemy = Heros.newEnemy(player);
            enemy.setEnemyPosition(enemyPosX, enemyPosY);
            regEnemy(enemy);
        }
    }

    public Enemy getEnemyCollision() {
        for (int i = 0; i < enemyArray.size(); i++) {
            if (enemyArray.get(i).getyCoordinate() == this.yCoordinate && enemyArray.get(i).getxCoordinate() == this.xCoordinate) {
                return (enemyArray.get(i));
            }
        }
        return null;
    }

    public void showGameField() {
        if (set = false) {
            setMap();
            setPlayerPosition();
            setEnemies();
            if (tmpArray.isEmpty()) {
                createEnemies();
            } else {
                enemyArray.addAll(tmpArray);
            }
            set = true;
        }

        //initialize map array to zeros
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        // initialize enemies
        for (Enemy enemy : enemyArray) {
            map[enemy.getyCoordinate()][enemy.getxCoordinate()] = enemy.getIdType();
        }

        //initialize hero
        map[this.yCoordinate][this.xCoordinate] = 4;

        // check collision with enemy
        for (Enemy enemy : enemyArray) {
            boolean collision = enemyCollision(this.xCoordinate, this.yCoordinate, enemy.getyCoordinate(), enemy.getxCoordinate());
            if (collision == true) {
                break ;
            }
        }

        System.out.println("Lvl: " + player.getHeroStats().getLvl() + " | " + "Attack: " + player.getHeroStats().getAttack() + " | " +
                "Protection: "+ player.getHeroStats().getProtection() + " | " + "Hit Points: " + player.getHeroStats().getHitp() + " | " +
                "Exp: " + player.getHeroStats().getExp() + "Artif: " + player.getArt().getArtType() + "\n\n");

        for (int y = 0; y < ymap; y++) {
            for (int x = 0; x < xmap; x++) {
                switch (map[y][x]) {
                    case 0:
                        System.out.println("|   |");
                        break ;
                    case 1:
                        System.out.println("| m |");
                        break ;
                    case 2:
                        System.out.println("| s |");
                        break ;
                    default:
                        System.out.println("| H |");
                        break ;
                }
            }
            System.out.println();
        }

    }

    public void updatePlayerPos(int xpos, int ypos) {
        int previousX = this.xCoordinate;
        int previousY = this.yCoordinate;
        this.xCoordinate += xpos;
        if (this.xCoordinate < 0) {
            this.xCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else if (this.xCoordinate >= this.size) {
            this.xCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else {
            showGameField();
        }

        this.yCoordinate += ypos;
        if (this.yCoordinate < 0) {
            this.yCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else if (this.yCoordinate >= this.size) {
            this.yCoordinate = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGameField();
        } else {
            showGameField();
        }
    }

    public boolean enemyCollision(int heroY, int heroX, int enemyY, int enemyX) {
        if ((heroX == enemyX) && (heroY == enemyY)) {
            System.out.println("You faced an Enemy , what you gonna to do: \n");
            System.out.println("1. RUN.\n2. FIGHT.\n");
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int ch = Integer.parseInt(str);
                    if (ch == 1) {
                        Random rand = new Random();
                        int go = rand.nextInt(2) + 1;
                        if (go == 1) {
                            System.out.println("You're a coward! You loose 5XP\n");
                            System.out.println("Your XP: " + (player.getHeroStats().getExp() - 5));
                            showGameField();
                        }
                    } else if (ch == 2) {
                        Enemy collided = getEnemyCollision();
                        int victorious = GameControll.fight(player, collided);
                        if (victorious == 1) {
                            battle(collided);
                            deleteEnemy(collided);
                            return true;
                        } else {
                            endOfGame();
                            break ;
                        }
                    } else {
                        System.out.println("Choose 1 or 2!");
                    }
                } else {
                    System.out.println("Choose 1 or 2!");
                }
            }
        }
        return false;
    }

    public void battle(Enemy collided) {
        enemyArray.remove(collided);
        upgrdExp(2);
        if (GameControll.luck() == true) {
            System.out.println("Enemy dropped an artifact: " + collided.getArt().getArtType() + "\n");
            System.out.println("1. Pick it up.\n2. Continue playing.");

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.matches("\\s*[1-2]\\s*")) {
                    int ch = Integer.parseInt(str);
                    if (ch == 1) {
                        String art = enemy.getArt().getArtType();
                        if (art.equals("Helm")) {
                            Helm h = new Helm("Helm");
                            player.setArt(h);
                            player.getHeroStats().setHitp(75);
                            ReadFromFile.refreshFile(player);
                            GameControll.go(player);
                            System.out.println("HELM");
                        } else if (art.equals("Weapon")) {
                            Weapon w = new Weapon("Weapon");
                            player.setArt(w);
                            player.getHeroStats().setAttack(65);
                            ReadFromFile.refreshFile(player);
                            GameControll.go(player);
                            System.out.println("WEAPON");
                        } else if (art.equals("Armor")) {
                            Armor a = new Armor("Armor");
                            player.setArt(a);
                            player.getHeroStats().setProtection(55);
                            ReadFromFile.refreshFile(player);
                            GameControll.go(player);
                            System.out.println("ARMOR");
                        }
                    } else if (ch == 2) {
                        upgrdExp(2);
                    }
                }
                System.out.println("Try one more time.");
            }
        } else {
            upgrdExp(2);
            System.out.println("You win! 500 EXP Gained.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                System.exit(0);
            }
            GameControll.go(player);
        }
    }

}
