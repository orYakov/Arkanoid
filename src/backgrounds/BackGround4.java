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
public class BackGround4 implements Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        Color color = new Color(10, 160, 255);
        d.setColor(color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine((70 + 10 * i), 600, (90 + 10 * i), 400);
        }
        d.setColor(Color.white);
        d.fillCircle(80, 400, 25);
        d.fillCircle(100, 415, 30);
        //d.setColor(Color.lightGray);
        d.fillCircle(115, 385, 30);
        //d.setColor(Color.lightGray);
        d.fillCircle(155, 410, 40);
        //d.setColor(Color.lightGray);
        d.fillCircle(130, 430, 25);
        for (int i = 0; i < 10; i++) {
            d.drawLine((570 + 10 * i), 600, (590 + 10 * i), 500);
        }
        d.fillCircle(590, 490, 25);
        d.fillCircle(610, 515, 30);
        //d.setColor(Color.lightGray);
        d.fillCircle(625, 485, 30);
        //d.setColor(Color.lightGray);
        d.fillCircle(665, 490, 40);
        //d.setColor(Color.lightGray);
        d.fillCircle(640, 515, 25);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }
}
