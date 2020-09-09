package interfaces;

import biuoop.DrawSurface;

/**
 * a Sprite interface.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public interface Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void timePassed(double dt);
}
