package scoreuses;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

import java.awt.Color;

/**
 * a HighScoresAnimation class.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endKey;
    private KeyboardSensor keyboard;
    private boolean stop = false;

    /**
     * HighScoresAnimation - constructor.
     *
     * @param scores the scores.
     * @param endKey the key that ends the animation.
     * @param ks     the keyboard sensor.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor ks) {
        this.scores = scores;
        this.endKey = endKey;
        this.keyboard = ks;
    }

    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param d  the DrawSurface to draw the animation on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.scores.size() == 0) {
            d.setColor(Color.cyan);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.green);
            int headLineX = (int) (d.getWidth() * 0.05);
            int headLineY = (int) (d.getHeight() * 0.10);
            d.drawText(headLineX, headLineY, "High Scores", 30);
        } else {
            d.setColor(Color.cyan);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.green);
            int headLineX = (int) (d.getWidth() * 0.05);
            int headLineY = (int) (d.getHeight() * 0.10);
            d.drawText(headLineX, headLineY, "High Scores", 30);
            int subHeadLine1X = (int) (d.getWidth() * 0.10);
            int subHeadLineY = (int) (d.getHeight() * 0.20);
            int subHeadLine2X = (int) (d.getWidth() * 0.65);
            int endOfLineX = (int) (d.getWidth() * 0.90);
            int lineY = (subHeadLineY + 10);
            d.setColor(Color.darkGray);
            d.drawText(subHeadLine1X, subHeadLineY, "Player Name", 25);
            d.drawText(subHeadLine2X, subHeadLineY, "Score", 25);
            d.drawLine(subHeadLine1X, lineY, endOfLineX, lineY);
            int namesX = subHeadLine1X;
            int scoresX = subHeadLine2X;
            int firstNameY = lineY + 40;
            int linesArea = d.getHeight() - firstNameY;
            int lineHeight = linesArea / this.scores.getMaxTableSize();
            for (int i = 0; i < this.scores.size(); i++) {
                d.setColor(Color.blue);
                d.drawText(namesX, (firstNameY + (i * lineHeight)),
                        this.scores.getHighScores().get(i).getName(), 25);
                Integer someScore = this.scores.getHighScores().get(i).getScore();
                d.drawText(scoresX, (firstNameY + (i * lineHeight)),
                        someScore.toString(), 25);
            }
//        if (this.keyboard.isPressed(this.endKey)) {
//            this.stop = true;
//        }
        }
    }

    /**
     * shouldStop - defines the stopping condition.
     *
     * @return true if the game should stop, and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
