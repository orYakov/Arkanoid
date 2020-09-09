package interfaces;

import geometry.Point;
import geometry.Rectangle;
import objects.Ball;
import objects.Velocity;

/**
 * a Collidable interface.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit - Notify the object that we collided with it at collisionPoint with a given velocity..
     *
     * @param collisionPoint  the point where this Collidable object and the other object collided.
     * @param currentVelocity the velocity with which the other object collided with this object.
     * @param hitter          the Ball that hit the Collidable object.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
