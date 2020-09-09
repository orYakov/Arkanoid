package readers;

import biuoop.KeyboardSensor;
import gameutils.AnimationRunner;
import gameutils.GameFlow;
import gameutils.MenuAnimation;
import interfaces.LevelInformation;
import scoreuses.HighScoresTable;
import tasks.PlayGameTask;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level sets reader.
 */
public class LevelSetsReader {
    private String fileName;
    //private List<String> lines = new ArrayList<>();
    //private Map<String, String> keyAndLevelMap = new TreeMap<>();
    //private Map<Map<String, String>, String> keyLevelAndPathMap= new TreeMap<>();
    private List<LevelInformation> levelsInfo = new ArrayList<>();
    private String title;
    private KeyboardSensor ks;
    private AnimationRunner ar;
    private GameFlow gameFlow;
    private HighScoresTable table;
    private File highscores;

    /**
     * Instantiates a new Level sets reader.
     *
     * @param fileName   the file name
     * @param title      the title
     * @param ks         the ks
     * @param ar         the ar
     * @param gameFlow   the game flow
     * @param table      the table
     * @param highscores the highscores
     */
    public LevelSetsReader(String fileName, String title, KeyboardSensor ks, AnimationRunner ar,
                           GameFlow gameFlow, HighScoresTable table, File highscores) {
        this.fileName = fileName;
        this.title = title;
        this.ks = ks;
        this.ar = ar;
        this.gameFlow = gameFlow;
        this.table = table;
        this.highscores = highscores;
    }

    /**
     * createSubMenuFromFile.
     *
     * @return sub menu.
     */
    public MenuAnimation createSubMenuFromFile() {
        MenuAnimation subMenu = new MenuAnimation(this.title, this.ks, this.ar);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.fileName);
        Reader levelInfoReader = new InputStreamReader(is);
        try {
            LineNumberReader reader1 = new LineNumberReader(levelInfoReader);
            String line = reader1.readLine();
            while (line != null) {

                if ((!line.isEmpty()) && (!line.startsWith("#"))) {
                    String[] levSymbolAndDecs = line.split(":");
                    line = reader1.readLine();
                    List<LevelInformation> levelInformationList;
                    LevelSpecificationReader levelReader = new LevelSpecificationReader();
                    InputStream anotherReader =
                            ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                    Reader levelSetsInfoReader = new InputStreamReader(anotherReader);
                    levelInformationList = levelReader.fromReader(levelSetsInfoReader);
                    subMenu.addSelection(levSymbolAndDecs[0], levSymbolAndDecs[1], new PlayGameTask(
                            ar, gameFlow, levelInformationList, table, highscores));
                }
                line = reader1.readLine();
            }
            subMenu.setRunner(ar);
            return subMenu;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
