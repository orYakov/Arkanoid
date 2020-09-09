package gameutils;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * a CountdownAnimation class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop = false;
    private long startTime;
    private double secondsForNumber;

    /**
     * CountdownAnimation - constructor.
     *
     * @param numOfSeconds the number of seconds that the numbers appear on the screen overall.
     * @param countFrom    the number from which to count backwards.
     * @param gameScreen   the game screen to display, represented by a SpriteCollection.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.secondsForNumber = (this.numOfSeconds / this.countFrom) * 1000;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param d  the DrawSurface to draw the animation on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.CYAN);
        d.drawText(400, 300, Integer.toString(this.countFrom), 60);
        if (System.currentTimeMillis() > this.startTime + this.secondsForNumber) {
            this.startTime = System.currentTimeMillis();
            this.countFrom--;
            if (countFrom == 0) {
                this.stop = true;
            }
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
