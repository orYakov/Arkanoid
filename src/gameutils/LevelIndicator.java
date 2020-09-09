package gameutils;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * a LevelIndicator class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class LevelIndicator implements Sprite {
    private String levelName;

    /**
     * LevelIndicator.
     *
     * @param levelName the levelName
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        int x = 600;
        int y = 17;
        int textFontSize = 18;
        String scoreLine = "Level Name: " + this.levelName;
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

