package gameutils;

import biuoop.DrawSurface;
import interfaces.Sprite;
import objects.Counter;

import java.awt.Color;

/**
 * a LevelIndicator class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * LevelIndicator.
     *
     * @param score the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        int x = 350;
        int y = 17;
        int textFontSize = 18;
        String scoreLine = "Score: " + String.valueOf(this.score.getValue());
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
     * addToGame - add the LevelIndicator to the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to add the LevelIndicator to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removeFromGame - remove the LevelIndicator from the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to remove the LevelIndicator from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
