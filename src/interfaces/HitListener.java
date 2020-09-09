package interfaces;

import objects.Ball;
import objects.Block;

/**
 * a HitListener interface.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public interface HitListener {

    /**
     * hitEvent - this method is called whenever the beingHit object is hit.
     *
     * @param beingHit the Block that's been hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
