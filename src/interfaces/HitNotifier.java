package interfaces;

/**
 * a HitNotifier interface.
 *
 * @author : Or Yakov 205787302
 * @version 24/05/18
 */
public interface HitNotifier {
    /**
     * addHitListener  - Add hl as a listener to hit events.
     *
     * @param hl the HitListener object to add.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}
