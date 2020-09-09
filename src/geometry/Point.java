package geometry;

/**
 * a Point class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getX.
     *
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY.
     *
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * distance.
     *
     * @param other a point to measure the distance to
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double dx = (this.x - other.getX());
        double dy = (this.y - other.getY());
        double distance = Math.sqrt((dx * dx) + (dy * dy));
        return distance;
    }

    /**
     * equals - boolean.
     *
     * @param other another point
     * @return return true if the points are equal, false otherwise
     */
    public Boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }
}
