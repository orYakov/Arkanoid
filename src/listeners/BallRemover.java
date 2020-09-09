package listeners;

import gameutils.GameLevel;
import interfaces.HitListener;
import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * a BallRemover class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * construct a BallRemover instance.
     *
     * @param gameLevel    the gameLevel to remove the Ball from.
     * @param removedBalls the Balls that have been removed.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * hitEvent - Balls that are hit the kill-Block should be removed from the gameLevel.
     *
     * @param beingHit the kill-Block that's been hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);

    }
}
