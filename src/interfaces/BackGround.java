package interfaces;


import java.awt.Color;

import biuoop.DrawSurface;
import gameutils.GameLevel;
import geometry.Rectangle;

/**
 * a BackGround interface.
 */
public interface BackGround {
    /**
     * setStroke.
     *
     * @param color the color of the stroke.
     */
    void setStroke(Color color);

    /**
     * Draw on.
     *
     * @param d the drawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * setRec.
     *
     * @param rectangle rectangle.
     */
    void setRec(Rectangle rectangle);

    /**
     * addToGame - add the background to the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to add the background to.
     */
    void addToGame(GameLevel gameLevel);

}
