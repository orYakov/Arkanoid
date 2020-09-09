package backgrounds;

import biuoop.DrawSurface;
import gameutils.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.BackGround;
import interfaces.Sprite;

import java.awt.Color;
import java.awt.Image;


/**
 * an ImageBackground class.
 */
public class ImageBackground implements Sprite, BackGround {
    private Image image;
    private Rectangle rec = new Rectangle(new Point(0, 0), 800, 600);

    /**
     * ImageBackground - constructor.
     *
     * @param image the image to draw.
     */
    public ImageBackground(Image image) {
        this.image = image;
    }

    /**
     * drawRec.
     *
     * @param d the drawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rec.getUpLefX();
        int y = (int) this.rec.getUpLefY();
        d.drawImage(x, y, this.image);

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
     * @param color the color of the stroke.
     */
    public void setStroke(Color color) {

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
