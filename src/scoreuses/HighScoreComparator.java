package scoreuses;

import java.util.Comparator;

/**
 * a HighScoreComparator class.
 */
public class HighScoreComparator implements Comparator {
    @Override
    public int compare(Object scoreInfo1, Object scoreInfo2) {
        if (scoreInfo1 instanceof ScoreInfo && scoreInfo2 instanceof ScoreInfo) {
            ScoreInfo score1 = (ScoreInfo) scoreInfo1;
            ScoreInfo score2 = (ScoreInfo) scoreInfo2;
            if (score1.getScore() < score2.getScore()) {
                return 1;
            } else if (score1.getScore() > score2.getScore()) {
                return -1;
            } else {
                return 0;
            }
        }
        return -1;
    }
}
