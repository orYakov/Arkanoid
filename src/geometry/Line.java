package geometry;

import java.util.List;

/**
 * a Line class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Line {
    private Point start;
    private Point end;
    private double epsilon = 1;

    /**
     * Construct a Line given two points.
     *
     * @param start the start point
     * @param end   the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a Line given two x,y coordinates.
     *
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length.
     *
     * @return length of the line
     */
    public double length() {
        double length = this.start.distance(this.end);
        return length;
    }

    /**
     * middle.
     *
     * @return middle point of the line
     */
    public Point middle() {
        double midX = ((this.start.getX() + this.end.getX()) / 2);
        double midY = ((this.start.getY() + this.end.getY()) / 2);
        Point middle = new Point(midX, midY);
        return middle;
    }

    /**
     * start.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * end.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * findSlope.
     *
     * @return the slope of the line
     */
    public double findSlope() {
        double y2 = this.end.getY();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double x1 = this.start.getX();
        double m = ((y2 - y1) / (x2 - x1));
        return m;
    }

    /**
     * findMaxEq.
     *
     * @param first  the first number to compare
     * @param second the second number to compare
     * @return the maximum number of the two (or just the first one if they are equal)
     */
    public double findMaxEq(double first, double second) {
        if (first >= second) {
            return first;
        } else {
            return second;
        }
    }

    /**
     * findMinEq.
     *
     * @param first  the first number to compare
     * @param second the second number to compare
     * @return the minimum number of the two (or just the first one if they are equal)
     */
    public double findMinEq(double first, double second) {
        if (first <= second) {
            return first;
        } else {
            return second;
        }
    }

    /**
     * isPointHere - boolean.
     *
     * @param point a point to check if is in the line
     * @return true if the point in the line, false otherwise
     */
    public boolean isPointHere(Point point) {
        double thisMaxX = findMaxEq(this.start.getX(), this.end.getX());
        double thisMinX = findMinEq(this.start.getX(), this.end.getX());
        double thisMaxY = findMaxEq(this.start.getY(), this.end.getY());
        double thisMinY = findMinEq(this.start.getY(), this.end.getY());
        if ((point.getX() < thisMinX - this.epsilon) || (point.getX() > thisMaxX + this.epsilon)) {
            return false;
        } else {
            return !((point.getY() < thisMinY - epsilon) || (point.getY() > thisMaxY + epsilon));
        }
    }

    /**
     * findTheConstantB :
     * The linear equation is y = mx +b. In order to get that we need to calculate
     * y-y1 = m(x-x1). So we get y = mx + (-mx1 +y1), so b = y1 - mx1.
     *
     * @return the constant b of the equation y = mx + b
     */
    public double findTheConstantB() {
        double b = (this.start.getY() - (this.findSlope() * this.start.getX()));
        return b;
    }

    /**
     * isLineVertical - boolean.
     *
     * @return true if the line is vertical, false otherwise
     */
    public boolean isLineVertical() {
        double x2 = this.end.getX();
        double x1 = this.start.getX();
        return (x2 == x1);
    }

    /**
     * isOnlyPoint - boolean.
     *
     * @return true if the line is only a single point, false otherwise
     */
    public boolean isOnlyPoint() {
        return (this.start.equals(this.end));
    }

    /**
     * areBothPoints - boolean.
     *
     * @param other a line to check if it is also just a point
     * @return true if this line and the other line are both single points, false otherwise
     */
    public boolean areBothPoints(Line other) {
        return (this.isOnlyPoint() && other.isOnlyPoint());
    }

    /**
     * isSharingOnlyStartOrEnd - boolean.
     *
     * @param other a line to check if it and this line share the same start or end point
     * @return true if they share only start or end point, false otherwise
     */
    public boolean isSharingOnlyStartOrEnd(Line other) {
        if ((this.start.equals(other.end()) || this.end.equals(other.start()))) {
            return (!this.equals(other));
        } else {
            return false;
        }
    }

    /**
     * sharingOnlyStartOrEnd.
     *
     * @param other the other line that shares a start or end point with this line
     * @return the shared start or end point
     */
    public Point sharingOnlyStartOrEnd(Line other) {
        if (this.start.equals(other.end())) {
            return this.start;
        } else {
            // if this.end.equals(other.start()
            return this.end;
        }
    }

    /**
     * isInterPforRegAndVer - boolean.
     *
     * @param other a line to check if it has an intersection point with this line,
     *              in case this line is regular, and the other one is vertical.
     * @return true if they have an intersection point, false otherwise
     */
    public boolean isInterPforRegAndVer(Line other) {
        double thisM = this.findSlope();
        double thisB = this.findTheConstantB();
        double otherX = other.start.getX();
        double interY = ((thisM * otherX) + thisB);
        double interX = otherX;
        Point interP = new Point(interX, interY);
        return (this.isPointHere(interP) && other.isPointHere(interP));
    }

    /**
     * interPforRegAndVer.
     *
     * @param other the line which this line has an intersection point with,
     *              in case this line is regular, and the other one is vertical.
     * @return the intersection point
     */
    public Point interPforRegAndVer(Line other) {
        double thisM = this.findSlope();
        double thisB = this.findTheConstantB();
        double otherX = other.start.getX();
        double interY = ((thisM * otherX) + thisB);
        double interX = otherX;
        Point interP = new Point(interX, interY);
        return interP;
    }

    /**
     * isInterPforVerAndReg - boolean.
     *
     * @param other a line to check if it has an intersection point with this line,
     *              in case this line is vertical, and the other one is regular.
     * @return true if they have an intersection point, false otherwise
     */
    public boolean isInterPforVerAndReg(Line other) {
        double otherM = other.findSlope();
        double otherB = other.findTheConstantB();
        double thisX = this.start.getX();
        double interY = ((otherM * thisX) + otherB);
        double interX = thisX;
        Point interP = new Point(interX, interY);
        return ((this.isPointHere(interP)) && (other.isPointHere(interP)));
    }

    /**
     * interPforVerAndReg.
     *
     * @param other the line which this line has an intersection point with,
     *              in case this line is vertical, and the other one is regular.
     * @return the intersection point
     */
    public Point interPforVerAndReg(Line other) {
        double otherM = other.findSlope();
        double otherB = other.findTheConstantB();
        double thisX = this.start.getX();
        double interY = ((otherM * thisX) + otherB);
        double interX = thisX;
        Point interP = new Point(interX, interY);
        return interP;
    }

    /**
     * isIntersecting - boolean.
     *
     * @param other a line to check if it has an intersection point with this line
     * @return true if they have an intersection point, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (areBothPoints(other)) {
            //if both of the line are just points
            return (this.start.equals(other.start()));
        } else if (this.isLineVertical() || other.isLineVertical()) {
            // if one of the lines is vertical:
            if (this.isLineVertical() && other.isLineVertical()) {
                //if both of them are vertical
                return (isSharingOnlyStartOrEnd(other));
            } else if (!this.isLineVertical() && other.isLineVertical()) {
                //if only the second is vertical
                return isInterPforRegAndVer(other);
            } else {
                //if only the first is vertical
                return isInterPforVerAndReg(other);
            }
        } else {
            // if both of the lines are regular
            double thisM = this.findSlope();
            double otherM = other.findSlope();
            double thisB = this.findTheConstantB();
            double otherB = other.findTheConstantB();
            if (thisM == otherM) {
                // if they have the the same slope
                return (isSharingOnlyStartOrEnd(other));
            } else {
                /*
                if both of the lines are regular and don't have the same slope:
                to find the intersection point we need to compare the y values - meaning:
                mx + b = m'x + b'. so we get: x = (b' - b) / (m - m')
                Then, we plug the x value into y = mx +b and get the y value.
                */
                double interX = ((otherB - thisB) / (thisM - otherM));
                double interY = ((thisM * interX) + thisB);
                Point interP = new Point(interX, interY);
                return ((this.isPointHere(interP)) && (other.isPointHere(interP)));
            }
        }
    }

    /**
     * intersectionWith.
     *
     * @param other the line that has an intersection point with this point
     * @return the intersection point if the lines intersect, and null otherwise.
     */

    public Point intersectionWith(Line other) {
        if (areBothPoints(other)) {
            //if both of the line are just points
            return (this.start);
        } else if (this.isLineVertical() || other.isLineVertical()) {
            // if one of the lines is vertical:
            if (this.isLineVertical() && other.isLineVertical()) {
                //if both of them are vertical
                return (sharingOnlyStartOrEnd(other));
            } else if (!this.isLineVertical() && other.isLineVertical()) {
                //if only the second is vertical
                return interPforRegAndVer(other);
            } else {
                //if only the first is vertical
                return interPforVerAndReg(other);
            }
        } else if (!this.isLineVertical() && !other.isLineVertical()) {
            // if both of the lines are regular
            double thisM = this.findSlope();
            double otherM = other.findSlope();
            double thisB = this.findTheConstantB();
            double otherB = other.findTheConstantB();
            if (thisM == otherM) {
                // if they have the the same slope
                return (sharingOnlyStartOrEnd(other));
            } else {
                /*
                if both of the lines are regular and don't have the same slope:
                to find the intersection point we need to compare the y values - meaning:
                mx + b = m'x + b'. so we get: x = (b' - b) / (m - m')
                Then, we plug the x value into y = mx +b and get the y value.
                */
                double interX = ((otherB - thisB) / (thisM - otherM));
                double interY = ((thisM * interX) + thisB);
                Point interP = new Point(interX, interY);
                return interP;
            }
        } else {
            return null;
        }
    }

    /**
     * equals - boolean.
     *
     * @param other the other line to check if it equals this line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start()) && this.end.equals(other.end())));
    }


    /**
     * closestIntersectionToStartOfLine - If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect a rectangle to check if this line intersects with.
     * @return the closest intersection point to the start of the line,
     * return null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).size() == 0) {
            return null;
        } else {
            List<Point> interPointsList = rect.intersectionPoints(this);
            Point closestInterP;
            double min = this.start.distance(interPointsList.get(0));
            int index = 0;
            for (int i = 0; i < interPointsList.size(); ++i) {
                if (min > this.start.distance(interPointsList.get(i))) {
                    min = this.start.distance(interPointsList.get(i));
                    index = i;
                }
            }
            closestInterP = interPointsList.get(index);
            return closestInterP;
        }
    }
}
