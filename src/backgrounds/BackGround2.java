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
public class BackGround2 implements Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.yellow);
        d.fillCircle(150, 150, 50);
        for (int i = 0; i < 10; i++) {
            d.drawCircle(150, 150, (50 + i * 2));
        }
        for (int i = 0; i < 800; i += 5) {
            d.drawLine(150, 150, i, 270);
        }
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }
}
