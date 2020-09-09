package backgrounds;


import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * a MenuBackGround class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class MenuBackGround implements Sprite {
    private String title;
    private List<String> keys;
    private List<String> messages;

    /**
     * MenuBackGround - constructor.
     *
     * @param title    - the menu title
     * @param keys     the keys to press
     * @param messages the messages of the keys
     */
    public MenuBackGround(String title, List<String> keys, List<String> messages) {
        this.title = title;
        this.keys = keys;
        this.messages = messages;
    }

    /**
     * drawOn - draw the sprite to the screen.
     *
     * @param d the DrawSurface instance to draw the sprite on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.blue);
        d.drawText(300, d.getHeight() / 2 - 100,
                this.title, 40);
        int y = 300;
        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(300, y, "(" + this.keys.get(i) + ") " + this.messages.get(i), 30);
            y += 40;
        }

//        d.drawText(300, 340, "(" + this.keys.get(1) + ") " + this.messages.get(1), 30);
//        d.drawText(300, 380, "(" + this.keys.get(2) + ") " + this.messages.get(2), 30);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }
}

