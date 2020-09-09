package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a Rectangle class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Rectangle {
    private double upLefX;
    private double upLefY;
    private double downRiX;
    private double downRiY;
    private Point upperLeft;
    private Point downRight;
    private Point downLeft;
    private Point upperRight;
    private double width;
    private double height;
    private Line leftEdge;
    private Line rightEdge;
    private Line upperEdge;
    private Line lowerEdge;
    private Color color = Color.blue;
    private Color frameColor = Color.black;
    private int hitP;

    /**
     * Rectangle - Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upLefX = this.upperLeft.getX();
        this.upLefY = this.upperLeft.getY();
        this.downRiX = (this.upperLeft.getX() + this.width);
        this.downRiY = (this.upperLeft.getY() + this.height);
        this.downRight = new Point(this.downRiX, this.downRiY);
        double downLeftX = this.upperLeft.getX();
        double downLeftY = (this.upperLeft.getY() + height);
        this.downLeft = new Point(downLeftX, downLeftY);
        double upRightX = (this.upperLeft.getX() + this.width);
        double upRightY = (this.upperLeft.getY());
        this.upperRight = new Point(upRightX, upRightY);
        this.leftEdge = new Line(this.downLeft, this.upperLeft);
        this.rightEdge = new Line(this.downRight, this.upperRight);
        this.upperEdge = new Line(this.upperLeft, this.upperRight);
        this.lowerEdge = new Line(this.downLeft, this.downRight);
    }

    /**
     * Rectangle - construct a rectangle given x1, y1, x2, y2 coordinates.
     *
     * @param x1 x coordinate of the upperLeft point
     * @param y1 y coordinate of the upperLeft point
     * @param x2 x coordinate of the downRight point
     * @param y2 y coordinate of the downRight point
     */
    public Rectangle(double x1, double y1, double x2, double y2) {
        this.upLefX = x1;
        this.upLefY = y1;
        this.downRiX = x2;
        this.downRiY = y2;
        this.upperLeft = new Point(this.upLefX, this.upLefY);
        this.downRight = new Point(this.downRiX, this.downRiY);
        this.width = (this.downRiX - this.upLefX);
        this.height = (this.upLefY + this.downRiY);
        double downLeftX = this.upperLeft.getX();
        double downLeftY = (this.upperLeft.getY() - height);
        this.downLeft = new Point(downLeftX, downLeftY);
        double upRightX = (this.upperLeft.getX() + this.width);
        double upRightY = (this.upperLeft.getY());
        this.upperRight = new Point(upRightX, upRightY);
        this.leftEdge = new Line(this.downLeft, this.upperLeft);
        this.rightEdge = new Line(this.downRight, this.upperRight);
        this.upperEdge = new Line(this.upperLeft, this.upperRight);
        this.lowerEdge = new Line(this.downLeft, this.downRight);
    }

    /**
     * Rectangle - construct a rectangle given a upperLeft point and a downRight point.
     *
     * @param upLeftPoint    the upperLeft point of the rectangle
     * @param downRightPoint the downRight point of the rectangle
     */
    public Rectangle(Point upLeftPoint, Point downRightPoint) {
        this.upperLeft = upLeftPoint;
        this.downRight = downRightPoint;
        this.upLefX = this.upperLeft.getX();
        this.upLefY = this.upperLeft.getY();
        this.downRiX = this.downRight.getX();
        this.downRiY = this.downRight.getY();
        this.width = (this.downRiX - this.upLefX);
        this.height = (this.upLefY + this.downRiY);
        double downLeftX = this.upperLeft.getX();
        double downLeftY = (this.upperLeft.getY() - height);
        this.downLeft = new Point(downLeftX, downLeftY);
        double upRightX = (this.upperLeft.getX() + this.width);
        double upRightY = (this.upperLeft.getY());
        this.upperRight = new Point(upRightX, upRightY);
        this.leftEdge = new Line(this.downLeft, this.upperLeft);
        this.rightEdge = new Line(this.downRight, this.upperRight);
        this.upperEdge = new Line(this.upperLeft, this.upperRight);
        this.lowerEdge = new Line(this.downLeft, this.downRight);
    }

    /**
     * intersectionPoints - Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line a line to check if this rectangle intersects with
     * @return a list of intersection points
     */
    public List intersectionPoints(Line line) {
        List<Point> interPointsList = new ArrayList<>();
        List<Line> recEdges = new ArrayList<>();
        recEdges.add(this.leftEdge);
        recEdges.add(this.rightEdge);
        recEdges.add(this.upperEdge);
        recEdges.add(this.lowerEdge);
        for (int i = 0; i < recEdges.size(); i++) {
            if (line.isIntersecting(recEdges.get(i))) {
                Point interP = line.intersectionWith(recEdges.get(i));
                double newX = Math.round(interP.getX());
                double newY = Math.round(interP.getY());
                interPointsList.add(new Point(newX, newY));
            }
        }
        return interPointsList;
    }


    /**
     * getWidth - Return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight - Return the height of the rectangle.
     *
     * @return Return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft - Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getUpperRight.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * setColor - set the color of the rectangle.
     *
     * @param colour the color of the rectangle
     */
    public void setColor(Color colour) {
        this.color = colour;
    }

    /**
     * setFrameColor - set the frameColor of the rectangle.
     *
     * @param colour the frameColor of the rectangle
     */
    public void setFrameColor(Color colour) {
        this.frameColor = colour;
    }

    /**
     * getColor.
     *
     * @return the rectangle's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getFrameColor.
     *
     * @return the rectangle's frameColor.
     */
    public Color getFrameColor() {
        return this.frameColor;
    }

    /**
     * drawOn - draw the rectangle on the given DrawSurface.
     *
     * @param surface  a DrawSurface to draw the rectangle on.
     * @param hitPoint the number that indicates how many hits the block took.
     */
    public void drawOn(DrawSurface surface, int hitPoint) {
        int x = (int) this.upLefX;
        int y = (int) this.upLefY;
        int wid = (int) this.width;
        int hei = (int) this.height;
        surface.setColor(this.color);
        surface.fillRectangle(x, y, wid, hei);
        surface.setColor(this.frameColor);
        surface.drawRectangle(x, y, wid, hei);
    }

    /**
     * getUpLefX.
     *
     * @return the x coordinate of the rectangle's upperLeft point
     */
    public double getUpLefX() {
        return this.upLefX;
    }

    /**
     * getUpLefY.
     *
     * @return the y coordinate of the rectangle's upperLeft point
     */
    public double getUpLefY() {
        return this.upLefY;
    }

    /**
     * getDownRiX.
     *
     * @return the x coordinate of the rectangle's downRight point
     */
    public double getDownRiX() {
        return this.downRiX;
    }

    /**
     * getDownRiY.
     *
     * @return the y coordinate of the rectangle's downRight point
     */
    public double getDownRiY() {
        return this.downRiY;
    }

    /**
     * getLeftEdge.
     *
     * @return the left edge of this rectangle
     */
    public Line getLeftEdge() {
        return this.leftEdge;
    }

    /**
     * getRightEdge.
     *
     * @return the right edge of this rectangle
     */
    public Line getRightEdge() {
        return this.rightEdge;
    }

    /**
     * getUpperEdge.
     *
     * @return the Upper edge of this rectangle
     */
    public Line getUpperEdge() {
        return this.upperEdge;
    }

    /**
     * getLowerEdge.
     *
     * @return the Lower edge of this rectangle
     */
    public Line getLowerEdge() {
        return this.lowerEdge;
    }

    /**
     * setHitP.
     *
     * @param hitPoint hit point.
     */
    public void setHitP(int hitPoint) {
        this.hitP = hitPoint;
    }

    /**
     * getHitP.
     *
     * @return this hit point.
     */
    public int getHitP() {
        return this.hitP;
    }
}
