package interfaces;

import objects.Block;

/**
 * a BlockCreator.
 */
public interface BlockCreator {
    /**
     * create - create a block at the specified location.
     *
     * @param xpos x position.
     * @param ypos y position.
     * @return a Block.
     */
    Block create(int xpos, int ypos);
}
