
package swingy.mvc.controller;

import swingy.mvc.models.Enemy.Enemy;
import swingy.mvc.models.Hero.Player;
import swingy.mvc.reader.ReadFromFile;
import swingy.mvc.views.gui.GUI;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;


public class GUIMap extends JFrame {

    private static ArrayList<Enemy> enemyArray = new ArrayList();
    private static ArrayList<Enemy> tmpArray = new ArrayList();

    private Player player;
    private Enemy enemy = new Enemy();
    private int[][] map;
    private boolean set = false;

    private JTextArea textArea = new JTextArea();

    private JFrame jFrame;

    private static int xCoordinate;
    private static int yCoordinate;

    private int size;
    private int lvl;
    private int xPos;
    private int yPos;
    private int xOld;
    private int yOld;
    private int enemyNbr;

    public GUIMap(Player player, JFrame jFrame) {
        this.player = player;
        this.jFrame = jFrame;
    }

    public void setMap() {
        size = (player.getHeroStats().getLvl() - 1) * 5 + 10 - (player.getHeroStats().getLvl() % 2);
        xCoordinate = size;
        yCoordinate = size;
        map = new int[size][size];
    }

    public void setEnemy() {
        this.enemyNbr = player.getHeroStats().getLvl() * 8;
    }

    public void setPlayerPosition() {
        int x = 0;
        int y = 0;
        if ((size % 2) == 1) {
            x = (int)(size / 2);
            y = (int)(size / 2);
        } else if ((size % 2) == 0) {
            x = size / 2;
            y = size / 2;
        }
        this.xPos = x;
        this.yPos = y;
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
            JOptionPane.showMessageDialog(null, "YOU HAVE LEVELED UP");
            enemyArray.removeAll(enemyArray);
            textArea.append(this.lvl + "\n");
        } else if (this.lvl == player.getHeroStats().getLvl()) {
            textArea.selectAll();
            textArea.replaceSelection("");
            tmpArray.addAll(enemyArray);
            enemyArray.removeAll(enemyArray);
        }
    }

    public void updatePlayerPos(int xPos, int yPos) {
        this.xOld = this.xPos;
        this.yOld = this.yPos;

        this.xPos += xPos;
        if (this.xPos < 0) {
            this.xPos = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGamefield();
        } else if (this.xPos >= this.size) {
            upgrdExp(1);
            victory();
            set = false;
            showGamefield();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
            showGamefield();
        }

        this.yPos += yPos;
        if (this.yPos < 0) {
            this.yPos = (int)(size / 2);
            upgrdExp(1);
            victory();
            set = false;
            showGamefield();
        } else if (this.yPos >= this.size) {
            upgrdExp(1);
            victory();
            set = false;
            showGamefield();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
            showGamefield();
        }
    }

    public JTextArea showGamefield() {
        if (set == false) {
            setMap();
            setPlayerPosition();
            setEnemy();
            createEnemies();
            set = true;
        }

        //initialize map array to zeros
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        //Randomly initialize Enemies
        for (Enemy v : enemyArray) {
            map[v.getyCoordinate()][v.getxCoordinate()] = v.getIdType();
        }

        //initialize player
        map[this.yPos][this.xPos] = 4;

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //collision with enemy
        for (Enemy collision : enemyArray) {
            boolean b = enemyCollision(this.yPos, this.xPos, collision.getyCoordinate(), collision.getxCoordinate());

            if (b == true) {
                enemyArray.remove(enemy);
                set = false;
                showGamefield();
                break ;
            }
        }

        textArea.append("Race: ............... " + player.getHeroStats().getPlayerType() + "\n" +
                "Name: ............. " + player.getNewPlayer() + "\n" +
                "Lvl: .................. " + String.valueOf(player.getHeroStats().getLvl()) + "\n" +
                "Attack: ............ " + player.getHeroStats().getAttack() + "\n" +
                "Protection: ...... " + player.getHeroStats().getProtection() + "\n" +
                "Hit points: ...... " + String.valueOf(player.getHeroStats().getHitp()) + "\n" +
                "Exp: ................ " + String.valueOf(player.getHeroStats().getExp()) + "\n" +
                "Artif: ............... " + String.valueOf(player.getArt().getArtType()).toUpperCase() + "\n\n");

        for (int y = 0; y < yCoordinate; y++) {
            for (int x = 0; x < xCoordinate; x++) {
                switch (map[y][x]) {
                    case 0:
                        String b = "|    |";
                        textArea.append(b);
                        break ;
                    case 1:
                        String o = "| O |";
                        textArea.append(o);
                        break ;
                    case 2:
                        String d = "| d |";
                        textArea.append(d);
                        break ;
                    default:
                        String p = "| P |";
                        textArea.append(p);
                        break ;
                }
            }
            textArea.append("\n");
        }
        return textArea;
    }

    public static void regEnemy(Enemy enemy) {
        if (enemyArray.contains(enemy)) {
            return;
        }
        enemyArray.add(enemy);
    }

    public void createEnemies() {
        for (int i = 0; i < this.enemyNbr; i++) {
            Random rand = new Random();
            int enemyPosX = rand.nextInt(size);
            int enemyPosY = rand.nextInt(size);

            while (enemyPosX == this.xPos || enemyPosY == this.yPos) {
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
            if (enemyArray.get(i).getyCoordinate() == this.yPos && enemyArray.get(i).getxCoordinate() == this.xPos) {
                return (enemyArray.get(i));
            }
        }
        return null;
    }

    public void upgrdExp(int type) {
        GUI gui = new GUI();
        if (type == 1) {
            int exp;
            if (player.getHeroStats().getExp() < 2450) {
                exp = 2450;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 4800) {
                exp = 4800;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 8050) {
                exp = 8050;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 12200) {
                exp = 12200;
                player.getHeroStats().setExp(exp);
            } else if (player.getHeroStats().getExp() < 12201) {
                System.out.println("     GAME ENDED     \n\n");
                gui.endOfGame();
            }
            victory();
        } else if (type == 2) {
            victory();
        }
    }

    public boolean luckySituation() {
        Random rand = new Random();
        int luckySituation = rand.nextInt(2) + 1;
        if (luckySituation == 1) {
            return (true);
        }
        return (false);
    }

    public int fatality() {
        int fatality = 0;
        int victory = 0;
        int shot = 0;
        Random rand = new Random();
        if (luckySituation() == true || player.getHeroStats().getPow() > enemy.getPow()) {
            fatality = 1;
        }
        if (player.getHeroStats().getHitp() > 0) {
            while (player.getHeroStats().getHitp() > 0 && enemy.getHitp() > 0) {
                if (fatality == 0) {
                    shot = rand.nextInt(20) + 1;
                    if (enemy.getHitp() > 0) {
                        player.getHeroStats().setHitp(-shot);
                        ReadFromFile.refreshFile(player);

                        if (player.getHeroStats().getHitp() <= 0) {
                            victory = 0;
                            break ;
                        }
                        fatality = 1;
                    }
                } else if (fatality == 1) {
                    shot = rand.nextInt(50) + 1;
                    if (player.getHeroStats().getHitp() > 0) {
                        enemy.setHitp(-shot);
                        if (enemy.getHitp() <= 0) {
                            victory = 1;
                            break ;
                        }
                        fatality = 0;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You are too weak to fight!");
        }
        return victory;
    }

    public boolean enemyCollision(int heroY, int heroX, int enemyX, int enemyY) {
        if ((heroX == enemyX) && (heroY == enemyY)) {
            enemy = getEnemyCollision();
            int buttonForDialog = JOptionPane.YES_NO_OPTION;
            int buttonForResult = JOptionPane.showConfirmDialog(this, "Will you fight with your enemy?", "Fight or Flight?", buttonForDialog);

            if (buttonForResult == 0) {
                if (fatality() == 1) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "You're DEAD\n\n");
                    jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                }
            } else {
                Random rand = new Random();
                int go = rand.nextInt(2) + 1;
                if (go == 1) {
                    textArea.selectAll();
                    textArea.replaceSelection("");
                    textArea.append("Duck\n\n");
                    this.xPos = this.xOld;
                    this.yPos = this.yOld;
                } else {
                    if (fatality() == 1) {
                        enemyArray.remove(enemy);
                        upgrdExp(2);
                        return (true);
                    } else {
                        JOptionPane.showMessageDialog(null, "YOU'RE DEAD!\n\n");
                        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                    }
                }
            }
        }
        return (false);
    }

}
