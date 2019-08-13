package swingy;

import swingy.mvc.views.console.Console;
import swingy.mvc.views.gui.GUI;
import swingy.mvc.writer.WriteToFile;


public class Main {

    public static void main(String[] args) {

        try {
            WriteToFile.create();

            if (args.length != 1) {
                System.out.println("Usage: java -jar swingy.jar [gui/console]");
                System.exit(1);
            }

            if (args[0].equals("console")) {
                System.out.println("Console View!");
                Console.start();
            } else {
                GUI gui = new GUI();
                gui.displayGUI();
            }
        } finally {
            WriteToFile.close();
        }

    }

}


