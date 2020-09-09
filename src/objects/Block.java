package objects;

import gameutils.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.BackGround;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * a Block class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private int hitPoint;
    private double epsilon = 1;
    private List<HitListener> hitListeners;
    private Counter counter;
    private BackGround bg = null;
    private Map<Integer, BackGround> hitPointBg = new TreeMap<>();

    /**
     * Block - construct a block given a rectangle.
     *
     * @param rectangle the rectangle that represents the block
     */
    public Block(Rectangle rectangle) {
        this.rec = rectangle;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Block - construct a block given location, width and height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Block(Point upperLeft, double width, double height) {
        this.rec = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * addHpBackground.
     *
     * @param hPbG hit point background.
     */
    public void addHpBackground(Map<Integer, BackGround> hPbG) {
        this.hitPointBg = hPbG;
    }

    /**
     * drawOn - draw the block on the given DrawSurface.
     *
     * @param surface a DrawSurface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        this.rec.setHitP(this.hitPoint);
        if (hitPointBg.containsKey(this.hitPoint)) {
            bg = hitPointBg.get(this.hitPoint);
            hitPointBg.get(this.hitPoint).drawOn(surface);
        } else if (bg != null) {
            bg.drawOn(surface);
        } else { //default
            this.rec.drawOn(surface, this.hitPoint);
        }
    }

    /**
     * getCollisionRectangle.
     *
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * hit - Notify the object that we collided with it at collisionPoint with a given velocity..
     *
     * @param collisionPoint  the point where this Collidable object and the other object collided.
     * @param currentVelocity the velocity with which the other object collided with this object.
     * @param hitter          the Ball that hit the Collidable object.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if ((Math.abs(collisionPoint.getY() - this.rec.getUpLefY()) <= epsilon)
                || (Math.abs(collisionPoint.getY() - this.rec.getDownRiY()) <= epsilon)) {
            dy *= (-1);
        }
        if ((Math.abs(collisionPoint.getX() - this.rec.getUpLefX()) <= epsilon)
                || (Math.abs(collisionPoint.getX() - this.rec.getDownRiX()) <= epsilon)) {
            dx *= (-1);
        }
        if (this.hitPoint != 100) {
            this.hitPoint -= 1;
        }
        return new Velocity(dx, dy);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {

    }

    /**
     * addToGame - add the block to the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to add the block to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
//        if (!this.hitPointBg.values().isEmpty()) {
//            for (BackGround value:this.hitPointBg.values()) {
//                value.addToGame(gameLevel);
//            }
//        }
        gameLevel.addCollidable(this);
    }

    /**
     * setHitPoint.
     *
     * @param hitNum set the num of hits the block can take.
     */
    public void setHitPoint(int hitNum) {
        this.hitPoint = hitNum;
    }

    /**
     * getHitPoints.
     *
     * @@return the num of hits the block can take.
     */
    public int getHitPoints() {
        return this.hitPoint;
    }

    /**
     * removeFromGame - remove the block from the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * addHitListener  - Add hl as a listener to hit events.
     *
     * @param hl the HitListener object to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifyHit - notifies all of the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter the Ball that hit this Block.
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners.size() == 0) {
            return;
        }
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * getCounter.
     *
     * @return this counter.
     */
    public Counter getCounter() {
        return this.counter;
    }

    /**
     * getHitPointBg.
     *
     * @return this hitPointBg.
     */
    public Map<Integer, BackGround> getHitPointBg() {
        return hitPointBg;
    }

    /**
     * getBg.
     *
     * @return this bg.
     */
    public BackGround getBg() {
        return bg;
    }
}
