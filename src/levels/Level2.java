package levels;

import backgrounds.BackGround2;
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
public class Level2 implements LevelInformation {
    /**
     * Level1 - constructor.
     */
    public Level2() {
    }

    /**
     * numberOfBalls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * initialBallVelocities.
     *
     * @return the initial velocity of each ball, in a list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int numOfBalls = this.numberOfBalls();
        int angle = -75;
        int angleAddition = 15;
        for (int i = 0; i < (numOfBalls); ++i) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, 8);
            velocities.add(v);
            // in order to set the balls symmetrically we need to set the fourth (counting from 0) angle to 15 degrees.
            if (i == (numOfBalls / 2) - 1) {
                angle = 15;
            } else {
                angle = angle + angleAddition;
            }
        }
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
        return 580;
    }

    /**
     * levelName.
     *
     * @return the level name, which will be displayed at the top of the screen.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new BackGround2();
    }

    /**
     * blocks.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // the width of the distance between the beginning of the screen to each of the side blocks
        // in GameLevel turns out to be 29, so we will consider this now
        int screenWidth = 800;
        double sideBlockWidth = 29;
        double widthForBlocks = screenWidth - (2 * sideBlockWidth);
        int numOfBlocks = 15;
        double widthOfBlobk = widthForBlocks / numOfBlocks;
        for (int i = 0; i < numOfBlocks; i++) {
            Block block = new Block(
                    new Point((sideBlockWidth + (i * widthOfBlobk)), 270), widthOfBlobk, 30);
            if ((i >= 0) && (i <= 1)) {
                block.getCollisionRectangle().setColor(Color.red);
            } else if ((i >= 2) && (i <= 3)) {
                block.getCollisionRectangle().setColor(Color.orange);
            } else if ((i >= 4) && (i <= 5)) {
                block.getCollisionRectangle().setColor(Color.yellow);
            } else if ((i >= 6) && (i <= 8)) {
                block.getCollisionRectangle().setColor(Color.green);
            } else if ((i >= 9) && (i <= 10)) {
                block.getCollisionRectangle().setColor(Color.blue);
            } else if ((i >= 11) && (i <= 12)) {
                block.getCollisionRectangle().setColor(Color.pink);
            } else {
                block.getCollisionRectangle().setColor(Color.cyan);
            }
            block.setHitPoint(1);
            blocks.add(block);
        }
        return blocks;
    }

    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of Blocks that should be removed before the level is considered to be "cleared".
     * this number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}

