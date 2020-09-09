package scoreuses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * a HighScoresTable class.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> scores;
    private int maxTableSize = 10;

    /**
     * HighScoresTable - constructor. Create an empty high-scores table with the specified size.
     *
     * @param size the size means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.maxTableSize = size;
        this.scores = new ArrayList<>();
    }


    /**
     * add - add a high-score.
     *
     * @param score the high score to add.
     */
    public void add(ScoreInfo score) {
        int index = this.getRank(score.getScore());
        this.scores.add((index - 1), score);
        if (this.scores.size() > this.maxTableSize) {
            this.scores.remove(this.maxTableSize);
        }
//        if (this.scores.size() < this.maxTableSize) {
//            this.scores.add(score);
//            this.scores.sort(new HighScoreComparator());
//        }
    }

    /**
     * size.
     *
     * @return the score table size.
     */
    public int size() {
        return this.scores.size();
    }

    /**
     * getHighScores.
     *
     * @return the current high scores. the list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * getRank.
     *
     * @param score the current score.
     * @return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     */
    public int getRank(int score) {
        int minSize = this.findMinEq(this.maxTableSize, this.scores.size());
        for (int i = 0; i < minSize; i++) {
            if (score > this.scores.get(i).getScore()) {
                return (i + 1);
            }
        }
        return (this.scores.size() + 1);
    }

    /**
     * clear - Clears the table.
     */
    public void clear() {
        List<ScoreInfo> listToClear = this.scores;
        this.scores.removeAll(listToClear);
    }

    /**
     * load - load table data from file. Current table data is cleared.
     *
     * @param filename the file name.
     * @throws IOException - IOException.
     */
    public void load(File filename) throws IOException {
        try {
            this.scores = loadFromFile(filename).getHighScores();
            this.save(filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * save - save table data to the specified file.
     *
     * @param filename the file name.
     * @throws IOException - IOException.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * loadFromFile.
     *
     * @param filename the file name.
     * @return - read a table from file and return it. If the file does not exist,
     * or there is a problem with reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream objectInputStream = null;
        HighScoresTable highScoreTable = new HighScoresTable(0);
        try {

            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            highScoreTable = (HighScoresTable) objectInputStream.readObject();
            highScoreTable.save(filename);
            return highScoreTable;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename.getName());
            return highScoreTable;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename.getName());
            return highScoreTable;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return highScoreTable;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * findMinEq.
     *
     * @param first  the first number to compare
     * @param second the second number to compare
     * @return the minimum number of the two (or just the first one if they are equal)
     */
    public int findMinEq(int first, int second) {
        if (first <= second) {
            return first;
        } else {
            return second;
        }
    }

    /**
     * getMaxTableSize.
     *
     * @return this MaxTableSize.
     */
    public int getMaxTableSize() {
        return this.maxTableSize;
    }

}
