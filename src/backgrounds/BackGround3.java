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
public class BackGround3 implements Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green.darker());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.black);
        d.fillRectangle(70, 400, 110, 200);
        d.setColor(Color.white);
        d.fillRectangle(80, 410, 100, 190);
        d.setColor(Color.black);
        for (int i = 0; i < 6; i++) {
            d.fillRectangle(70 + (20 * i), 400, 10, 200);
        }
        for (int i = 0; i < 5; i++) {
            d.fillRectangle(70, 400 + (40 * i), 110, 10);
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(105, 340, 40, 60);
        d.setColor(Color.gray);
        d.fillRectangle(115, 190, 20, 150);
        d.setColor(Color.yellow);
        Color c = new Color(255, 243, 30);
        d.setColor(c);
        d.fillCircle(125, 174, 16);
        d.setColor(Color.red);
        d.fillCircle(125, 174, 10);
        d.setColor(Color.white);
        d.fillCircle(125, 174, 4);

    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }
}
