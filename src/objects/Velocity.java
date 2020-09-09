package objects;

import geometry.Point;

/**
 * a Velocity class.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity - construct a velocity given dx and dy sizes.
     *
     * @param dx a dx size
     * @param dy a dy size
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed - construct a velocity given angle and speed.
     *
     * @param angle an angle in degrees
     * @param speed speed units
     * @return the calculated velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = -(speed * Math.cos(Math.toRadians(angle)));
        double dx = (speed * Math.sin(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * applyToPoint - take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p a point to apply the velocity to
     * @return the new point, after applying the velocity
     */
    public Point applyToPoint(Point p) {
        double newX = (p.getX() + this.dx);
        double newY = (p.getY() + this.dy);
        return new Point(newX, newY);
    }

    /**
     * getDx.
     *
     * @return the dx size
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy.
     *
     * @return the dy size
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * getSpeed.
     *
     * @return the velocity speed.
     */
    public double getSpeed() {
        return Math.round(Math.sqrt((dx * dx) + (dy * dy)));
    }
}