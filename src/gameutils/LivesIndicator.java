package gameutils;

import biuoop.DrawSurface;
import interfaces.Sprite;
import objects.Counter;

import java.awt.Color;

/**
 * a LivesIndicator class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * LivesIndicator.
     *
     * @param lives the current number of lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        int x = 100;
        int y = 17;
        int textFontSize = 18;
        String scoreLine = "Lives: " + String.valueOf(this.lives.getValue());
        d.setColor(Color.black);
        d.drawText(x, y, scoreLine, textFontSize);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }

    /**
     * addToGame - add the LivesIndicator to the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to add the LivesIndicator to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removeFromGame - remove the LivesIndicator from the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to remove the LivesIndicator from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
