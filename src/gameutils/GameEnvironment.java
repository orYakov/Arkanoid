package gameutils;

import geometry.Line;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * a GameEnvironment class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class GameEnvironment {
    private List<Collidable> colList;

    /**
     * GameEnvironment - construct a GameEnvironment.
     */
    public GameEnvironment() {
        this.colList = new ArrayList<>();
    }

    /**
     * addCollidable - add the given collidable to the environment.
     *
     * @param c the collidable to add to this environment
     */
    public void addCollidable(Collidable c) {
        colList.add(c);
    }

    /**
     * removeCollidable - remove the given collidable to the environment.
     *
     * @param c the collidable to remove from this environment
     */
    public void removeCollidable(Collidable c) {
        colList.remove(c);
    }

    /**
     * CollisionInfo.
     *
     * @param trajectory the line that we assume the object is moving on, from line.start() to line.end().
     * @return null, if this object will not collide with any of the collidables in this collection.
     * Otherwise, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> colInfoList = new ArrayList<>();
        for (int i = 0; i < colList.size(); i++) {
            CollisionInfo colInfo = new CollisionInfo(colList.get(i), trajectory);
            if (colInfo.isThereCollision(trajectory)) {
                colInfoList.add(colInfo);
            }
        }
        if (colInfoList.size() == 0) {
            return null;
        } else {
            CollisionInfo closestCollision;
            double min = trajectory.start().distance(colInfoList.get(0).collisionPoint());
            int index = 0;
            for (int i = 0; i < colInfoList.size(); i++) {
                if (min > trajectory.start().distance(colInfoList.get(i).collisionPoint())) {
                    min = trajectory.start().distance(colInfoList.get(i).collisionPoint());
                    index = i;
                }
            }
            closestCollision = colInfoList.get(index);
            return closestCollision;
        }
    }
}
