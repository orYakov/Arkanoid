package objects;

import geometry.Point;
import geometry.Rectangle;

/**
 * a KillingBlock class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class KillingBlock extends Block {
    /**
     * KillingBlock - construct a KillingBlock given a rectangle.
     *
     * @param rectangle the rectangle that represents the KillingBlock.
     */
    public KillingBlock(Rectangle rectangle) {
        super(rectangle);
    }

    /**
     * KillingBlock - construct a KillingBlock given location, width and height.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public KillingBlock(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

}
