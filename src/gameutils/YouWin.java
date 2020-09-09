package gameutils;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import objects.Counter;

import java.awt.Color;

/**
 * a YouWin class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class YouWin implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * YouWin - constructor.
     *
     * @param k     a KeyboardSensor.
     * @param score the score.
     */
    public YouWin(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param d  the DrawSurface to draw the animation on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.cyan);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.blue);
        d.drawText(300, d.getHeight() / 2 - 100,
                "You Win!", 40);
        d.drawText(265, d.getHeight() / 2,
                "Your score is " + this.score.getValue(), 32);
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
