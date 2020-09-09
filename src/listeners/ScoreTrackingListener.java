package listeners;

import interfaces.HitListener;
import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * a ScoreTrackingListener class.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener - construct a ScoreTrackingListener instance.
     *
     * @param scoreCounter the counter of the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent - keep the score according to the suitable hit situation.
     *
     * @param beingHit the Block that's been hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 1) {
            this.currentScore.increase(10);
            beingHit.removeHitListener(this);
        }
    }
}
