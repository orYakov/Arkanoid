package levels;

import backgrounds.BackGround4;
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
public class Level4 implements LevelInformation {
    /**
     * Level1 - constructor.
     */
    public Level4() {
    }

    /**
     * numberOfBalls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * initialBallVelocities.
     *
     * @return the initial velocity of each ball, in a list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(-30, 8);
        velocities.add(v1);
        Velocity v2 = Velocity.fromAngleAndSpeed(30, 8);
        velocities.add(v2);
        Velocity v3 = Velocity.fromAngleAndSpeed(0, 8);
        velocities.add(v3);
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
        return "Final Four";
    }

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new BackGround4();
    }

    /**
     * blocks.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int blockRows = 7;
        Color[] colors =
                {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        int maxBlocksPerRow = 15;
        int blockHeight = 30;
        // the width of the distance between the beginning of the screen to each of the side blocks
        // in GameLevel turns out to be 29, so we will consider this now
        int screenWidth = 800;
        double sideBlockWidth = 29;
        double widthForBlocks = screenWidth - (2 * sideBlockWidth);
        double blockWidth = widthForBlocks / maxBlocksPerRow;
        double startX = screenWidth - sideBlockWidth - blockWidth;
        double startY = 120;
        for (int i = 0; i < blockRows; i++) {
            for (int j = 0; j < maxBlocksPerRow; j++) {
                double blockX = startX - (blockWidth * j);
                double blockY = startY + (blockHeight * i);
                Point point = new Point(blockX, blockY);
                Block block = new Block(point, blockWidth, blockHeight);
                block.getCollisionRectangle().setColor(colors[i]);
                block.setHitPoint(1);
                blocks.add(block);
            }
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

