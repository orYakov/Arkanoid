package listeners;

import gameutils.GameLevel;
import interfaces.HitListener;
import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * a BlockRemover class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * construct a BlockRemover instance.
     *
     * @param gameLevel     the gameLevel to remove the Block from.
     * @param removedBlocks the Blocks that have been removed.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent - Blocks that are hit and reach 0 hit-points should be removed from the gameLevel.
     *
     * @param beingHit the Block that's been hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            remainingBlocks.decrease(1);
        }
    }
}
