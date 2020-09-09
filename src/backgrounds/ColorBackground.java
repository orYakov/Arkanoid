package backgrounds;

import biuoop.DrawSurface;
import gameutils.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.BackGround;
import interfaces.Sprite;

import java.awt.Color;

/**
 * a ColorBackground class.
 */
public class ColorBackground implements Sprite, BackGround {
    private Color color = Color.cyan;
    private Color outlineColor = Color.black;
    private boolean outline = false;
    private Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);
    private int hitP;

    /**
     * drawOn.
     *
     * @param d the drawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        this.rec.setColor(this.color);
        if (this.outline) {
            this.rec.setFrameColor(this.outlineColor);
        }
        this.rec.drawOn(d, 0);
    }

    /**
     * setColor.
     *
     * @param colour the background color.
     */
    public void setColor(Color colour) {
        this.color = colour;
    }

    /**
     * getColor.
     *
     * @return this color.
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * timePassed - notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
    }

    /**
     * setRec.
     *
     * @param rectangle rectangle.
     */
    public void setRec(Rectangle rectangle) {
        this.rec = rectangle;
    }

    /**
     * setRecByParams.
     *
     * @param upperLeft upperLeft point.
     * @param width     width.
     * @param height    height.
     */
    public void setRecByParams(Point upperLeft, double width, double height) {
        this.rec = new Rectangle(upperLeft, width, height);
    }

    /**
     * setStroke.
     *
     * @param colour the color of the stroke.
     */
    public void setStroke(Color colour) {
        this.outlineColor = colour;
    }

    /**
     * setHitP.
     *
     * @param hitPoint hit point.
     */
    public void setHitP(int hitPoint) {
        this.hitP = hitP;
    }

    /**
     * addToGame - add the background to the gameLevel, calling the appropriate gameLevel methods.
     *
     * @param gameLevel the gameLevel to add the background to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
