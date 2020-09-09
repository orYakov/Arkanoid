package gameutils;

import interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * a SpriteCollection class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class SpriteCollection {
    private List<Sprite> spriteList;
    private double dt;

    /**
     * SpriteCollection - construct a SpriteCollection.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }

    /**
     * addSprite - add the given sprite to the collection, i.e the list.
     *
     * @param s the sprite to add to this collection.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * removeSprite - remove the given sprite to the collection, i.e the list.
     *
     * @param s the sprite to remove from this collection.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * notifyAllTimePassed - call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteListCopy = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : spriteListCopy) {
            sprite.timePassed(this.dt);
        }
    }

    /**
     * drawAllOn - call drawOn(d) on all sprites.
     *
     * @param d - the DrawSurface instance to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteListCopy = new ArrayList<Sprite>(this.spriteList);
        for (Sprite sprite : spriteListCopy) {
            Sprite spriteObject = (Sprite) sprite;
            spriteObject.drawOn(d);
        }
    }

    /**
     * setDt.
     *
     * @param dtt the dt.
     */
    public void setDt(double dtt) {
        this.dt = dtt;
    }
}
