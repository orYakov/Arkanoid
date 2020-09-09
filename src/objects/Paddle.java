package objects;

import gameutils.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a Paddle class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private double speed = 10;
    private GameLevel g;
    private Block leftBorder;
    private Block rightBorder;
    private Boolean needsChangeByDt = true;
    private int frameRate = 60;

    /**
     * Paddle - construct a paddle given a keyboardSensor and a Rectangle.
     *
     * @param key  the keyboardSensor for the paddle motion.
     * @param rect the rectangle that represents the block.
     */
    public Paddle(biuoop.KeyboardSensor key, Rectangle rect) {
        this.keyboard = key;
        this.rec = rect;
    }

    /**
     * Paddle - construct a Paddle given a keyboardSensor, location, width and height.
     *
     * @param key       the keyboardSensor for the paddle motion
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Paddle(biuoop.KeyboardSensor key, Point upperLeft, double width, double height) {
        this.keyboard = key;
        this.rec = new Rectangle(upperLeft, width, height);
    }

    /**
     * moveLeft - move the paddle left.
     */
    public void moveLeft() {
        double newX = this.rec.getUpperLeft().getX();
        double newY = this.rec.getUpperLeft().getY();
        double width = this.rec.getWidth();
        double height = this.rec.getHeight();
        newX = (newX - speed);
        Rectangle tempPaddleRec = new Rectangle(new Point(newX, newY), width, height);
        Line leftBorderRightEdge = this.leftBorder.getCollisionRectangle().getRightEdge();
        double tempPaddleRecUpLeftX = tempPaddleRec.getUpperLeft().getX();
        double leftBorderRightEdgeX = leftBorderRightEdge.start().getX();
        if (tempPaddleRecUpLeftX < leftBorderRightEdgeX) {
            newX = leftBorderRightEdgeX;
        }
        Color color = this.getCollisionRectangle().getColor();
        this.rec = new Rectangle(new Point(newX, newY), width, height);
        this.rec.setColor(color);
    }

    /**
     * moveRight - move the paddle right.
     */
    public void moveRight() {
        double newX = this.rec.getUpperLeft().getX();
        double newY = this.rec.getUpperLeft().getY();
        double width = this.rec.getWidth();
        double height = this.rec.getHeight();
        newX += speed;
        Rectangle tempPaddleRec = new Rectangle(new Point(newX, newY), width, height);
        Line rightBorderLeftEdge = this.rightBorder.getCollisionRectangle().getLeftEdge();
        double tempPaddleRecUpRightX = tempPaddleRec.getUpperRight().getX();
        double rightBorderLeftEdgeX = rightBorderLeftEdge.start().getX();
        if (tempPaddleRecUpRightX > rightBorderLeftEdgeX) {
            newX = rightBorderLeftEdgeX - width;
        }
        Color color = this.getCollisionRectangle().getColor();
        this.rec = new Rectangle(new Point(newX, newY), width, height);
        this.rec.setColor(color);
    }

    /**
     * timePassed - notify the sprite that time has passed.
     * check if the "left" or "right" keys are pressed, and if so move the paddle accordingly.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
        dt = 1.0 / this.frameRate;
        if (needsChangeByDt) {
            double newSpeed = this.getSpeed() * dt;
            this.setSpeed(newSpeed);
            needsChangeByDt = false;
        }
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * drawOn - draw the block on the given DrawSurface.
     *
     * @param d a DrawSurface to draw the block on
     */
    public void drawOn(DrawSurface d) {
        this.rec.drawOn(d, 100);
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
     * padRegions.
     *
     * @return a list of lines that represent equal regions of the the paddle's upper edge.
     */
    public List<Line> padRegions() {
        List<Line> padRegions = new ArrayList<>();
        int numOfRegions = 5;
        double regionSize = (this.rec.getWidth() / numOfRegions);
        double regionsY = this.rec.getUpLefY();
        double regionX = this.rec.getUpLefX();
        for (int i = 0; i < numOfRegions; i++) {
            Point regStart = new Point((regionX + (i * regionSize)), regionsY);
            Point regEnd = new Point((regionX + ((i + 1) * regionSize)), regionsY);
            Line regionLine = new Line(regStart, regEnd);
            padRegions.add(regionLine);
        }
        return padRegions;
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
        if (this.rec.getUpperEdge().isPointHere(collisionPoint)) {
            double velSpeed = currentVelocity.getSpeed();
            List<Line> padRegions = this.padRegions();
            if (padRegions.get(0).isPointHere(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(-60, velSpeed);
                return v;
            } else if (padRegions.get(1).isPointHere(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(-30, velSpeed);
                return v;
            } else if (padRegions.get(2).isPointHere(collisionPoint)) {
                double dx = currentVelocity.getDx();
                double dy = currentVelocity.getDy();
                dy *= (-1);
                return new Velocity(dx, dy);
            } else if (padRegions.get(3).isPointHere(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(30, velSpeed);
                return v;
            } else if (padRegions.get(4).isPointHere(collisionPoint)) {
                Velocity v = Velocity.fromAngleAndSpeed(60, velSpeed);
                return v;
            }
        } else if (this.rec.getLeftEdge().isPointHere(collisionPoint)
                || this.rec.getRightEdge().isPointHere(collisionPoint)) {
            double dx = currentVelocity.getDx();
            double dy = currentVelocity.getDy();
            dx *= (-1);
            return new Velocity(dx, dy);
        }
        //else:
        return null;
    }

    /**
     * addToGame - Add this paddle to the gameLevel.
     *
     * @param gameLevel the gameLevel to add the paddle to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
        this.g = gameLevel;
        this.leftBorder = this.g.getLeftBlock();
        this.rightBorder = this.g.getRightBlock();
    }

    /**
     * removeFromGame - remove this paddle to the gameLevel.
     *
     * @param gameLevel the gameLevel to remove the paddle from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * setSpeed.
     *
     * @param s the requested speed for the Paddle.
     */
    public void setSpeed(double s) {
        this.speed = s;
    }

    /**
     * getSpeed.
     *
     * @return the speed of the Paddle.
     */
    public double getSpeed() {
        return this.speed;
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
