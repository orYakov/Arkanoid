package scoreuses;


import java.io.Serializable;

/**
 * a ScoreInfo class.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * ScoreInfo - constractor.
     *
     * @param name  - the player's name.
     * @param score - the player's score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName.
     *
     * @return this name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore.
     *
     * @return this score.
     */
    public int getScore() {
        return this.score;
    }
}
