package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * a BackGround1 class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class BackGround1 implements Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.blue);
        d.drawCircle(400, 150, 50);
        d.drawCircle(400, 150, 100);
        d.drawCircle(400, 150, 150);
        d.drawLine(400, 0, 400, 310);
        d.drawLine(245, 150, 555, 150);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }
}
