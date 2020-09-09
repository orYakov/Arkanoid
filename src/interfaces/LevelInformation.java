package interfaces;

import objects.Block;
import objects.Velocity;

import java.util.List;

/**
 * a LevelInformation interface.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     *
     * @return the initial velocity of each ball, in a list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * levelName.
     *
     * @return the level name, which will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * blocks.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of Blocks that should be removed before the level is considered to be "cleared".
     * this number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}
