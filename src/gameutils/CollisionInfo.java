package gameutils;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

import java.util.List;

/**
 * a CollisionInfo class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class CollisionInfo {
    private Line trajectory;
    private Collidable colObject;

    /**
     * CollisionInfo - construct a CollisionInfo instance.
     *
     * @param collidable the collidable object
     * @param traj       the trajectory of the moving object
     */
    public CollisionInfo(Collidable collidable, Line traj) {
        this.colObject = collidable;
        this.trajectory = traj;
    }

    /**
     * isThereCollision - boolean.
     *
     * @param traject the trajectory to check if there is a collision with.
     * @return true if there is a collision point, false otherwise.
     */
    public Boolean isThereCollision(Line traject) {
        this.trajectory = traject;
        Rectangle rec = this.colObject.getCollisionRectangle();
        List<Point> interPoints = rec.intersectionPoints(this.trajectory);
        return !(interPoints.size() == 0);
    }

    /**
     * collisionPoint.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        Rectangle rec = this.colObject.getCollisionRectangle();
        return this.trajectory.closestIntersectionToStartOfLine(rec);
    }

    /**
     * collisionObject.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.colObject;
    }
}
