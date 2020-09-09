package objects;


import gameutils.CollisionInfo;
import gameutils.GameLevel;
import gameutils.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * a Ball class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity = new Velocity(0, 0);
    private int leftLimit;
    private int rightLimit;
    private int upLimit;
    private int downLimit;
    private GameEnvironment gameEnvironment;
    private Line trajectory;
    private double epsilon = 1;
    private Boolean needsChangeByDt = true;
    private int frameRate = 60;
    private double dtt = 1.0 / this.frameRate;

    /**
     * Ball - construct a ball given a center point, a radius, and a color.
     *
     * @param center the center point of the ball
     * @param r      the size of the ball - which is the radius of the circle that represents the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Ball - construct a ball given x and y coordinates, a radius, and a color.
     *
     * @param x     the x coordinate of the center point of the ball
     * @param y     the y coordinate of the center point of the ball
     * @param r     the size of the ball - which is the radius of the circle that represents the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * getX.
     *
     * @return the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     *
     * @return the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     *
     * @return the size of the ball, meaning its radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * drawOn - draw the ball on the given DrawSurface.
     *
     * @param surface a DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * setVelocity - set the ball's velocity, given a velocity.
     *
     * @param v a velocity to apply on the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity - set the ball's velocity, given dx and dy sizes.
     *
     * @param dx a dx size
     * @param dy a dy size
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getVelocity.
     *
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getRightLimit.
     *
     * @return the ball's right limit
     */
    public int getRightLimit() {
        return this.rightLimit;
    }

    /**
     * getUpLimit.
     *
     * @return the ball's upper limit
     */
    public int getUpLimit() {
        return this.upLimit;
    }

    /**
     * setLimits - set the ball's limits.
     *
     * @param leftLim  the left limit of the ball
     * @param rightLim the right limit of the ball
     * @param downLim  the lower limit of the ball
     * @param upLim    the upper limit of the ball
     */
    public void setLimits(int leftLim, int rightLim, int downLim, int upLim) {
        this.leftLimit = leftLim;
        this.rightLimit = rightLim;
        this.downLimit = downLim;
        this.upLimit = upLim;
    }

    /**
     * moveOneStep - move the ball one step.
     */
    public void moveOneStep() {
        if (needsChangeByDt) {
            //this.dtt = this.dtt * this.frameRate;
            Velocity vel = new Velocity(this.velocity.getDx(), this.velocity.getDy());
            this.velocity = vel;
            needsChangeByDt = false;
        }
        if (!wouldHit()) {
            this.center = this.velocity.applyToPoint(this.center);
            this.setTrajectory();
        } else {
            CollisionInfo colInfo = this.gameEnvironment.getClosestCollision(trajectory);
            Velocity v = colInfo.collisionObject().hit(this, colInfo.collisionPoint(), this.velocity);
            this.setVelocity(v);
            this.center = this.velocity.applyToPoint(this.center);
            Point colPoint = colInfo.collisionPoint();
            double newX = this.center.getX();
            double newY = this.center.getY();
            Rectangle rec = colInfo.collisionObject().getCollisionRectangle();
            if (rec.getLeftEdge().isPointHere(colPoint)) {
                newX -= this.getSize();
            }
            if (rec.getRightEdge().isPointHere(colPoint)) {
                newX += this.getSize();
            }
            if (rec.getLowerEdge().isPointHere(colPoint)) {
                newY += this.getSize();
            }
            if (rec.getUpperEdge().isPointHere(colPoint)) {
                newY -= this.getSize();
            }
            this.center = new Point(newX, newY);
            this.setTrajectory();
            this.isBallInBlock();
        }
    }

    /**
     * setGameEnvironment - set the ball's gameEnvironment.
     *
     * @param gameEnvi - the gameEnvironment og the ball.
     */
    public void setGameEnvironment(GameEnvironment gameEnvi) {
        this.gameEnvironment = gameEnvi;
    }

    /**
     * setTrajectory - set the ball's trajectory: from its center to the point after setting the velocity.
     */
    public void setTrajectory() {
        double tStartX = Math.round(this.center.getX());
        double tStartY = Math.round(this.center.getY());
        double tEndX = Math.round(this.velocity.applyToPoint(this.center).getX());
        double tEndY = Math.round(this.velocity.applyToPoint(this.center).getY());
        this.trajectory = new Line(tStartX, tStartY, tEndX, tEndY);
    }

    /**
     * wouldHit.
     *
     * @return true if the closest collision point is the end of the trajectory, false otherwise.
     */
    public boolean wouldHit() {
        if (gameEnvironment.getClosestCollision(this.trajectory) == null) {
            return false;
        } else {
            Point colPoint = gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            return this.trajectory.isPointHere(colPoint);
        }
    }

    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
        this.dtt = dt;
        this.moveOneStep();
    }

    /**
     * addToGame - add the ball to the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removeFromGame - remove the ball from the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to remove the ball from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * isBallInBlock.
     *
     * @return true if the ball is in the block, return it to the collision point and change its velocity,
     * return false if the ball isn't in the block.
     */
    public boolean isBallInBlock() {
        CollisionInfo colInfo = this.gameEnvironment.getClosestCollision(this.trajectory);
        if (colInfo != null) {
            Rectangle rec = colInfo.collisionObject().getCollisionRectangle();
            double y = this.center.getY();
            double x = this.center.getX();
            if ((y >= (rec.getUpLefY() - epsilon) && (y <= (rec.getDownRiY() + epsilon)))
                    && (x >= (rec.getUpLefX() - epsilon) && (x <= (rec.getDownRiX() + epsilon)))) {
                this.center = colInfo.collisionPoint();
                double dx = -this.velocity.getDx();
                double dy = -this.velocity.getDy();
                this.setVelocity(dx, dy);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * setFrameRate.
     *
     * @param rate the frame rate.
     */
    public void setFrameRate(int rate) {
        this.frameRate = rate;
    }
}
