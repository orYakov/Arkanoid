import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameutils.AnimationRunner;
import gameutils.GameFlow;
import gameutils.KeyPressStoppableAnimation;
import gameutils.MenuAnimation;
import interfaces.LevelInformation;

import interfaces.Task;

import readers.LevelSetsReader;

import scoreuses.HighScoresAnimation;
import scoreuses.HighScoresTable;
import tasks.ExitTask;

import tasks.ShowHiScoresTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * an Ass6Game class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Ass6Game {
    /**
     * main - the main function that initializes "GameLevel" class.
     *
     * @param args an array that contains the program arguments.
     */
    public static void main(String[] args) {
//        LevelInformation levelone = new Level1();
//        LevelInformation levelTwo = new Level2();
//        LevelInformation levelThree = new Level3();
//        LevelInformation levelFour = new Level4();
        List<LevelInformation> levelInformationList = new ArrayList<>();
//        Map<Integer, LevelInformation> levelMap = new TreeMap<>();
//        levelMap.put(new Integer(1), levelone);
//        levelMap.put(new Integer(2), levelTwo);
//        levelMap.put(new Integer(3), levelThree);
//        levelMap.put(new Integer(4), levelFour);
//        List<String> levelKeys = new ArrayList<>();
//        for (int i = 1; i <= 4; i++) {
//            Integer num = i;
//            levelKeys.add(num.toString());
//        }
//        boolean contains = false;
//        for (int i = 0; i < args.length; i++) {
//            if (levelKeys.contains(args[i].toString())) {
//                levelInformationList.add(levelMap.get(new Integer(args[i])));
//                contains = true;
//            }
//        }
//        if (!contains) {
//            levelInformationList.add(levelone);
//            levelInformationList.add(levelTwo);
//            levelInformationList.add(levelThree);
//            levelInformationList.add(levelFour);
//        }

        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60);
        KeyboardSensor ks = gui.getKeyboardSensor();

//        gameFlow.runLevels(levelInformationList);
        String levelSetString;
        if (args.length > 0) {
            levelSetString = args[0];
        } else {
            levelSetString = "level_sets.txt";
        }
//            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
//            Reader levelInformation = new InputStreamReader(is);
//            LevelSetsReader levelSetsReader = new LevelSetsReader();
//            levelInformationList = levelSetsReader.fromReader(levelInformation);

//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("definitions/level_definitions.txt");
//        Reader levelInformation = new InputStreamReader(is);
//        LevelSpecificationReader levelReader = new LevelSpecificationReader();
//        levelInformationList = levelReader.fromReader(levelInformation);
        int topScores = 5;
        HighScoresTable table = new HighScoresTable(topScores);
        File highscores = new File("highscores");
        GameFlow gameFlow = new GameFlow(ar, ks, table, highscores);
        try {
            table.load(highscores);
            //table = HighScoresTable.loadFromFile(highscores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", ks, ar);
        KeyPressStoppableAnimation score = new KeyPressStoppableAnimation(ks, "space",
                new HighScoresAnimation(table, "space", ks));

        menu.addSelection("h", "Hi scores", new ShowHiScoresTask(ar,
                score));
//        menu.addSelection("s", "Start Game", new PlayGameTask(ar, gameFlow,
//                levelInformationList, table, highscores));
        LevelSetsReader levelSetsReader = new LevelSetsReader(
                levelSetString, "Choose Difficulty", ks, ar, gameFlow, table, highscores);
        menu.addSubMenu("s", "Start Game", levelSetsReader.createSubMenuFromFile());
        menu.addSelection("q", "Quit", new ExitTask(ar));
// ...
        while (true) {
            ar.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            score.reset();
            menu.reset();
        }

    }

}
