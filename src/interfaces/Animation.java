package interfaces;

import biuoop.DrawSurface;

/**
 * an Animation interface.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public interface Animation {
    /**
     * doOneFrame - treats the logic of one turn of the game.
     *
     * @param d  the DrawSurface to draw the animation on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop - defines the stopping condition.
     *
     * @return true if the game should stop, and false otherwise.
     */
    boolean shouldStop();
}
