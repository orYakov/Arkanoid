package gameutils;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import objects.Counter;
import scoreuses.HighScoresAnimation;
import scoreuses.HighScoresTable;
import scoreuses.ScoreInfo;

import java.io.File;
import java.util.List;

/**
 * a GameFlow class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class GameFlow {
    private Counter score = new Counter();
    private Counter lives = new Counter();
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private boolean won = true;
    private HighScoresTable table;
    private File file;

    /**
     * GameFlow - constructor.
     *
     * @param ar    the AnimationRunner.
     * @param ks    KeyboardSensor.
     * @param table HighScoresTable.
     * @param file  file.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable table, File file) {
        this.runner = ar;
        this.keyboard = ks;
        this.table = table;
        this.file = file;
    }

    /**
     * runLevels.
     *
     * @param levels levels.
     */
    public void runLevels(List<LevelInformation> levels) {
//        Menu<String> menu = new MenuAnimation<String>("Arkanoid", this.keyboard);
//// the parameters to addSelection are:
//// key to wait for, line to print, what to return
//        menu.addSelection("a", "First Choice", "option a");
//        menu.addSelection("b", "Second Choice", "option b");
//        menu.addSelection("c", "Third Choice", "option c");
//
//        runner.run(menu);
//// A menu with the selections is displayed.
//// Runs until user presses "a", "b"  or "c"
//
//        String result = menu.getStatus();
//// result will contain "option a", "option b"
//// or "option c"
//        System.out.println("You chose:" + result);
        int topScores = 5;
//        this.table = new HighScoresTable(topScores);
//        File highscores = new File("highscores");
        try {
            this.table.load(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.lives.increase(7);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner,
                    this.score, this.lives);

            level.initialize();

            while ((level.getBlockCounter().getValue() != 0) && (level.getLives().getValue() != 0)) {
                level.playOneTurn();
                this.lives = level.getLives();
                this.score = level.getScore();
            }
            if (this.lives.getValue() <= 0) {
                this.won = false;
                break;
            }

        }

        //}
        if (this.won) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                    new YouWin(this.keyboard, this.score)));
        } else {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                    new GameOver(this.keyboard, this.score)));
        }
        if (this.table.getRank(this.score.getValue()) <= topScores) {
            DialogManager dialog = this.runner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            //System.out.println(name);
            ScoreInfo scoreInfo = new ScoreInfo(name, this.score.getValue());
            this.table.add(scoreInfo);
        }
        try {
            this.table.save(this.file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY,
                new HighScoresAnimation(this.table, this.keyboard.SPACE_KEY, this.keyboard)));
        //this.runner.getGui().close();
        this.score = new Counter();
    }

    /**
     * HighScoresTable.
     *
     * @return this table.
     */
    public HighScoresTable getTable() {
        return this.table;
    }
}
