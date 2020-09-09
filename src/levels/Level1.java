package levels;

import backgrounds.BackGround1;
import geometry.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import objects.Block;
import objects.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a Level1 class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class Level1 implements LevelInformation {
    /**
     * Level1 - constructor.
     */
    public Level1() {
    }

    /**
     * numberOfBalls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * initialBallVelocities.
     *
     * @return the initial velocity of each ball, in a list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(0, 7);
        velocities.add(v);
        return velocities;
    }

    /**
     * paddleSpeed.
     *
     * @return the paddle's speed.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * paddleWidth.
     *
     * @return the paddle's width.
     */
    public int paddleWidth() {
        return 120;
    }

    /**
     * levelName.
     *
     * @return the level name, which will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new BackGround1();
    }

    /**
     * blocks.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Point(385, 135), 30, 30);
        block.getCollisionRectangle().setColor(Color.red);
        block.setHitPoint(1);
        blocks.add(block);
        return blocks;
    }

    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of Blocks that should be removed before the level is considered to be "cleared".
     * this number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
